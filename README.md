# umbrella-app-server

# docker build 과정

- jar 파일 빌드
```
./mvnw package && java -jar target/gs-spring-boot-docker-0.1.0.jar
```

- docker 빌드
```
docker build -t springio/gs-spring-boot-docker .
```

- docker run
```
docker run -p 808:8081 springio/gs-spring-boot-docker
```


