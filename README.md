# Overview
This is server program for JAX-WS MTOM file transfer on servlet container.   

# Tested configuration
- CentOS release 6.6 (Final)
- jdk1.7.0_80 (from Oracle, 64bit)
- Apache Maven 3.3.9
- Apache Tomcat 7.0.69 or 8.0.33 (tar.gz)  
    Also I tried, JBoss EAP 6.1. But the problem did not occur on the EAP.
- Apache HTTP Server 2.2.15-39.el6.centos and mod_proxy_ajp that bundled.  
    Also I tried, Apache HTTP Server 2.2.31 and mod_jk 1.2.41 built from source code.  
    Problem occurred with these versions too.

# Setup
1. Build
```
$ mvn compile war:war
```
2. Deploy to tomcat. (or other container.)
```
$ cp target/jaxws-mtom-sample-server.war $CATALINA_HOME/webapps/
```
3. If your container is not running, run your container.  
ex.
```
$ startup.sh
```
 - NOTE: When clients upload files, those files are stored in below. 
    ./UploadedFiles/yyyyMMdd-HHmmss/

4. Run client  
see [jaxws-mtom-sample-client](https://github.com/ooura/jaxws-mtom-sample-client/)  

# Disclaimer
This program was created in order to reproduce and debug some of the problems of my application.  
So this is not intended to be used in a production environment.  
For any damage due to the use of this program , I will not be responsible.  
