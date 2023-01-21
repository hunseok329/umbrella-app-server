# umbrella-app-server

# 환경 구성

> Java : 11  
> Maven : 2.7.2

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
docker run -p 8080:8081 springio/gs-spring-boot-docker
```


