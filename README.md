# Contacts regex parser [![Build Status](https://travis-ci.com/Artificial-Friend/rest-contact-parser.svg?branch=master)](https://travis-ci.com/Artificial-Friend/rest-contact-parser)

## Description

In this application you need to connect to a PostgreSQL database and pass a string regex filter by which you could get any matching contact.
Tests completed in internal H2 database with 5 hardcoded contact entities and has no influence on production database.

***
## Running the Application
1. Download and Install [JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Database:  
  - Download PostgreSQL [Postgres](https://www.postgresql.org/download/) 
  - In application file `application.properties` fill `user` and `password` fields. Create database `db` or change `spring.datasource.url` to your PostgreSQL database.
5. Run application;
6. Send GET requests with parameter `nameFilter` and any regex value by which you need to filter contacts names.

***
#### Author: Monchakivskyi Oleksii
