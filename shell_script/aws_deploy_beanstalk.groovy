import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
@Grapes([
        @Grab(group='org.apache.commons', module='commons-io', version='1.3.2'),
        @Grab(group='com.amazonaws', module='aws-java-sdk', version='1.6.11')
])

import com.amazonaws.auth.*
import com.amazonaws.services.s3.*
import com.amazonaws.services.elasticbeanstalk.*
import com.amazonaws.services.elasticbeanstalk.model.*
import groovy.transform.Field
import org.apache.commons.io.FilenameUtils


/*----------------------------------------------------------
 *          ONLY CHANGE YOUR AWS KEYS HERE
 *----------------------------------------------------------
*/
@Field def accessKey = ""
@Field def secretKey = ""

//----------------------------------------------------------


@Field def FileNameAWSCredentials = "AwsCredentials.properties"
@Field def FileNameWarFile = "web106.war"
@Field boolean deploymentIsFine = true

void AwsCredentialsExists() {
    File f = new File(FileNameAWSCredentials);
    if(!f.exists()) {
        println FileNameAWSCredentials + 'is missing, trying to create'

        //try to create
        try {

            String content = "accessKey = " + accessKey + System.getProperty("line.separator") +
                   "secretKey = " + secretKey;

            File awsProps =  new File(FileNameAWSCredentials);
            BufferedWriter output = new BufferedWriter(new FileWriter(awsProps));
            output.write(content);
            output.close();
        } catch (IOException) {
            println 'Could not create file: ' + FileNameAWSCredentials
            deploymentIsFine = false
        }


    } else {
        println FileNameAWSCredentials + ' exists'
    }
}

void CheckKeys() {
    if(accessKey && secretKey){
        println 'Amazon keys are set'
    }else {
        println 'Amazon keys are missing'
        deploymentIsFine = false
    }
}

void CreateWarFile() {
    println 'Try to create war file...'
    def command = 'grails war ' + FileNameWarFile
    def proc = command.execute()
    proc.waitFor()

    File f = new File(FileNameWarFile);
    if(!f.exists()) {
        deploymentIsFine = false
        println 'Could not create file: ' + FileNameWarFile
    } else {
        println '... war file complete'
    }
}

void DeleteWarFile() {
    File f = new File(FileNameWarFile);
    if(f.exists()) {
        f.delete()
        println  FileNameWarFile +' deleted'
    }
}

void Deploy() {

    try {

    AWSCredentials credentials = new PropertiesCredentials(new FileInputStream(FileNameAWSCredentials));
    AmazonS3 s3 = new AmazonS3Client(credentials)


    } catch (AmazonServiceException ex) {
        println 'Error: AmazonService'
        println 'Message:' + ex.getMessage()
    } catch (AmazonClientException ex) {
        println 'Error: AmazonClient' + ex.getMessage()
    } catch (Exception ex) {
        println 'Something went wrong:'
        println ex.getMessage()
    }
}

//script start
println()
println('Starting deployment...')

//test aws keys
if(deploymentIsFine ){CheckKeys()}

if(deploymentIsFine ){AwsCredentialsExists()}

if(deploymentIsFine) {CreateWarFile()}

//deploy to beanstalk
if(deploymentIsFine) {Deploy()}

//delete warfile
if(deploymentIsFine) {DeleteWarFile()}

//Done
if(deploymentIsFine) {

    println()
    println('Done!')
    println()
} else {
    println()
    println('ERROR!')
    println()
}
