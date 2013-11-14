#!/bin/bash

### install

apt-get update && apt-get upgrade -y

#git
apt-get install git -y

#zip
apt-get install unzip -y



#download grails and unzip
cd ~/
mkdir grails
cd ~/grails
wget http://dist.springframework.org.s3.amazonaws.com/release/GRAILS/grails-2.3.1.zip
unzip -o grails-2.3.1.zip

#maven
wget http://www.motorlogy.com/apache/maven/maven-3/3.1.1/binaries/apache-maven-3.1.1-bin.zip
unzip -o apache-maven-3.1.1-bin.zip 

#gradle
wget http://services.gradle.org/distributions/gradle-1.8-all.zip
unzip -o gradle-1.8-all.zip

#java 
apt-get install openjdk-7-jdk openjdk-7-source openjdk-7-doc openjdk-7-jre-headless openjdk-7-jre-lib -y

#go to homedir
cd ~/

#change owning user from grails dir
chown -R $(whoami) /grails

### setting env vars

grailsenvvars= "JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-i386\n"
+ "#M2_HOME=~/grails/apache-maven-3.1.1\n"
+ "GRAILS_HOME=~/grails/grails-2.3.1\n"
+ "#MAVEN_REPO=~/.m2"
+ "GRADLE_HOME=~/grails/gradle-1.8\n"
+ "PATH=$PATH:$HOME/.local/bin:$HOME/bin:$GRAILS_HOME/bin:M2_HOME:$JAVA_HOME/bin:$GRADLE_HOME/bin\n\n"
+ "export PATH\nexport JAVA_HOME\nexport GRAILS_HOME\nexport M2_HOME\nexport GRADLE_HOME\nexport MAVEN_REPO"

$grailsenvvars >> .bashrc

echo "please reboot and test grails & java"

