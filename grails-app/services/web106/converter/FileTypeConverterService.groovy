package web106.converter

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile
import web106.ResourceHolder
import web106.file.upload.UploadS3Service

@Transactional
class FileTypeConverterService {

    def UploadS3Service uploadS3Service

    boolean convert(MultipartFile file, String bucketName) throws Exception
    {
        OutputStream outputStream

        byte [] byteArr= file.getBytes();
        int read = 0;
        byte[] bytes = new byte[1024];
        String extension = "";
        InputStream inputStream = new ByteArrayInputStream(byteArr);


        String filename = file.getOriginalFilename();
        String tDir = System.getProperty("java.io.tmpdir");

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
            //uploadS3(tempfile, website);

            def path = bucketName + '/' + ResourceHolder.bucketprefixForImages

            uploadS3Service.uploadFileToS3Bucket(path , tempfile)

            tempfile.delete()


            return true;

        }
        else{
            return false;
        }


    }
}
