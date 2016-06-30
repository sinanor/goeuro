To Run app with fat.jar, "application.properties" file is required. That file
contains parameters for the applications.

app.url=http://api.goeuro.com/api/v2/position/suggest/en/
app.exportFileName=goeruoexp.csv
app.exportCSVSeperator=;

To run App 

java -jar gs-consuming-rest-0.1.0.jar "berlin" 

Above command will call a rest service and store the data to csv file which is set in application.properties.


System requirements for build and run from source code.

This application built and tested with Gradle 2.13, Java 8, Ubuntu 14.04 LTS, Spring boot, open csv.

 
To Run Application with gradle, go to project root directory and run below command.

gradle bootRun -Pargs="berlin"

To Run tests;

gradle test

Generating fat jar(with all dependenceies) , go to project root directory and

./gradlew build

Above command will generate a fat jar to "build/libs/gs-consuming-rest-0.1.0.jar" directory.

Eclipse with Gradle plugin can be used for IDE development Environment











