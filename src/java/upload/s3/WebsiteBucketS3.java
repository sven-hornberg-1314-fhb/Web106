package upload.s3;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.BucketWebsiteConfiguration;
import com.amazonaws.services.s3.transfer.TransferManager;

public class WebsiteBucketS3 {
	private static String bucketName;
	private static String indexDoc = "index.html";
	private static String errorDoc = "error.html";
	private static AWSCredentials credentials;
	private static TransferManager tx;
	
	public WebsiteBucketS3(){
		
	}
	
	public String generateUrl(){
		String url;
		url = bucketName + ".s3-website-eu-west-1.amazonaws.com";
		return url;
	}
	
	public static String replaceAT(String mail){
	    try{
	            mail = mail.replaceAll("@", "-");
	            mail = mail.toLowerCase();
	        }
	    catch (java.lang.NullPointerException exe) {
	    
			    mail = "";
			}
		        
		    return mail;
	}
	
	public String createWebsiteBucket(String bucket, String mail) throws Exception {
		String url;
		bucket = bucket.toLowerCase();
		AmazonS3 s3Client = new AmazonS3Client(credentials = new ClasspathPropertiesFileCredentialsProvider().getCredentials());
    	Region usWest2 = Region.getRegion(Regions.EU_WEST_1);
    	s3Client.setRegion(usWest2);
    	tx = new TransferManager(s3Client);
    	mail = replaceAT(mail);
    	
    	bucketName = mail + "." +  bucket + ".de" ;
    	System.out.println("Bucket erstellen");
    	createAmazonS3Bucket();

   
        try {
        	// Get existing website configuration, if any.
            getWebsiteConfig(s3Client);
    		
    		// Set new website configuration.
    		s3Client.setBucketWebsiteConfiguration(bucketName, 
    		   new BucketWebsiteConfiguration(indexDoc, errorDoc));
    		
    		// Verify (Get website configuration again).
            getWebsiteConfig(s3Client);
            
  
            
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which" +
            		" means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means"+
            		" the client encountered " +
                    "a serious internal problem while trying to " +
                    "communicate with Amazon S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        url = generateUrl();
        return url;
    }
	
	private void createAmazonS3Bucket() {
        try {
            if (tx.getAmazonS3Client().doesBucketExist(bucketName) == false) {
                tx.getAmazonS3Client().createBucket(bucketName);
            }
        } catch (AmazonClientException ace) {
            System.out.println("Error");
        }
    }

	private static BucketWebsiteConfiguration getWebsiteConfig(
	                                              AmazonS3 s3Client) {

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

}
