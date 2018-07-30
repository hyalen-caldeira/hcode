# hCode by Hyalen

Hello and Welcome to an exciting system of _Algorithms Control_ where I'm developing an 
end-to-end full stack _Algorithm App_ similar to LeetCode.

I’ve built the backend server using **_Spring Boot_** where I used **_Spring Security_** along 
with **_JWT_** authentication. The app configuration model was developed using **_Spring Cloud_**.
For storage I'm using **_MySQL_** database.

The frontend application was built using **_React_**. I've also used **_React Bootstrap_** for 
designing the user interface.

The application was totally developed from scratch and you can get all the source code
hosted on **_GitHub_** as reference for a complete full stack application.

### Development Steps
##### Creating the Backend Application using Spring Boot
* 
*
##### Adding additional dependencies/libraries
* 
*
##### Configuring the Server, Database, Hibernate and Jackson
* 
*
##### Configuring Spring Boot to use Java 8 Date/Time converters and UTC Timezone
* 
*
##### Creating the domain models
* 
*
##### Creating the Repositories for accessing models data
* 
*
##### Configuration and Dependency Inject
* 
*

## Spring Cloud Config
#### Client Configuration
* Create the following entries in the bootstrap.properties file
  * spring.application.name=name_of_the_application - must match with the configuration file name in the Spring Cloud Config Server
  * spring.cloud.config.uri=http://host:port - address where the Spring Cloud Config server is running
#### Active Profiles
* In order to set up which profile (Production, Development, Test, etc) should be considered, each application should set up the environment variable SPRING_PROFILES_ACTIVE. If there are more than one profile it should be separated by comma 
  * Example: $ export SPRING_PROFILES_ACTIVE=QA, Development
  * The environment variable could obviously be created through of any shell script startup file
* The profiles can also be informed by command line
  * Example: $ java -jar -Dspring.profiles.active=production name_of_application.jar
