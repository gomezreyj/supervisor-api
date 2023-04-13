# supervisor-api
This API expose endpoints to read (GET) a list of supervisor and submit(POST) a new supervisor info. 
 
## How to Run It
Option 1 - Docker
```bash
1. Clone the repository https://github.com/gomezreyj/supervisor-api.git  ( master branch )
2. Checkout the master branch: git checkout master 
3. Go to the directory supervisors-api. This should contains the Dockefile
4. Create the image: docker build -t supervisors-api:supervisors-api-001 .
5. Run the container: docker run -d -p 8080:8080 -name supervisors-api supervisors-api-001:latest
6. Verify that the container was created: docker container ps
7. To consume the GET endpoint navigate to Postman (Create a GET method) / Browser and type http://localhost:8080/api/supervisors
8. To consume the POST endpoint navigate to Postman (Create a POST method) and in the Body params choose:
   raw / JSON with test values. An example could be:
   {
    "firstName":"First Name",
    "lastName":"My Last name",
    "email":"qwqwqwq@jjj.com",
    "phoneNumber":"1212121",
    "supervisor":"any"
}
9. Do not forget to view the console output
```
Option 2 - Plane JVM Command - Clone the repository and go to the directory supervisors-api
```bash
1. Clone the repository https://github.com/gomezreyj/supervisor-api.git  ( master branch )
2. Checkout the master branch: git checkout master 
3. Go to the directory supervisors-api. 
4. In any machine that has JVM with JDK 1.8
5. Under the directory supervisors-api it will appear a JAR file with name: supervisors-api-0.0.1-SNAPSHOT.jar
6. Run the application using: java -jar supervisors-api-0.0.1-SNAPSHOT.jar
7. Do not forget to view the console output
```

