# Contacts regex parser [![Build Status](https://travis-ci.com/Artificial-Friend/rest-contact-parser.svg?branch=master)](https://travis-ci.com/Artificial-Friend/rest-contact-parser)

## Description

In this application you need to connect to a PostgreSQL database and pass a string regex filter by which you could get any matching contact.
Tests completed in internal H2 database with 5 hardcoded contact entities and has no influence on production database.

***

## Running the Application
1. Download and Install [JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Database:
  - Download [PostgreSQL](https://www.postgresql.org/download/)
  - In application file `application.properties` fill `user` and `password` fields. Create database `db` or change `spring.datasource.url` to your PostgreSQL database.
3. Run application;
4. Send GET requests with parameter `nameFilter` and any regex value by which you need to filter contacts names.

### OR

1.  Install [Docker Desktop](https://docs.docker.com/desktop/)
2.  Run following commands in terminal from rest-contact-parser directory: 
  > mvn clean package

  > docker build ./ -t application

3. Database:
  - Download [PostgreSQL](https://www.postgresql.org/download/)
  - In application file `application.properties` fill `user` and `password` fields. Create database `db` or change `spring.datasource.url` to your PostgreSQL database.

4. Run following command:

  > docker-compose up --build

5. Send GET requests with parameter `nameFilter` and any regex value by which you need to filter contacts names.

***
#### Author: Monchakivskyi Oleksii
