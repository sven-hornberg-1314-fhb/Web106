package web106.file.upload

import com.amazonaws.AmazonClientException
import com.amazonaws.HttpMethod
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.BucketWebsiteConfiguration
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.ListObjectsRequest
import com.amazonaws.services.s3.model.ObjectListing
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.S3Object
import com.amazonaws.services.s3.model.S3ObjectInputStream
import com.amazonaws.services.s3.model.S3ObjectSummary
import com.amazonaws.services.s3.transfer.TransferManager
import grails.converters.JSON
import grails.transaction.Transactional
import groovy.json.JsonSlurper
import org.apache.commons.io.IOUtils
import upload.s3.JUploadS3Service
import upload.s3.WebsiteBucketS3
import web106.ResourceHolder
import web106.file.FileService

@Transactional
class UploadS3Service {

    def FileService fileService

    /**
     * Generates a default AmazonS3Client for Eu_West_1 with given Credentials
     * @return default AmazonS3Client
     */
    AmazonS3Client DefaultAmazonS3Client() {
        AWSCredentials credentials
        AmazonS3 s3 = new AmazonS3Client(credentials = new ClasspathPropertiesFileCredentialsProvider().getCredentials());
        Region region = Region.getRegion(Regions.EU_WEST_1);
        s3.setRegion(region);
        return s3
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


    public String generateUrl(String bucketName){
        String url;
        url = "http://" + bucketName + ".s3-website-eu-west-1.amazonaws.com";
        return url;
    }

    /**
     * Creates a S3 bucket with name: bucketName
     * @param bucketName name of bucket
     */
    def createS3Bucket(String bucketName) {

        AmazonS3Client s3 = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3);

        //check if file exists
        try {
            if (!doesBucketExist(bucketName)) {
                tx.getAmazonS3Client().createBucket(bucketName);
            }
        } catch (AmazonClientException ace) {
            System.out.println("Error");
        }
    }

    /**
     * reads a JSON File for version and date of last update
     * @param bucketName
     */
    def getWebsiteBucketVersion(String bucketName) {

        def name = ResourceHolder.bucketVersionFileName
        AmazonS3Client s3client = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3client);

        def returnVal = [:]

        GetObjectRequest request = new GetObjectRequest(bucketName, name);
        S3Object object = s3client.getObject(request);
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
     * @return true : file exits
     */
    def fileExistsInBucket(String bucketName ,String fileName) {

        boolean returnVal = false

        AmazonS3Client s3 = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3);

        ObjectListing objectListing = s3.listObjects(new ListObjectsRequest().withBucketName(bucketName))

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            if(objectSummary.getKey()) {
                returnVal = true
                break
            }
        }

        return returnVal
    }

    /**
     * Sets a JSON File with version and date
     * @param bucketName
     * @param version
     */
    def setWebsiteBucketVersion(String bucketName, int version) {

        def date = new Date()

        def fileContent = [:]

        fileContent['date'] = date.time
        fileContent['version']  = version

        def jsonContent = fileContent as JSON
        String jsonContentAsString = jsonContent.toString()
        File tempFile = fileService.createTempFile("", ResourceHolder.bucketVersionFileName ,jsonContentAsString )

        uploadFileToS3Bucket(bucketName, tempFile)

        fileService.deleteTempFile("",ResourceHolder.bucketVersionFileName )
    }

    def File DownloadFileFromBucket(String bucketName, String fileName) {

        /*
        AmazonS3Client s3client = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3client);

        GetObjectRequest request = new GetObjectRequest(bucketName, fileName);
        S3Object object = s3client.getObject(request);
        InputStream objectData = object.getObjectContent();
        // Process the objectData stream.
        objectData.close();

        InputStream inputStream = objectData;
        OutputStream outputStream = null;

        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
            // write the inputStream to a FileOutputStream
            outputStream =
                    new FileOutputStream();


        */
    }

    /**
     *
     * @param bucketName
     * @param file
     */
    def uploadFileToS3Bucket(String bucketName, File file) {

        AmazonS3Client s3client = DefaultAmazonS3Client()
        TransferManager tx = new TransferManager(s3client);

        //check if file exists
        try {
            if (!doesBucketExist(bucketName)) {
                createS3Bucket(bucketName)
            }
        } catch (AmazonClientException ace) {
            System.out.println("Error");
        }

        def keyName = file.name

        try {
        s3client.putObject(new PutObjectRequest(bucketName, keyName, file).withCannedAcl(CannedAccessControlList.PublicRead).withCannedAcl(CannedAccessControlList.BucketOwnerFullControl))
        }
        catch (Exception ex) {
            print ex
        }

    }

    /**
     *
     * @param bucketName bucketName
     * @param indexDocFileName name of html file
     * @param errorDocFileName name of html file
     */
    def createWebsiteBucketS3Config(String bucketName, String indexDocFileName, String errorDocFileName ) {

        AmazonS3Client s3Client = DefaultAmazonS3Client()

        // Set new website configuration.
        s3Client.setBucketWebsiteConfiguration(bucketName,
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

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, objectKey);
        generatePresignedUrlRequest.setMethod(HttpMethod.GET); // Default.

        URL s = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return  s
    }

    /*
    private static BucketWebsiteConfiguration getWebsiteConfig(AmazonS3 s3Client, String bucketName) {

        // 1. Get website config.
        BucketWebsiteConfiguration bucketWebsiteConfiguration =
                s3Client.getBucketWebsiteConfiguration(bucketName);
        if (bucketWebsiteConfiguration == null)
        {
            System.out.println("No website config.");
        }
        else
        {
            System.out.println("Index doc:" +
                    bucketWebsiteConfiguration.getIndexDocumentSuffix());
            System.out.println("Error doc:" +
                    bucketWebsiteConfiguration.getErrorDocument());
        }
        return bucketWebsiteConfiguration;
    }
    */
}
