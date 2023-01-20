# 에브리타임 클론 코딩 - 서버

본 프로젝트는 <b>2023 DEU Winter Hack 경진대회</b>에서 진행한 프로젝트입니다.

## 프로젝트 소개
![everytime-image](https://user-images.githubusercontent.com/23499675/213755696-5bbf25b1-7bbc-4bc0-b193-416aef66a3c9.png)<br>
해당 프로젝트는 에브리타임을 클론 코딩한 프로젝트입니다. <br>
본 레포는 서버를 담당하는 레포입니다. <br>


## 개발 환경
- oracle JDK 17
- Spring boot 3.0.1
- MairaDB
- IntelliJ IDEA

## 설치
### JDK 17 설치
- [JDK 17 다운로드](https://www.oracle.com/java/technologies/downloads/)

### MariaDB 설치
- [MariaDB 다운로드](https://mariadb.org/download/)

## 프로젝트 다운로드
```bash
$ git clone https://github.com/Caste-Untouchable/everytime-server.git
```

### MariaDB 설정
1. MariaDB 설치

### application.properties 설정
```properties
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://127.0.0.1:3306/everytime
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=create
jwt.secret= {input your secret key}
```

## 빌드
```bash
$ cd everytime-server
$ ./gradlew build
```

## 실행
```
$ java -jar build/libs/everytime-server-0.0.1-SNAPSHOT.jar
```
