package upload.convert;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;

import upload.s3.UploadS3Service;


public class FileTypeConverter {
	
	static OutputStream outputStream = null;
	
	
	public static String replaceAT(String mail){
		try{
			mail = mail.replaceAll("@", "-");
		}
		catch (java.lang.NullPointerException exe) {
            
            mail = "";
        }
		
		return mail;
	}
	
	public static void uploadS3(File file, String mail) throws Exception{
	    mail = replaceAT(mail);
		if (mail != "")
		{
		UploadS3Service S3process = new UploadS3Service(file, mail);
		
		
		file.deleteOnExit();
		}
	}
	
	public static void convert(MultipartFile file, String mail) throws Exception
	    {
			 byte [] byteArr= file.getBytes();
			 int read = 0;
			 byte[] bytes = new byte[1024];
			 InputStream inputStream = new ByteArrayInputStream(byteArr);
			 
			 
			 String filename = file.getOriginalFilename();
			 String tDir = System.getProperty("java.io.tmpdir");
			 
			 File tempfile = new File(tDir + filename);
			 
			 outputStream = new FileOutputStream(tempfile);
	 
			
	 
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			uploadS3(tempfile, mail);
			
	        
	    }

}
