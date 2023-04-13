# supervisor-api
This API expose endpoints to read (GET) a list of supervisor and submit(POST) a new supervisor info. 
## How to Run It
Option 1 - Docker - Clone the repository and go to the directory that contains the Dockefile: supervisors-api
```bash
1. Create the image: docker build -t supervisors-api:supervisors-api-001 .
2. Run the container: docker run -d -p 8080:8080 -name supervisors-api supervisors-api-001:latest
3. Verify that the container was created: docker container ps
4. To consume the GET endpoint navigate to Postman (Create a GET method) / Browser and type http://localhost:8080/api/supervisors
5. To consume the POST endpoint navigate to Postman (Create a POST method) and in the Body params choose:
   raw / JSON with test values. An example could be:
   {
    "firstName":"First Name",
    "lastName":"My Last name",
    "email":"qwqwqwq@jjj.com",
    "phoneNumber":"1212121",
    "supervisor":"any"
}
```
Option 2 - Plane JVM Command - Clone the repository and go to the directory supervisors-api
```bash
1. In any machine that has JVM with JDK 1.8
2. Under the directory supervisors-api it will appear a JAR file with name: supervisors-api-0.0.1-SNAPSHOT.jar
3. Run the application using: java -jar supervisors-api-0.0.1-SNAPSHOT.jar
```

