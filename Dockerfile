FROM openjdk:11
ADD target/contacts-parser.jar contacts-parser.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/contacts-parser.jar"]
