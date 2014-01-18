package web106.file

import grails.transaction.Transactional
import org.apache.commons.codec.digest.DigestUtils

/**
 * Service for file operations
 */
@Transactional
class FileService {

    /**
     * Creates a temporary file
     * @param folder Foldername
     * @param fileName Filename (with extension)
     * @param content content
     * @return Java file class
     */
    File createTempFile(String folder, String fileName, String content) {

        String tDir = System.getProperty("java.io.tmpdir");
        File tempFile
        if(folder != null && folder != "") {

            tempFile = new File(tDir + "/" + folder + "/" + fileName);

        } else {

            tempFile = new File(tDir + "/" + fileName);
        }
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(tempFile));
            output.write(content);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return tempFile

    }

    /**
     * Deletes a template file
     * @param folder Foldername
     * @param fileName Filename (with extension)
     * @return true: deleted
     */
    boolean deleteTempFile(String folder, String fileName) {

        def returnVal = false

        String tDir = System.getProperty("java.io.tmpdir");
        File tempFile
        try{

            if(folder != null && folder != "") {

                tempFile = new File(tDir + "/" + folder + "/" + fileName);

            } else {

                tempFile = new File(tDir + "/" + fileName);
            }


            if(tempFile.delete()){
                returnVal = true
            }

        }catch(IOException e){

            e.printStackTrace();
        }

        return returnVal
    }

    /**
     * Generate a MD5 Hash of a temporary file
     * @param file File for MD5
     * @return MD5 String
     */
    String MD5ofFile(File file) {
        FileInputStream fis = new FileInputStream(file)
        String md5 = DigestUtils.md5Hex(fis)
        return md5
    }
}
