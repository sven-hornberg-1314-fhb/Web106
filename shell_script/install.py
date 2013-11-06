#!/usr/bin/env python

import os

#download directory
download_dir = os.path.isdir('download') and os.path.exists('download')
if download_dir != 1:
	os.mkdir('download')
else: 
	os.system("rm -rf download")
	os.mkdir('download')
os.chdir('download')

#tomcat
tomcat = os.system("wget http://mirror.softaculous.com/apache/tomcat/tomcat-6/v6.0.37/src/apache-tomcat-6.0.37-src.tar.gz")
print(('tomcat download failed', 'tomcat downloaded')[tomcat == 0])
os.system("tar -xvf apache-tomcat-6.0.37-src.tar.gz")

#git
os.system("sudo apt-get install git")

#grails
grailss = os.system("wget http://dist.springframework.org.s3.amazonaws.com/release/GRAILS/grails-2.3.1.zip ")
os.system('unzip grails-2.3.1.zip')
