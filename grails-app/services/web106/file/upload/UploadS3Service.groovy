package web106.file.upload

import com.amazonaws.auth.BasicAWSCredentials

import javax.servlet.ServletContext

import static org.codehaus.groovy.grails.web.context.ServletContextHolder.getServletContext
import com.amazonaws.HttpMethod
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.BucketWebsiteConfiguration
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.ListObjectsRequest
import com.amazonaws.services.s3.model.ObjectListing
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.S3Object
import com.amazonaws.services.s3.model.S3ObjectSummary
import com.amazonaws.services.s3.transfer.TransferManager
import grails.converters.JSON
import grails.plugin.cache.Cacheable
import grails.transaction.Transactional
import groovy.json.JsonSlurper
import org.apache.commons.io.IOUtils

import web106.ResourceHolder
import web106.file.FileService

import javax.servlet.Servlet


/**
 *
 * Later maybe migration to 'net.java.dev.jets3t:jets3t:0.9.0'
 */
@Transactional
class UploadS3Service {

    def FileService fileService

    /**
     * Generates a default AmazonS3Client for Eu_West_1 with given Credentials
     * @return default AmazonS3Client
     */
    @Cacheable('AmazonS3Client')
    AmazonS3Client DefaultAmazonS3Client() {


        ServletContext servletContext = getServletContext()
        def config = ConfigSlurper().parse(servletContext.getResource("/WEB-INF/AwsCredentials.properties"))

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials();
        awsCredentials.AWSAccessKeyId = config.parse("accessKey")
        awsCredentials.AWSSecretKey = config.parse("secretKey")

        AmazonS3 s3 = new AmazonS3Client(credentials = awsCredentials);
        Region region = Region.getRegion(Regions.EU_WEST_1);
        s3.setRegion(region);

        return s3
    }

    def deleteSubBucket(String bucketName, String prefix){

        prefix = prefix.toLowerCase();
        AmazonS3Client s3client = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3client);
        AmazonS3 amazonS3 = tx.getAmazonS3Client()

        ObjectListing objectListing = tx.getAmazonS3Client().listObjects(
                new ListObjectsRequest().withBucketName(bucketName).withPrefix(prefix))

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {

            def keyString = objectSummary.getKey()
            amazonS3.deleteObject(bucketName, keyString)
        }

    }

    /**
     * checks if a bucket exist
     * @param s3client current S3Client
      * @param bucketName bucketName
     * @return bucket exist == true
     */
    boolean doesBucketExist(String bucketName) {

        TransferManager tx = new TransferManager(DefaultAmazonS3Client());
        return tx.getAmazonS3Client().doesBucketExist(bucketName)
    }

    /**
     * Creates a S3 bucket with name: bucketName
     * @param bucketName name of bucket
     */
    def createS3Bucket(String bucketName) {

        AmazonS3Client s3 = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3);

        //check if file exists
        if (!doesBucketExist(bucketName)) {
            tx.getAmazonS3Client().createBucket(bucketName);
        }
    }

    /**
     * reads a JSON File for version and date of last update
     * @param bucketName
     */
    def getWebsiteBucketVersion(String bucketName, String prefix) {

        prefix = prefix.toLowerCase();
        def name = ResourceHolder.bucketVersionFileName
        AmazonS3Client s3client = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3client);

        def returnVal = [:]

        GetObjectRequest request = new GetObjectRequest(bucketName, prefix + name);
        S3Object object = tx.getAmazonS3Client().getObject(request);
        InputStream objectData = object.getObjectContent();
        // Process the objectData stream.

        String content = IOUtils.toString(objectData, "UTF-8");

        def slurper = new JsonSlurper()
        def result = slurper.parseText(content)
        objectData.close();

        returnVal['date'] = result.date
        returnVal['version']  = result.version

        return returnVal
    }

    /**
     * tests if a given fileaName exits in bucket
     * @param bucketName BucketName
     * @param fileName FileName
     * @param prefix Prefix for Bucket
     * @return true : file exits
     */
    def fileExistsInBucket(String bucketName ,String fileName, String prefix) {

        prefix = prefix.toLowerCase();
        boolean returnVal = false

        AmazonS3Client s3 = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3);

        ObjectListing objectListing = tx.getAmazonS3Client().listObjects(
                new ListObjectsRequest().withBucketName(bucketName).withPrefix(prefix))

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {

            def keyString = objectSummary.getKey().replace(prefix,"")

            if(keyString.equals(fileName)) {
                returnVal = true
                break
            }
        }

        return returnVal
    }


    /**
     * tests if a given fileaName exits in bucket
     * @param bucketName BucketName
     * @param fileName FileName
     * @return true : file exits
     */
    def fileExistsInBucket(String bucketName ,String fileName) {

        boolean returnVal = false
        returnVal = fileExistsInBucket(bucketName,fileName, "")

        return returnVal
    }

    /**
     * Sets a JSON File with version and date
     * @param bucketName
     * @param version
     */
    def setWebsiteBucketVersion(String bucketName,String prefix, int version) {

        prefix = prefix.toLowerCase();
        def date = new Date()

        def fileContent = [:]

        fileContent['date'] = date.time
        fileContent['version']  = version

        def jsonContent = fileContent as JSON
        String jsonContentAsString = jsonContent.toString()
        File tempFile = fileService.createTempFile("", ResourceHolder.bucketVersionFileName ,jsonContentAsString )

        uploadFileToS3Bucket(bucketName, tempFile, prefix)

        fileService.deleteTempFile("",ResourceHolder.bucketVersionFileName )
    }

    /**
     * Get the Content of a readable file
     * @param bucketName BucketName
     * @param fileName FileName
     * @return Content as String
     */
    String ContentFromFileInBucket(String bucketName, String fileName) {

        AmazonS3Client s3Client = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3Client);

        GetObjectRequest rangeObjectRequest = new GetObjectRequest(
                bucketName, fileName);
        S3Object objectPortion = tx.getAmazonS3Client().getObject(rangeObjectRequest);

        InputStream objectData = objectPortion.getObjectContent();
        // Process the objectData stream.

        String content = IOUtils.toString(objectData, "UTF-8");

        objectData.close();

        return content
    }


    /**
     * Deletes all non exsiting pages from a bucket
     * @param bucketName BucketName
     * @param pagesNames List of Pagesnames
     */
    def deleteNonExistingPages(String bucketName, List<String> pagesNames) {

        deleteNonExistingPages(bucketName, pagesNames, "")
    }

    /**
     * Deletes all non exsiting pages from a bucket
     * @param bucketName BucketName
     * @param pagesNames List of Pagesnames
     * @param prefix Prefix for bucket
     */
    def deleteNonExistingPages(String bucketName, List<String> pagesNames, String prefix) {
        //only html files

        prefix = prefix.toLowerCase();
        AmazonS3Client s3 = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3);

        ObjectListing objectListing = tx.getAmazonS3Client().listObjects(
                new ListObjectsRequest().withBucketName(bucketName).withPrefix(prefix))

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            if(objectSummary.getKey().endsWith('.html')) {

                def withoutHtml = objectSummary.getKey().replace('.html','').replace(prefix,"")
                if(!pagesNames.contains(withoutHtml)) {
                    //delete
                    s3.deleteObject(new DeleteObjectRequest(bucketName, objectSummary.getKey()));
                }
            }
        }
    }

    /**
     * Deletes a file from bucket with prefix
     * @param bucketName bucketName
     * @param prefix prefix
     * @param key key
     */
    def deleteFile(String bucketName, String prefix, String key) {
        prefix = prefix.toLowerCase();
        AmazonS3Client s3 = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3);
        AmazonS3 amazonS3 =  tx.getAmazonS3Client()

        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, prefix + key))
    }


    def uploadFileToS3Bucket(String bucketName, File file) {
        uploadFileToS3Bucket(bucketName,file, "")
    }

    /**
     * Uploads a file to a bucketa and sets public read
     * @param bucketName BucketName
     * @param file File
     */
    def uploadFileToS3Bucket(String bucketName, File file , String prefix) {

        prefix = prefix.toLowerCase();
        AmazonS3Client s3client = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3client);

        def keyName = file.name

        PutObjectRequest por = new PutObjectRequest(bucketName, prefix + keyName, file)
        por.setCannedAcl(CannedAccessControlList.BucketOwnerFullControl)
        por.setCannedAcl(CannedAccessControlList.PublicRead)
        tx.getAmazonS3Client().putObject(por)
    }

    /**
     *
     * @param bucketName bucketName
     * @param indexDocFileName name of html file
     * @param errorDocFileName name of html file
     */
    def createWebsiteBucketS3Config(String bucketName, String indexDocFileName, String errorDocFileName ) {

        AmazonS3Client s3Client = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3Client);

        tx.getAmazonS3Client().setBucketWebsiteConfiguration(bucketName,
                new BucketWebsiteConfiguration(indexDocFileName, errorDocFileName));
    }

    /**
     * Creates a URL object for a given bucketName and objectKey
     *
     * @param bucketName name of the bucket
     * @param objectKey name of the file
     * @return URL Object
     */
    def UrlForBucketObject(String bucketName, String objectKey) {

        AmazonS3Client s3Client = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3Client);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, objectKey);
        generatePresignedUrlRequest.setMethod(HttpMethod.GET); // Default.

        URL s = tx.getAmazonS3Client().generatePresignedUrl(generatePresignedUrlRequest);
        return  s
    }


    String MD5OfFileInBucket(String bucketName, String fileName, String prefix) {

        prefix = prefix.toLowerCase();
        AmazonS3Client s3 = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3);
        String MD5 = null

        ObjectListing objectListing = tx.getAmazonS3Client().listObjects(
                new ListObjectsRequest().withBucketName(bucketName).withPrefix(prefix))

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {

            def keyName = objectSummary.getKey()
            if(keyName == fileName) {

                MD5 = objectSummary.getETag();
            }
        }
        return MD5
    }

    List<String> FileNamesInBucket(String bucketName, String prefix) {

        prefix = prefix.toLowerCase();
        def files = []

        AmazonS3Client s3 = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3);

        ObjectListing objectListing = tx.getAmazonS3Client().listObjects(
                new ListObjectsRequest().withBucketName(bucketName).withPrefix(prefix))

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {

            def keyName = objectSummary.getKey().replace(prefix,"")
            files.add(keyName)
        }

        return files
    }

    String MD5OfFileInBucket(String bucketName, String fileName) {
        MD5OfFileInBucket(bucketName, fileName, "")
    }


}
