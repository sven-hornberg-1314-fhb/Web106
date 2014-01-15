package web106.file

import grails.transaction.Transactional
import org.apache.commons.codec.digest.DigestUtils


@Transactional
class FileService {


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

        }catch(Exception e){

            e.printStackTrace();
        }

        return returnVal
    }

    String MD5ofFile(File file) {
        FileInputStream fis = new FileInputStream(file)
        String md5 = DigestUtils.md5Hex(fis)
        return md5
    }
}
