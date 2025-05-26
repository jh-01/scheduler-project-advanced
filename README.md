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
| 번호 | 기능       | Method | URL                                                 | 요청 데이터           | 응답 데이터       |
|----|----------|--------|-----------------------------------------------------|------------------|--------------|
| 1  | 일정 생성    | POST   | `/api/schedules`                                    | 일정 등록 정보         | 생성된 일정 정보    |
| 2  | 전체 일정 조회 | GET    | `/api/schedules`                                    | 페이지 요청 정보        | 일정 목록        |
| 3  | 단일 일정 조회 | GET    | `/api/schedule/{id}`                                | 없음               | 일정 상세 정보     |
| 4  | 일정 수정    | PATCH  | `/api/schedule/{id}`                                | 수정할 일정 정보        | 수정된 일정 정보    |
| 5  | 일정 삭제    | DELETE | `/api/schedule/{id}`                                | 삭제할 일정 정보        | 성공 메시지       |
| 6  | 아이디 중복 확인 | GET    | `/api/members/exists`                               | 중복 조회할 아이디       | 사용 가능 여부 메시지 |
| 7  | 사용자 등록   | POST   | `/api/members`                                      | 사용자 등록 정보        | 등록된 사용자 정보   |
| 8  | 전체 사용자 조회 | GET    | `/api/members`                                      | 조회할 계정 생성일 기준 정보 | 사용자 정보       |
| 9  | 단일 사용자 조회 | GET    | `/api/members/{id}`                                 | 조회할 계정 아이디       | 사용자 정보       |
| 10 | 사용자 정보 수정 | PATCH  | `/api/members`                                      | 변경할 사용자 정보       | 수정된 사용자 정보   |
| 13 | 사용자 삭제   | DELETE | `/api/members`                                      | 삭제할 사용자 정보       | 삭제 완료 메시지    |
| 14 | 로그인      | POST   | `/session-login`                                    | 로그인할 사용자 정보      | 로그인 완료 메시지   |
| 15 | 로그아웃     | POST   | `/session-logout`                                   | 로그아웃할 사용자 정보     | 로그아웃 완료 메시지  |
| 15 | 홈        | POST   | `/session-home`                                     | 없음               | 로그아웃 완료 메시지  |
| 15 | 댓글 등록    | POST   | `/api/comments`                                     | 작성할 댓글 정보        | 작성된 댓글 정보    |
| 16 | 일정 댓글 조회 | GET    | `/api/schedules/{scheduleId}/comments`              | 조회할 댓글 정보        | 댓글 목록        |
| 16 | 유저 댓글 조회 | GET    | `/api/members/{memberId}/comments`                  | 조회할 댓글 정보        | 댓글 목록        |
| 17 | 댓글 수정    | PATCH   | `/api/comments/{commentId}`                         | 수정할 댓글 정보        | 수정된 댓글 정보    |
| 18 | 댓글 삭제    | DELETE   | `/api/comments/{commentId}`                         | 삭제할 댓글 정보        | 삭제 완료 메시지    |
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
#### POST /api/schedules

#### 🔹 Request Body
```
{
    "title": "새 일정",
    "content": "일정 내용",
}
```

##### 🔹 Response Body
```
{
    "scheduleId": 1,
    "nickname": "닉네임",
    "title": "새 일정",
    "content": "일정 내용",
    "createDate": "2025-05-14T10:27:50",
    "updateDate": "2025-05-14T10:27:50"
}
```
<br>

---
### 2. 전체 일정 조회
#### GET /api/schedules
- 전체 일정 조회

#### 🔹 Request Params
| Parameter  | Required/Optional | Description | Data Type |
|------------|-------------------|-------------|-----------|
| memberId | Required          | 유저 고유번호     | Long      |


#### 🔹 Request Body
```
{
  "page": 1,
  "size": 10,
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
#### GET /api/schedules/{id}

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description | Data Type |
|-----------|-------------------|-------------|-----------|
| id        | Required          | 일정 고유번호     | Long      |


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
#### PUT /api/schedules/{id}

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description | Data Type |
|-----------|-------------------|-------------|-----------|
| id        | Required          | 일정 고유번호     | Long      |


#### 🔹 Request Body
```
{
    "title": "수정 제목",
    "content": "수정 내용"
}
```

#### 🔹 Response Body
```
{
    "id": 8,
    "title": "수정 제목",
    "content": "수정 내용",
    "nickname": "b",
    "createAt": "2025-05-26T13:13:11.060703",
    "updatedAt": "2025-05-26T13:13:11.060703"
}
```
<br>

---
### 5. 선택 일정 삭제
- 선택 일정 삭제
#### DELETE /api/schedules

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description | Data Type |
|-----------|-------------------|-------------|-----------|
| id        | Required          | 일정 고유번호     | Long      |


#### 🔹 Response Body
```
일정 삭제가 완료되었습니다.
```
<br>

---
### 6. 아이디 중복 확인
- 아이디 중복 확인
#### GET /api/users/exists/{email}

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description | Data Type |
|-----------|-------------------|-------------|-----------|
| email    | Required          | 유저 이메일      | String    |


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

#### POST /api/members

#### 🔹 Request Body
```
{
    "email": "c@gmail.com",
    "nickname": "c",
    "password": "asdf1234!"
}
```

#### 🔹 Response Body
```
{
    "id": 8,
    "email": "c@gmail.com",
    "nickname": "c",
    "createAt": "2025-05-26T13:17:12.232777",
    "updatedAt": "2025-05-26T13:17:12.232777"
}
```

<br>

---
### 8. 전체 사용자 조회
- 전체 사용자 조회

#### GET /api/members

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description     | Data Type     |
|-----------|-------------------|-----------------|---------------|
| since     | Optional          | 계정 생성일          | LocalDateTime |
| until     | Optional          | 계정 생성일          | LocalDateTime |


#### 🔹 Response Body
```
[
    {
        "id": 1,
        "email": "asdsfsf@gmail.com",
        "nickname": "asdf",
        "createAt": "2025-05-21T13:49:54",
        "updatedAt": "2025-05-21T17:55:38.076847"
    },
    {
        "id": 2,
        "email": "dsfsdgdf",
        "nickname": "dfgdfgdfg",
        "createAt": "2025-05-21T15:17:17",
        "updatedAt": "2025-05-21T15:17:16"
    },
    {
        "id": 3,
        "email": "asdf@asfj",
        "nickname": "t",
        "createAt": "2025-05-21T17:52:55.743479",
        "updatedAt": "2025-05-21T17:52:55.743479"
    },
    {
        "id": 5,
        "email": "a@gmail.com",
        "nickname": "a",
        "createAt": "2025-05-25T02:56:30.864753",
        "updatedAt": "2025-05-25T02:56:30.864753"
    },
    {
        "id": 7,
        "email": "b@gmail.com",
        "nickname": "b",
        "createAt": "2025-05-25T15:08:23.028892",
        "updatedAt": "2025-05-25T15:39:23.553867"
    },
    {
        "id": 8,
        "email": "c@gmail.com",
        "nickname": "c",
        "createAt": "2025-05-26T13:17:12.232777",
        "updatedAt": "2025-05-26T13:17:12.232777"
    }
]
```

<br>

---
### 9. 단일 사용자 조회
- 단일 사용자 조회

#### GET /api/members/{id}

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description | Data Type |
|-----------|-------------------|-------------|-----------|
| id        | Required          | 유저 아이디      | Long      |


#### 🔹 Response Body
```
{
    "id": 7,
    "email": "b@gmail.com",
    "nickname": "b",
    "createAt": "2025-05-25T15:08:23.028892",
    "updatedAt": "2025-05-25T15:39:23.553867"
}
```

<br>

---

### 10. 사용자 이메일 수정
- 사용자 이메일 수정

#### PATCH /api/members

#### 🔹 Request Body
```
{
    "oldEmail": "b@gmail.com",
    "email": "d@gmail.com"
}
```

#### 🔹 Response Body
```
{
    "success": true,
    "data": {
        "id": 7,
        "email": "d@gmail.com",
        "nickname": "b",
        "createAt": "2025-05-25T15:08:23.028892",
        "updatedAt": "2025-05-25T15:39:23.553867"
    },
    "error": null,
    "message": "이메일 수정이 완료되었습니다."
}
```
<br>

---

### 11. 사용자 닉네임 수정
- 사용자 닉네임 수정

#### PUT /api/members

#### 🔹 Request Body
```
{
    "nickname": "c",
    "oldNickname": "b"
}
```

#### 🔹 Response Body
```
{
    "success": true,
    "data": {
        "id": 7,
        "email": "d@gmail.com",
        "nickname": "c",
        "createAt": "2025-05-25T15:08:23.028892",
        "updatedAt": "2025-05-25T15:39:23.553867"
    },
    "error": null,
    "message": "닉네임 수정이 완료되었습니다."
}
```
<br>

---

### 12. 사용자 비밀번호 수정
- 사용자 비밀번호 수정

#### PUT /api/members

#### 🔹 Request Body
```
{
    "oldPassword": "qwer1234!",
    "password": "asdf1234!!"
}
```

#### 🔹 Response Body
```
{
    "success": true,
    "data": null,
    "error": null,
    "message": "비밀번호 수정이 완료되었습니다."
}
```
<br>

---

### 13. 사용자 삭제
- 사용자 삭제

#### DELETE /api/members

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

#### POST /session-login
#### 🔹 Request Body
```
{
"email": "이메일",
"password": "비밀번호"
}
```

#### 🔹 Response Body
```
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>

<body>
    <h1>Welcome Session Home!</h1>

    <p>안녕하세요, d@gmail.com님!</p>

    <!-- 로그아웃 버튼 -->
    <form action="/session-logout" method="post" style="margin-top: 10px;">
        <button type="submit">Logout</button>
    </form>

</body>

</html>
```
<br>

---

### 15. 로그아웃
- 사용자 로그아웃

#### POST /session-logout

#### 🔹 Response Body
```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Session Login</title>
</head>
<body>
<h2>Session Login</h2>
<form th:action="@{/session-login}" method="post">
    <div>
        <label for="email">email:</label>
        <input type="email" id="email" name="email" required>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <button type="submit">Login</button>
</form>

</body>
</html>
```
<br>

---

### 16. 댓글 등록
- 일정에 댓글 등록

#### POST /api/comments
#### 🔹 Request Body
```
{
    "memberId": 7,
    "scheduleId": 1,
    "content": "댓글"
```
#### 🔹 Response Body
```
{
    "id": 7,
    "memberId": 7,
    "scheduleId": 1,
    "content": "댓글",
    "createAt": "2025-05-26T06:15:30.6588029",
    "updatedAt": "2025-05-26T06:15:30.6588029"
}
```
<br>

---
### 17. 댓글 조회
- 일정의 댓글 목록 조회

#### GET /api/schedules/{scheduleId}/comments
#### 🔹 Path Parameter
| Parameter | Required/Optional | Description | Data Type |
|-----------|-------------------|-------------|-----------|
| scheduleId        | Required          | 일정 아이디      | Long      |

#### 🔹 Response Body
```
[
    {
        "id": 1,
        "memberId": 7,
        "scheduleId": 1,
        "content": "댓글",
        "createAt": "2025-05-26T05:25:45.114685",
        "updatedAt": "2025-05-26T05:25:45.114685"
    },
    ...
]
```

<br>

### 17. 댓글 조회
- 사용자의 댓글 목록 조회

#### GET /api/members/{memberId}/comments
#### 🔹 Path Parameter
| Parameter | Required/Optional | Description | Data Type |
|-----------|-------------------|-------------|-----------|
| memberId        | Required          | 사용자 아이디     | Long      |

#### 🔹 Response Body
```
[
    {
        "id": 1,
        "memberId": 7,
        "scheduleId": 1,
        "content": "댓글",
        "createAt": "2025-05-26T05:25:45.114685",
        "updatedAt": "2025-05-26T05:25:45.114685"
    },
    ...
]
```

<br>

---
### 18. 댓글 수정
- 댓글 내용 수정

#### PATCH /api/comments/{commentId}
#### 🔹 Request Body
```
{
    "content": "수정내용"
}
```

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description | Data Type |
|-----------|-------------------|-------------|-----------|
| commentId        | Required          | 댓글 아이디      | Long      |


#### 🔹 Response Body
```
{
    "id": 3,
    "memberId": 7,
    "scheduleId": 1,
    "content": "수정내용",
    "createAt": "2025-05-26T05:34:05.758574",
    "updatedAt": "2025-05-26T07:07:15.857419"
}
```

<br>

---

### 19. 댓글 삭제
- 댓글 삭제

#### DELETE /api/comments/{commentId}
#### 🔹 Request Body
```
{
"userId": "작성자 ID"
}
```

#### 🔹 Path Parameter
| Parameter | Required/Optional | Description | Data Type |
|-----------|-------------------|-------------|-----------|
| commentId        | Required          | 댓글 아이디      | Long      |



#### 🔹 Response Body
```
{
"message": "댓글 삭제 완료"
}
```

---
## ERD
![Image](https://github.com/user-attachments/assets/b94a1909-030d-4e7b-9520-cda2bb0edb97)

