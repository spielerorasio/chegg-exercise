# Chegg Student Questions

This exercise requires docker installed

To run this exercise do the following


1. git pull this repository and cd into the chegg-exercise 
2. cd chegg-exercise
3. mvn clean install -DskipTests dockerfile:build 
4. go to docker-compse file and set OCR API KEY under GOOGLE_APPLICATION_API_KEY
5. docker-compose up and wait till it is up
6. Go to http://localhost:8080/ 



# Supported API

    GET http://localhost:8080/rest/find/source/<sourceType>
    GET http://localhost:8080/rest/find/source/<sourceType>?page=<page>&size=<size>

For example
 
    GET http://localhost:8080/rest/find/source/csv
    GET http://localhost:8080/rest/find/source/csv?page=0&size=10

Full text search


GET http://localhost:8080/rest/find/text/<text>
GET http://localhost:8080/rest/find/text/<text>?page=<page>&size=<size>


For example

    GET http://localhost:8080/rest/find/text/chemistry


Combine

    GET http://localhost:8080/rest/find/source/<sourceType>/text/<text>?page=<page>&size=<size>
    
    
    
Any Questions 

    Orasio Spieler 0545900855 spieler.orasio@gmail.com 