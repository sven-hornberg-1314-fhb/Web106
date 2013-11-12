#run this shell file with sudo python ubuntu1204_dev.py
import os

#set git email & password & name
gitname = ""
gitemail = ""
gitpass = ""

print ('installing git')
os.system("apt-get install git -y")

os.system("cd ~/")
os.system("mkdir grails")
os.system("cd grails")
os.system("wget http://dist.springframework.org.s3.amazonaws.com/release/GRAILS/grails-2.3.1.zip")


#JAVA_HOME=/usr/lib/jvm/java-7-oracle
#M2_HOME="~/grails/apache-maven-3.1.1"

#GRAILS_HOME=/home/devnull/grails/grails-2.3.1

#MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=256m"

#MAVEN_REPO="~/.m2"

#GRADLE_HOME="~/gradle-1.8"

#PATH=$PATH:$HOME/.local/bin:$HOME/bin:$GRAILS_HOME/bin:M2_HOME:$JAVA_HOME/bin:$GRADLE_HOME/bin

#export PATH
#export JAVA_HOME
#export GRAILS_HOME
#export M2_HOME
#export GRADLE_HOME
#export MAVEN_REPO
#export MAVEN_OPTS
