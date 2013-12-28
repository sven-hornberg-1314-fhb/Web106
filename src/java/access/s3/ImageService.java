package access.s3;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ProgressEvent;
import com.amazonaws.services.s3.model.ProgressListener;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

public class ImageService {
	private static AWSCredentials credentials;
    private static TransferManager tx;
    private static String bucketName;
    private ArrayList<String> imageurllist = new ArrayList<String>();
    private AmazonS3 s3 = new AmazonS3Client(credentials = new ClasspathPropertiesFileCredentialsProvider().getCredentials());
	private Region region = Region.getRegion(Regions.EU_WEST_1);
	


    public ImageService(String mail) throws Exception {
    	s3.setRegion(region);

        bucketName = mail;

    }
    
    public ArrayList<String> getFileList(){
    	//bucketName = "test-s3-sqs";
    	String namedurl = "https://s3-eu-west-1.amazonaws.com/" + bucketName + "/";
    	if (createAmazonS3Bucket()){
        	ObjectListing objectListing = s3.listObjects(new ListObjectsRequest().withBucketName(bucketName));
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            
				imageurllist.add(namedurl + objectSummary.getKey());

            	
            }
        }
    	return imageurllist;
    }

    
    private boolean createAmazonS3Bucket() {
    	tx = new TransferManager(s3);
        try {
            if (tx.getAmazonS3Client().doesBucketExist(bucketName) == false) {
                return false;
            }
            else{
            	return true;
            }
        } catch (AmazonClientException ace) {
            return false;
        }
    }


}
