# A HOME TO SHARE v2 installation guildline
This installation guildline is basd on Windows platform. Operations may vary for other platform but the basic idea should be same.


# Install Java 8
Get Java 8 from Oracle and install it from
https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html

# Java 8 environment configuration
Open environment Variable in System Properties, Add the path to your java/bin of which you just download to SystemVariable/PATH and save. 
Follow the link below and go to "Updating the PATH Environment Variable" to find out more if you have any more problems
https://docs.oracle.com/javase/8/docs/technotes/guides/install/windows_jdk_install.html

# Install Gradle
Get Gradle 4.10.2 from Gralde Offical
https://gradle.org/next-steps/?version=4.10.2&format=all

# Configure Gradle environment
Open environment Variable in System Properties, Add the path to your gradle/bin of which you just download to SystemVariable/PATH and save. 

# Install MySQL8.0
Go to the link below to download MySQL8 and install it, be sure to remember you username and password
https://dev.mysql.com/doc/relnotes/mysql/8.0/en/

# Configure AHomeToShare
Go to ahometoshare_v2/src/main/resources/application.properties
modify 
spring.datasource.username and spring.datasource.password to whatever you set for your MySQL.
You may also modify 
globalsettings.admin-email-address, globalsettings.email-address and globalsettings.email-password to your own email box.

# Start A HOME TO SHARE
Go to ahometoshare_v2 home root directory and open a CMD. 
Type Gradle bootRun
When you see words like "Tomcat started on port(s): 8088 (http) with context path", it means your application is started

Go to http://localhost:8088/ to verify it.
