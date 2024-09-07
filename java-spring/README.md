## Getting started with development

```
./mvnw clean install
./mvnw spring-boot:run
```

```
open http://localhost:8080/info
```

## Building and packaging for production

```
./mvnw clean package
java -jar ./target/simple-api-demo-0.0.1-SNAPSHOT.jar
```
