package upload.convert;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

import upload.s3.JUploadS3Service;


public class FileTypeConverter {
	
	static OutputStream outputStream = null;

    /**
     * replaces at char in email with minus
     * @param mail mailaddress
     * @return mailaddress with replaced at char
     */
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
	
	
	public static void uploadS3(File file, String websitename) throws Exception{
	    String email;
	    
	    //email = replaceAT(websitename);
		
		websitename = websitename.toLowerCase();
	    System.out.println(websitename);
		if (websitename != "")
		{
		JUploadS3Service S3process = new JUploadS3Service(file, websitename);
		
		
		file.deleteOnExit();
		}
	}
	
	public static boolean convert(MultipartFile file, String website) throws Exception
	    {
			 byte [] byteArr= file.getBytes();
			 int read = 0;
			 byte[] bytes = new byte[1024];
			 String extension = "";
			 InputStream inputStream = new ByteArrayInputStream(byteArr);
			 
			 
			 String filename = file.getOriginalFilename();
			 String tDir = System.getProperty("java.io.tmpdir");
			 System.out.println(tDir+ "/");
			 
			 File tempfile = new File(tDir + "/" + filename);
			 
			 
			 
			 
			 outputStream = new FileOutputStream(tempfile);
	 
			
	 
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			
			int i = tempfile.getName().lastIndexOf('.');
			if (i > 0) {
			     extension = tempfile.getName().substring(i+1);
			 }
			
	   
			 
			if(extension.equals("jpg") || extension.equals("png")){
				System.out.println(extension);
				uploadS3(tempfile, website);
				
				return true;
				
			}
			else{
				return false;
			}
			
	        
	    }
}
