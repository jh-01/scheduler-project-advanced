# 🗓️ Scheduler Project

Spring Boot 기반의 일정 관리 API 프로젝트입니다.  
사용자 계정 생성부터 일정 등록, 수정, 삭제까지 제공하여  
**개인 일정을 효율적으로 관리**할 수 있는 기능을 구현합니다.

<br>

---

## 📌 프로젝트 개요

- **백엔드 프레임워크**: Spring Boot
- **데이터베이스**: MySQL
- **ORM**: Spring JDBC (`JdbcTemplate`)
- **빌드 도구**: Gradle
- **검증 방식**: Bean Validation (`@Valid`, `@NotBlank`, `@Min` 등)
- **REST API 제공**

<br>

---

## ⚙️ 주요 기능

### 👤 사용자 기능
- 회원 가입 (`POST /api/users`)
- 사용자 정보 조회 및 수정
- 비밀번호 및 로그인 아이디 변경
- 아이디 중복 검사
- 사용자 삭제
- 로그인 기능

### 📅 일정 기능
- 일정 등록 / 수정 / 삭제
- 사용자별 전체 일정 조회
- 특정 일정 단건 조회
- 기간 필터 조회 (선택적)

### 📝 댓글 기능
- 댓글 등록 / 수정 / 삭제
- 일정 별 전체 댓글 조회(페이징 적용)
- 특정 댓글 단건 조회
- 기간 필터 조회 (선택적)

<br>

---

## 🔧 API 목록
| 번호 | 기능         | Method | URL                                                | 요청 데이터           | 응답 데이터       |
|----|------------|--------|----------------------------------------------------|------------------|--------------|
| 1  | 일정 생성      | POST   | `/api/schedule`                                    | 일정 등록 정보         | 생성된 일정 정보    |
| 2  | 전체 일정 조회   | GET    | `/api/schedule/all/{userId}`                       | 페이지 요청 정보        | 일정 목록        |
| 3  | 단일 일정 조회   | GET    | `/api/schedule/one/{scheduleId}`                   | 없음               | 일정 상세 정보     |
| 4  | 일정 수정      | PATCH  | `/api/schedule/modify/{scheduleId}`                | 수정할 일정 정보        | 수정된 일정 정보    |
| 5  | 일정 삭제      | DELETE | `/api/schedule/delete/{scheduleId}`                | 삭제할 일정 정보        | 성공 메시지       |
| 6  | 아이디 중복 확인  | GET    | `/api/users/isDuplicate`                           | 중복 조회할 아이디       | 사용 가능 여부 메시지 |
| 7  | 사용자 등록     | POST   | `/api/users`                                       | 사용자 등록 정보        | 등록된 사용자 정보   |
| 8  | 전체 사용자 조회  | GET    | `/api/users/all`                                   | 조회할 계정 생성일 기준 정보 | 사용자 정보       |
| 9  | 단일 사용자 조회  | GET    | `/api/users/one/{userId}`                          | 없음               | 사용자 정보       |
| 10 | 사용자 정보 수정  | PATCH  | `/api/users/{userId}/modify/info`                 | 수정할 사용자 정보       | 수정된 사용자 정보   |
| 11 | 비밀번호 변경    | PATCH  | `/api/users/{userId}/modify/password`                       | 변경할 비밀번호 정보      | 수정된 사용자 정보   |
| 12 | 로그인 아이디 변경 | PATCH  | `/api/users/{userId}/modify/loginId`                        | 변경할 로그인 아이디 정보   | 수정된 사용자 정보   |
| 13 | 사용자 삭제     | DELETE | `/api/users/delete`                                | 삭제할 사용자 정보       | 삭제 완료 메시지    |
| 14 | 로그인        | POST   | `/api/users/{userId}/login`                                 | 로그인할 사용자 정보      | 로그인 완료 메시지   |
| 15 | 로그아웃       | POST   | `/api/users/{userId}/logout`                                | 로그아웃할 사용자 정보     | 로그아웃 완료 메시지  |
| 15 | 댓글 등록      | POST   | `/api/schedule/{scheduleId}/comments`              | 작성할 댓글 정보        | 작성된 댓글 정보    |
| 16 | 댓글 조회      | GET    | `/api/schedule/{scheduleId}/comments`              | 조회할 댓글 정보        | 댓글 목록        |
| 17 | 댓글 수정      | PATCH   | `/api/schedule/{scheduleId}/comments/{commentsId}` | 수정할 댓글 정보        | 수정된 댓글 정보    |
| 18 | 댓글 삭제      | DELETE   | `/api/schedule/{scheduleId}/comments/{commentsId}` | 삭제할 댓글 정보        | 삭제 완료 메시지    |
<br>

---


<br>

## 🧪 유효성 검사

- `@Valid`를 통해 DTO에 대한 입력값 검증 수행
- 이메일, 비밀번호, 일정 제목 등 주요 필드는 `@NotBlank`, `@Email`, `@Pattern` 등의 어노테이션을 통해 무결성 보장

<br>

---

## 📂 프로젝트 구조

```bash
scheduler-project/

```

<br>

---

## 🗂 공통 사항
+ 모든 날짜 형식: YYYY-MM-DDTHH:MM:SS
+ 모든 인증 정보(예: 비밀번호)는 요청 Body에 포함

<br>

---

## 🏁 시작하기
```
git clone https://github.com/jh-01/scheduler-project.git
cd scheduler-project
./gradlew bootRun
```

<br>

--- 

## 👀 TODO
- [ ]  잘못된 요청(필수 파라미터 누락, 잘못된 데이터 타입 입력 등) 발생 시 적절한 예외 처리를 적용한 응답을 반환할 수 있는가?
- [ ]  Layered Architecture 개념과 각 계층의 역할을 설명하고, 실제 애플리케이션 설계에 적용할 수 있는가?
- [ ]  MySQL 설치 및 JDBC를 활용하여 SQL 구문 적용을 통해 데이터베이스에서 데이터를 삽입, 조회, 수정, 삭제할 수 있는가?
- [ ]  Spring 요청/응답 데이터 처리 실습을 통해 데이터 변환 및 검증을 효과적으로 수행할 수 있는가?
- [ ]  Spring Boot에서 CRUD API를 구현하고 Postman 또는 Swagger로 테스트할 수 있는가?
- [ ]  DI 와 IoC를 활용하여 객체 간 의존 관계를 설정 및 구현할 수 있는가?
- [ ]  Spring MVC에서 클라이언트 요청이 컨트롤러 메서드로 매핑되도록 @RequestMapping, @GetMapping/@PostMapping 을 활용했는가?
- [ ]  @RestController, @Service, @Repository 어노테이션을 통해 컴포넌트 스캔과 빈 등록이 자동으로 이루어지는 구조를 이해하고 이를 활용할 수 있는가?
- [ ]  Spring Framework의 설치 및 프로젝트를 생성, spring-boot-starter를 사용하여 Spring Boot 애플리케이션을 설정하고 실행할 수 있는가?

<br>

---

## 📌 API 명세 상세
### 1. 일정 생성
- 일정을 생성하는 메서드
#### POST /api/schedule

#### 🔹 Request Body
```
{
    "loginId": "로그인 아이디",
    "title": "새 일정",
    "contents": "일정 내용",
    "password": "로그인 비밀번호"
}
```

##### 🔹 Response Body
```
{
    "scheduleId": 1,
    "nickname": "닉네임",
    "title": "새 일정",
    "contents": "일정 내용",
    "createDate": "2025-05-14T10:27:50",
    "updateDate": "2025-05-14T10:27:50"
}
```
<br>

---
### 2. 전체 일정 조회
#### GET /api/schedule/all
- 전체 일정 조회

#### 🔹 Request Body
```
{
  "page": 1,
  "size": 10,
  "loginId": "t",
  "since": "2025-05-01T00:00:00",
  "until": "2025-05-12T23:59:59"
}
```

#### 🔹 Response Body
```
{
    "data": [
        {
            "scheduleId": 30,
            "nickname": "zxcv1234",
            "title": "새 일정",
            "contents": "일정 내용",
            "createDate": "2025-05-14T10:37:28",
            "updateDate": "2025-05-14T10:37:28"
        },
        {
            "scheduleId": 31,
            "nickname": "zxcv1234",
            "title": "새 일정",
            "contents": "일정 내용",
            "createDate": "2025-05-14T10:37:28",
            "updateDate": "2025-05-14T10:37:28"
        },
        {
            "scheduleId": 28,
            "nickname": "zxcv1234",
            "title": "새 일정",
            "contents": "일정 내용",
            "createDate": "2025-05-14T10:37:27",
            "updateDate": "2025-05-14T10:37:27"
        },
        {
            "scheduleId": 29,
            "nickname": "zxcv1234",
            "title": "새 일정",
            "contents": "일정 내용",
            "createDate": "2025-05-14T10:37:27",
            "updateDate": "2025-05-14T10:37:27"
        },
        {
            "scheduleId": 26,
            "nickname": "zxcv1234",
            "title": "새 일정",
            "contents": "일정 내용",
            "createDate": "2025-05-14T10:37:26",
            "updateDate": "2025-05-14T10:37:26"
        }
    ],
    "pageInfo": {
        "page": 1,
        "size": 5,
        "totalCount": 31,
        "hasPrev": false,
        "hasNext": true
    }
}
```
<br>

---
### 3. 선택 일정 조회
- 선택 일정 조회
#### GET /api/schedule/{scheduleId}

#### 🔹 Path Parameter
| Parameter  | Required/Optional | Description | Data Type |
|------------|-------------------|-------------|-----------|
| scheduleId | Required          | 일정 고유번호     | Int       |


#### 🔹 Response Body
```
{
    "scheduleId": 5,
    "nickname": "asdf1234",
    "title": "새 일정",
    "contents": "일정 내용",
    "createDate": "2025-05-14T10:36:56",
    "updateDate": "2025-05-14T10:36:56"
}
```
<br>

---
### 4. 선택 일정 수정
- 선택 일정 수정
#### PUT /api/schedule/modify/{scheduleId}

#### 🔹 Path Parameter
| Parameter  | Required/Optional | Description | Data Type |
|------------|-------------------|-------------|-----------|
| scheduleId | Required          | 일정 고유번호     | Int       |


#### 🔹 Request Body
```
{
    "loginId": "qwer1234",
    "password": "qwer1234!",
    "scheduleData":{
        "scheduleId": 12,
        "title": "수정 제목2",
        "contents": "수정 내용2"
    }
}
```

#### 🔹 Response Body
```
{
    "scheduleId": 12,
    "nickname": "qwer1234",
    "title": "수정 제목2",
    "contents": "수정 내용2",
    "createDate": "2025-05-14T10:37:08",
    "updateDate": "2025-05-14T10:40:02"
}
```
<br>

---
### 5. 선택 일정 삭제
- 선택 일정 삭제
#### DELETE /api/schedule/delete

#### 🔹 Request Body
```
{
    "loginId": "zxcv1234",
    "scheduleId": 31,
    "password" : "zxcv1234!"
}
```

#### 🔹 Response Body
```
{
    "available": true,
    "message": "일정 삭제를 완료했습니다."
}
```
<br>

---
### 6. 아이디 중복 확인
- 아이디 중복 확인
#### GET /api/users/isDuplicate/{loginId}

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description  | Data Type |
|-----------|-------------------|--------------|-----------|
| loginId    | Required          | 유저 아이디       | String    |


#### 🔹 Response Body
```
{
    "available": true,
    "message": "사용 가능 아이디입니다."
}
```

<br>

---
### 7. 사용자 추가
- 사용자 추가

#### POST /api/users

#### 🔹 Request Body
```
{
    "loginId": "사용자 아이디",
    "nickname": "닉네임",
    "email": "이메일",
    "password" : "비밀번호"
}
```

#### 🔹 Response Body
```
{
    "userId": 3,
    "loginId": "사용자 아이디",
    "email": "이메일",
    "nickname": "닉네임",
    "createDate": "2025-05-14T10:36:19",
    "updateDate": "2025-05-14T10:36:19"
}
```

<br>

---
### 8. 전체 사용자 조회
- 전체 사용자 조회

#### GET /api/users/all/{loginId}

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description     | Data Type     |
|-----------|-------------------|-----------------|---------------|
| since     | Optional          | 계정 생성일          | LocalDateTime |
| until     | Optional          | 계정 생성일          | LocalDateTime |


#### 🔹 Response Body
```
[
    {
        "userId": 3,
        "loginId": "zxcv1234",
        "email": "zxcv1234@gmail.com",
        "nickname": "zxcv1234",
        "createDate": "2025-05-14T10:36:19",
        "updateDate": "2025-05-14T10:36:19"
    },
    {
        "userId": 2,
        "loginId": "qwer1234",
        "email": "qwer1234@gmail.com",
        "nickname": "qwer1234",
        "createDate": "2025-05-14T10:36:08",
        "updateDate": "2025-05-14T10:36:08"
    },
    {
        "userId": 1,
        "loginId": "asdf1234",
        "email": "asdf1234@gmail.com",
        "nickname": "asdf1234",
        "createDate": "2025-05-14T10:35:42",
        "updateDate": "2025-05-14T10:35:42"
    }
]
```

<br>

---
### 9. 단일 사용자 조회
- 단일 사용자 조회

#### GET /api/users/one/{loginId}

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description | Data Type |
|-----------|-------------------|-------------|-----------|
| loginId    | Required          | 유저 아이디      | String    |


#### 🔹 Response Body
```
{
    "userId": 2,
    "loginId": "qwer1234",
    "email": "qwer1234@gmail.com",
    "nickname": "qwer1234",
    "createDate": "2025-05-14T10:36:08",
    "updateDate": "2025-05-14T10:36:08"
}
```

<br>

---

### 10. 사용자 정보 수정
- 사용자 정보 수정

#### PATCH /api/users/modify/info

#### 🔹 Request Body
```
{
    "loginId": "로그인 아이디",
    "nickname": "닉네임",
    "email": "이메일",
    "password": "비밀번호"
}
```

#### 🔹 Response Body
```
{
  "user_id": 1,
  "loginId": "사용자 아이디",
  "nickname": "닉네임",
  "email": "이메일",
  "createDate": "2025-05-07",
  "updateDate": "2025-05-07"
}
```
<br>

---

### 11. 사용자 아이디 수정
- 사용자 아이디 수정

#### PUT /api/users/modify/loginId

#### 🔹 Request Body
```
{
    "tempLoginId": "현재 로그인 아이디",
    "newLoginId": "새 로그인 아이디",
    "password": "비밀번호"
}
```

#### 🔹 Response Body
```
{
  "user_id": 1,
  "loginId": "사용자 아이디",
  "nickname": "닉네임",
  "email": "이메일",
  "createDate": "2025-05-07",
  "updateDate": "2025-05-07"
}
```
<br>

---

### 12. 사용자 비밀번호 수정
- 사용자 비밀번호 수정

#### PUT /api/users/modify/password

#### 🔹 Request Body
```
{
    "loginId": "닉네임",
    "tempPassword": "현재 비밀번호",
    "newPassword": "새 비밀번호"
}
```

#### 🔹 Response Body
```
{
  "message": "비밀번호가 성공적으로 수정되었습니다."
}
```
<br>

---

### 13. 사용자 삭제
- 사용자 삭제

#### DELETE /api/users/delete

#### 🔹 Request Body
```
{
    "loginId": "로그인 아이디",
    "password": "비밀번호"
}
```

#### 🔹 Response Body
```
{
    "message": "삭제 완료"
}
```
<br>

---
### 14. 로그인
- 사용자 로그인

#### POST /api/users/login
#### 🔹 Request Body
```
{
"loginId": "로그인 아이디",
"password": "비밀번호"
}
```

#### 🔹 Response Body
```
{
"message": "로그인 성공",
"token": "JWT 또는 세션 토큰"
}
```
<br>

---

### 15. 로그아웃
- 사용자 로그아웃

#### POST /api/users/logout
#### 🔹 Request Body
```
{
"userId": "로그아웃할 사용자 ID"
}
```

#### 🔹 Response Body
```
{
"message": "로그아웃 완료"
}
```
<br>

---

### 16. 댓글 등록
- 일정에 댓글 등록

#### POST /api/schedules/{scheduleId}/comments
#### 🔹 Request Body
```
{
"userId": "작성자 ID",
"content": "댓글 내용"
}
```
#### 🔹 Response Body
```
{
"commentId": 1,
"userId": "작성자 ID",
"content": "댓글 내용",
"createdAt": "2025-05-16T12:00:00Z"
}
```
<br>

---
### 17. 댓글 조회
- 일정의 댓글 목록 조회

#### GET /api/schedules/{scheduleId}/comments
#### 🔹 Request Params
없음

#### 🔹 Response Body
```
[
    {
    "commentId": 1,
    "userId": "작성자 ID",
    "content": "댓글 내용",
    "createdAt": "2025-05-16T12:00:00Z"
    },
    ...
]
```

<br>

---
### 18. 댓글 수정
- 댓글 내용 수정

#### PATCH /api/schedules/{scheduleId}/comments/{commentId}
#### 🔹 Request Body
```
{
"userId": "작성자 ID",
"content": "수정할 댓글 내용"
}
```

#### 🔹 Response Body
```
{
"commentId": 1,
"content": "수정된 댓글 내용",
"updatedAt": "2025-05-16T12:30:00Z"
}
```

<br>

---

### 19. 댓글 삭제
- 댓글 삭제

#### DELETE /api/schedules/{scheduleId}/comments/{commentId}
#### 🔹 Request Body
```
{
"userId": "작성자 ID"
}
```

#### 🔹 Response Body
```
{
"message": "댓글 삭제 완료"
}
```

---
## ERD
![Image](https://github.com/user-attachments/assets/b94a1909-030d-4e7b-9520-cda2bb0edb97)

