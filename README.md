# hCode by Hyalen

Hello and Welcome to an exciting system of Algorithms Control where I'm developing an 
end-to-end full stack algorithm app similar to LeetCode.

I’ve built the backend server using Spring Boot where I used Spring Security along 
with JWT authentication. The app configuration model was developed using Spring Cloud.
For storage I'm using MySQL database.

The front-end application was built using React. I've also used React Bootstrap for 
designing the user interface.

The application was totally developed from scratch and you can get all the source code
hosted on GitHub as reference for a complete full stack application.

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
