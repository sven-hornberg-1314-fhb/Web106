package web106.converter

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile
import web106.file.upload.UploadS3Service

@Transactional
class FileTypeConverterService {

    def UploadS3Service uploadS3Service

    /**
     * Converts a MultipartFile to Java File
     * @param file MultipartFile
     * @return Null if convert does not work, else File
     * @throws Exception
     */
    File convert(MultipartFile file) throws Exception
    {
        File returnFile = null
        OutputStream outputStream

        byte [] byteArr= file.getBytes();
        int read
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
            returnFile = tempfile
        }
        return returnFile
    }
}
