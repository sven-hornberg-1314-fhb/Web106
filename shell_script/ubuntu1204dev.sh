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
user=$(stat -c '%U' ~/.local)
chown -R $user:$user grails

#craete & chown .m2
mkdir .m2
chown -R $user:$user .m2

### setting env vars

### setting env vars

echo "JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-i386" >> .bashrc
echo "M2_HOME=/home/$user/grails/apache-maven-3.1.1" >> .bashrc
echo "GRAILS_HOME=/home/$user/grails/grails-2.3.1" >> .bashrc
echo "MAVEN_REPO=/home/$user/.m2" >> .bashrc
echo "GRADLE_HOME=/home/$user/grails/gradle-1.8" >> .bashrc

echo "PATH=\$PATH:\$HOME/.local/bin:\$HOME/bin:\$GRAILS_HOME/bin:\$M2_HOME/bin:\$JAVA_HOME/bin:\$GRADLE_HOME/bin" >> .bashrc

echo "export JAVA_HOME" >> .bashrc 
echo "export M2_HOME" >> .bashrc 
echo "export GRAILS_HOME" >> .bashrc 
echo "export MAVEN_REPO" >> .bashrc 
echo "export GRADLE_HOME" >> .bashrc 
echo "export PATH" >> .bashrc

###last update & upgrade
apt-get update && apt-get upgrade -y

echo "please reboot and test grails & java"

