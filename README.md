# AGGREGATE Service

## Description

Takes time series data from a REST API endpoint provided and aggregate the provided data from minute values into 10
minute, 30 minute, and 1 hour averaged values based on an aggregate time period specified in the request. The data set
can be accessed here:

https://reference.intellisense.io/test.dataprovider

**API:**

HTTP Methos: POST

URL: http://localhost:8080/v1/aggregate

sampleRequest:   {"period":30}

## Ownership

This project owned by Kaviya Team (Email - kavythangaraj@gmail.com)

## Tech Stack

- Spring Boot
- maven

## Quick Start

- Clone the git repository
- Import maven project in Intelli-J
- mvn clean install
- docker build --tag=aggregate .
- docker run -p8080:8080 aggregate
- Application starts in 8080 port

## swagger link

url: http://localhost:8080/swagger-ui.html



 