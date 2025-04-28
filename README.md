# back

## 📚 프로젝트 개요
신세계 I&C 스파로스 아카데미 6기
* [스타벅스 앱](https://play.google.com/store/apps/details?id=com.starbucks.co&hl=ko&pli=1)의 
온라인 스토어 리빌딩 프로젝트 입니다.
* 단순 기능 복제가 아닌, DDD + Layered Architecture 기반의 체계적 설계와 대용량 데이터 처리 경험을 통해, 
MSA 및 CQRS 도입 필요성을 학습하는 것을 목표로 진행되었습니다.
* [데이터](https://github.com/4-javachip/data)의 경우 SSG.com에서 50,000건 가량의 데이터를 크롤링하여 진행하였습니다.
* 프로젝트 전체의 readme를 원하시면 [이곳](https://github.com/4-javachip)으로 이동하세요.

### 🛠️ 주요 기능
- 회원가입/로그인/소셜 로그인
- 상품/상품옵션/이벤트/기획전
- 베스트 상품
- 주문/결제
- 최근 본 상품
- 댓글
- 배송지 관리
- 찜

## 📦 Requirements

### ⚒ Languages & Platforms
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=SpringBoot&logoColor=white"> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat-square&logo=SpringSecurity&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=flat-square&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/redis-FF4438?style=flat-square&logo=redis&logoColor=white"> <img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=python&logoColor=white">
<br/>
<img src="https://img.shields.io/badge/Amazon Ec2-FF9900?style=flat-square&logoColor=white"> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logoColor=white"> <img src="https://img.shields.io/badge/Github Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white"> <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/NginX-009639?style=flat-square&logo=nginx&logoColor=white"> <img src="https://img.shields.io/badge/Cloud Flare DNS-F38020?style=flat-square&logo=cloudflare&logoColor=white">
<br/>

### ⚒ Tools
<img src="https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=flat-square&logo=IntelliJ%20IDEA&logoColor=white"> <img src="https://img.shields.io/badge/Git-F05032?&style=flat-square&logo=Git&logoColor=white"/> <img src="https://img.shields.io/badge/Postman-FF6C37?&style=flat-square&logo=Postman&logoColor=white"/>
<img src="https://img.shields.io/badge/swagger-85EA2D?&style=flat-square&logo=swagger&logoColor=white"/>
<img src="https://img.shields.io/badge/Discord-5865F2?style=flat-square&logo=Discord&logoColor=white"/> 

### System Spec

| * | Specification                        |
|:------:|:-------------------------------------|
| WAS | Spring Boot 3.4.3                    |
| DB | Mysql 9.2.0                          |
| Cache | Redis 7.4.2 |
| 언어 | Java (JDK 17)        |
| 빌드 도구 | Gradle 8.13 |
| IDE | Intellij IDEA                        |
| 형상관리 | GIT                                  |

### 🚀 기술 선정 이유
**Spring Boot**
* 기업용 백엔드 기준으로 널리 채택되어 있고, Spring Security 및 차후 MSA 확장성(Spring cloud) 등을 고려하여 선정
* 최신 LTS 버전인 Spring Boot 3.4.3 선정

**Redis**
* 인증, 최근 본 상품, 쿠폰 등에서 사용
* 인증 데이터의 빠른 접근, 만료 시간 설정, 쿠폰의 동시성 처리 기능 등을 편하게 사용하기 위해 사용

**Docker/Github Actions**
* Docker를 활용해 개발/운영 환경 통일
* Github Actions와 Discord Webhook을 연동하여 자동 빌드, 테스트, 배포, 결과 알림까지 완전 자동화된 CI/CD 파이프라인 구축

**MySQL**
* 복잡한 비즈니스 데이터 모델링과 무결성 관리를 위한 RDB + Spring Boot와의 호환성을 고려하여 선정
* 단, 차후 MSA 학습을 위한 용도로 각 도메인간의 relation은 최대한 끊는 방향으로 프로젝트 진행

### System Architecture
![image](https://github.com/user-attachments/assets/34f415f9-ebb3-49c6-b998-c2983cfaef3f)

### CI/CD
![image](https://github.com/user-attachments/assets/1a3fdb13-a45f-4e42-8057-c068734f7105)

### ERD
![image](https://github.com/user-attachments/assets/4910721b-e95f-440c-b509-f539b4ed14f4)


## Distributing Roles
### 👨‍💻 Back-end
* 팀장 김민조: Infra, CI/CD, OAuth/Auth/User, 약관, 최근 본 상품, 쿠폰 기능 담당
* 팀원 조현재: 상품(카테고리/기획전/이벤트/베스트), 키워드 검색, 페이징 처리(QueryDSL, Cusor, Page), 댓글, 찜 기능 담당
* 팀원 정동섭: 주문/결제(toss payments 연동)입

### 🗂️ 폴더 구조

```bash
src
└── com.starbucks.back          # domain 기반 DDD 구조
    └── agreement               # 약관 동의 
        ├── application             # service layer
        ├── domain                  # domain
        ├── dto                     # dto      
        ├── infrastructure          # repository layer
        ├── presentation            # controller layer
        └── vo                      # vo
    ├── auth                    # 인증
    ├── banner                  # 상품 메인 배너
    ├── best                    # 베스트 상품
    ├── cart                    # 장바구니
    ├── category                # 카테고리
    ├── common                  # 공통 (jwt, base entity, base exception, ...)
    ├── coupon                  # 쿠폰
    ├── dummy                   # 더미 데이터
    ├── event                   # 이벤트 기획전
    ├── oauth                   # 소셜 로그인
    ├── option                  # 상품 옵션
    ├── order                   # 주문
    ├── payment                 # 결제
    ├── product                 # 상품
    ├── recent                  # 최근 본 상품
    ├── review                  # 댓글
    ├── season                  # 시즌별 상품
    ├── shippingaddress         # 배송지
    ├── user                    # 유저
    ├── user_withdrwal_pending  # 회원 탈퇴
    └── wishlist                # 찜
```




## ⚙️ 프로젝트 실행 방법
백엔드 프로젝트를 실행하기 전 아래 항목들을 실행해주세요.
- MySQL, Redis, S3 연결을 위한 설정이 필요합니다.
- SMTP를 이용하기 위해, 구글 계정(이메일 전송자)을 준비해야합니다.
- toss-payments API를 이용하기 위해 [toss 개발자 문서](https://developers.tosspayments.com/)를 참고하여 
toss-payments secret-key, client-key를 발급받아야 합니다.

### 🔧 환경변수

<details>
<summary>application.yml</summary>

```
spring:
  datasource:
    url: {DATABASE_URL}
    username: {MYSQL_USERNAME}
    password: {MYSQL_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver

  mail:
    host: smtp.gmail.com
    port: {MAIL_PORT}
    username: {GOOGLE_EMAIL}
    password: {GOOGLE_PASSWORD}
    properties:
      mail.smtp.auth: true
      mail.smtp.starttls.enable: true

  data:
    redis:
      host: {BACK_HOST_URL}
      port: {REDIS_PORT}
      username: {REDIS_USERNAME}
      password: {REDIS_PASSWORD}


  output:
    ansi:
      enabled: always

  jpa:
    hibernate:
      ddl-auto: update
      format_sql: true
    show-sql: true

    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true

JWT:
  secret-key: {JWT_SECRET_KEY}
  token:
    access-expire-time: {JWT_ACCESS_EXPIRE_TIME}
    refresh-expire-time: {JWT_REFRESH_EXPIRE_TIME}

payment:
  secret-key: {TOSS_PAYMENTS_SECRET_KEY}
  client-key: {TOSS_PAYMENTS_CLIENT_KEY}
  base-url: https://api.tosspayments.com/v1
  success-url: {FRONT_SUCCESS_URL}
  fail-url: {FRONT_FAIL_URL}
  callback-url: {TOSS_BACK_WEBHOOK_URL}

cloud:
  aws:
    credentials:
      access-key: {AWS_ACCESS_KEY}
      secret-key: {AWS_SECRET_KEY}
    region:
      static: ap-northeast-2
    s3:
      bucket: {AWS_S3_BUCKET_NAME}
    stack:
      auto: false
```



| environment | description                                    |
|:-----------:|:-----------------------------------------------|
| {DATABASE_URL} | DB의 URL을 입력해 주세요                               |
| {MYSQL_USERNAME} | DB의 username을 입력해 주세요                          |
| {MYSQL_PASSWORD} | DB의 password를 입력해 주세요                          |
| {MAIL_PORT} | 메일 서버의 포트를 입력해 주세요                             |
| {GOOGLE_EMAIL} | 구글 메일 계정을 입력해 주세요                              |
| {GOOGLE_PASSWORD} | 구글 메일 비밀번호를 입력해 주세요                            |
| {BACK_HOST_URL} | Redis 서버의 host URL을 입력해 주세요                    |
| {REDIS_PORT} | Redis 서버의 포트를 입력해 주세요                          |
| {REDIS_USERNAME} | Redis 서버의 username을 입력해 주세요                    |
| {REDIS_PASSWORD} | Redis 서버의 password를 입력해 주세요                    |
| {JWT_SECRET_KEY} | JWT 토큰 발급을 위한 secret key를 입력해 주세요              |
| {JWT_ACCESS_EXPIRE_TIME} | JWT Access Token 만료 시간을 입력해 주세요                |
| {JWT_REFRESH_EXPIRE_TIME} | JWT Refresh Token 만료 시간을 입력해 주세요               |
| {TOSS_PAYMENTS_SECRET_KEY} | Toss Payments의 secret key를 입력해 주세요             |
| {TOSS_PAYMENTS_CLIENT_KEY} | Toss Payments의 client key를 입력해 주세요             |
| {FRONT_SUCCESS_URL} | 결제 성공 후 이동할 프론트엔드 URL을 입력해 주세요                 |
| {FRONT_FAIL_URL} | 결제 실패 후 이동할 프론트엔드 URL을 입력해 주세요                 |
| {TOSS_BACK_WEBHOOK_URL} | Toss 가상계좌 결제 완료 시 호출될 백엔드 webhook URL을 입력해 주세요 |
| {AWS_ACCESS_KEY} | AWS 접근용 access key를 입력해 주세요                    |
| {AWS_SECRET_KEY} | AWS 접근용 secret key를 입력해 주세요                    |
| {AWS_S3_BUCKET_NAME} | AWS S3 버킷 이름을 입력해 주세요                          |          |

</details>

## 📦 Getting Started
1. 프로젝트 클론
```bash
git clone https://github.com/4-javachip/back.git
```
2. 프로젝트 디렉토리로 이동
```bash
cd back
```
3. 빌드 및 실행
Linux / Mac
```bash
./gradlew build
./gradlew bootRun
```
Window
```bash
gradlew.bat build
gradlew.bat bootRun
```

4. API 문서 확인
* 서버가 정상적으로 실행되면 Swagger UI를 통해 API를 확인할 수 있습니다.
* http://localhost:8080/swagger-ui/index.html








