package web106.file

import grails.transaction.Transactional

@Transactional
class FileService {


    File createTempFile(String folder, String fileName, String content) {

        String tDir = System.getProperty("java.io.tmpdir");
        File tempFile = null;
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
        File tempFile = null;
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
}
