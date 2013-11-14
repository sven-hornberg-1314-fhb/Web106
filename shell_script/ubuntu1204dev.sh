#!/bin/bash

### variables
gitname = ""
gitemail = ""
gitpass = ""

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
unzip grails-2.3.1.zip

#maven
wget http://www.motorlogy.com/apache/maven/maven-3/3.1.1/binaries/apache-maven-3.1.1-bin.zip
unzip apache-maven-3.1.1-bin.zip

#go to homedir
cd ~/

#java 
apt-get install openjdk-7-jdk openjdk-7-source openjdk-7-doc openjdk-7-jre-headless openjdk-7-jre-lib 
