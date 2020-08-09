#!/bin/sh
echo Update AMI
sudo yum -y install update

sudo yum -y install java-1.8.0-openjdk-src.x86_64
sudo yum -y remove java-1.7.0-openjdk
sudo mkdir /home/ec2-user/tomcat-8
sudo wget https://archive.apache.org/dist/tomcat/tomcat-8/v8.0.32/bin/apache-tomcat-8.0.32.tar.gz -P /home/ec2-user
sudo tar -xvf /home/ec2-user/apache-tomcat-8.0.32.tar.gz -C /home/ec2-user/tomcat-8 --strip-components=1
sudo rm /home/ec2-user/apache-tomcat-* -Rf

sudo chown ec2-user:ec2-user /home/ec2-user -R

echo =============================
echo Installed Java 8 and Tomcat 9
echo Please check directory /home/ec2-user/tomcat-8