#!/bin/bash

# Declare and assign values to variables
app_name="application-0.0.1-SNAPSHOT"

# Build the Spring Boot application with Maven
mvn clean install

# Run the Spring Boot application
java -jar target/"$app_name".jar
