#run this shell file with sudo python ubuntu1204_dev.py
import os

#set git email & password & name
gitname = ""
gitemail = ""
gitpass = ""

print ('installing git')

os.system("apt-get install git -y")