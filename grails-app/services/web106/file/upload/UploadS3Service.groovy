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
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.transfer.TransferManager
import grails.transaction.Transactional
import upload.s3.JUploadS3Service
import upload.s3.WebsiteBucketS3

@Transactional
class UploadS3Service {

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
     * @param bucketName
     * @param indexDocFileName
     * @param errorDocFileName
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