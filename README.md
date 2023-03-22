# 블로그 검색 서비스 API 명세 
 
 

# 🔗 블로그 검색하기

다음 블로그 서비스에서 질의어로 게시물을 검색합니다. 원하는 검색어와 함께 결과 형식 파라미터를 선택적으로 추가할 수 있습니다. 

기본적으로는 카카오 블로그에서 검색을 하고, 장애 발생 시 네이버 블로그에서 조회하여 결과를 반환합니다.

(*네이버 블로그 요청 시 장애가 발생하면 500 Internal Server Error를 반환합니다.)

### 기본 정보

```jsx
POST /blogs HTTP/1.1
Host: http://localhost:8080
```

## HTTP Request

### Example

```java
{
    "query" : "카카오뱅크",
    "page":1,
    "size":10,
    "sort":"ACCURACY"
}
```

 <table>
    <tr>
      <td>Name</td>
      <td>Type</td>
      <td>Description</td>
      <td>Required</td>
    </tr>
    <tr>
      <td>query</td>
      <td>String</td>
      <td>검색을 원하는 질의어 </br>
        특정 블로그 글만 검색을 하고 싶은 경우, 블로그 url과 검색어를 공백(’ ’) 구분자로 넣을 수 있음 </br>
        *값이 없거나, 공백(’ ‘)만 입력할 경우에는 400 Bad Request를 반환</td>
      <td>O</td>
    </tr>
    <tr>
      <td>sort</td>
      <td>String</td>
      <td>결과 문서 정렬 방식 </br>
        ACCURACY(정확도순), RECENCY(최신순) </br>
        기본 값 ACCURACY</td>
      <td>X</td>
    </tr>
    <tr>
      <td>page</td>
      <td>Integer</td>
      <td>결과 페이지 번호 </br>
        1~50 사이의 값 </br>
        기본 값 1</td>
      <td>X</td>
    </tr>
    <tr>
      <td>size</td>
      <td>Integer</td>
      <td>한 페이지에 보여질 문서 수 </br>
        1~50 사이의 값 </br>
        기본 값 10</td>
      <td>X</td>
    </tr>
  </table>

## HTTP Response

### Example

```bash
{
    "resultCode": "OK",
    "result": {
        "blogs": [
            {
                "title": "<b>카카오</b><b>뱅크</b> 전월세보증금 총정리",
                "content": "그랬던 것처럼 보증금 낼 돈이 500만 원이 전부라면 어떻게 해야 할까요. 5분만 아니 3분만 보시면 두 번째 집으로 이사 가실 수 있게 도와드리겠습니다. <b>카카오</b><b>뱅크</b> 전월세보증금 총정리 공인중개사 현직에서 일하다 보면 많은 분들이 <b>카카오</b><b>뱅크</b> 전월세보증금에 대해 문의하십니다. 잠깐 살펴보면 너무 단순하고 간편...",
                "postUrl": "http://hwshare07.com/48",
                "blogName": "하운드쉐어",
                "postDate": "2023-03-19"
            }
        ],
        "page": {
            "totalCount": 800,
            "size": 10,
            "page": 1,
            "totalPage": 50,
            "sort": "ACCURACY"
        }
    }
}
```

| Name | Type | Description |
| --- | --- | --- |
| resultCode | String | 결과 코드 |
| results.blogs[].title | String | 블로그 글 제목 |
| results.blogs[].contents | String | 블로그 글 요약 |
| results.blogs[].postUrl | String | 블로그 글 URL |
| results.blogs[].blogName | String | 블로그 이름 |
| results.blogs[].postDate | String | 블로그 글 작성 날짜 |
| page.totalCount | Integer | 노출 가능한 전체 문서 수 |
| page.size | Integer | 노출 가능한 문서 수 |
| page.page | Integer | 결과 페이지 번호 |
| page.totalPage | Integer | 검색 가능한 전체 페이지 |
| page.sort | String | 결과 문서 정렬 방식 |

---

# 🔗 인기 키워드 조회하기

블로그 검색 횟수가 많은 질의어를 상위 10개만 반환합니다.

(저장되어있는 질의어가 10개 이하일 경우에는 10개 이하로 결과가 반환됩니다.)

### 기본 정보

```bash
GET /popular-keywords HTTP/1.1
Host: http://localhost:8080
```

## HTTP Response

### Example

```bash
{
    "resultCode": "OK",
    "result": [
        {
            "keyword": "사과",
            "count": 10
        },
        {
            "keyword": "바나나",
            "count": 9
        },
        {
            "keyword": "포도",
            "count": 8
        },
        {
            "keyword": "레몬",
            "count": 7
        },
		...
    ]
}
```

| Name | Type | Description |
| --- | --- | --- |
| resultCode | String | 결과 코드 |
| results[].keyword | String | 검색 된 질의어 |
| results[].count | Integer | 질의어를 검색한 횟수 |

---

# 공통 에러 코드

| 에러 코드 | 설명 | HTTP Status |
| --- | --- | --- |
| INVALID_PARAMETER | 올바르지 않는 요청 데이터 에러 | 400 Bad Request. |
| REQUEST_BODY_MISSING_ERROR | 누락되거나 올바르지 않는 데이터 요청 에러 | 400 Bad Request. |
| POPULAR_KEYWORD_NOT_FOUND | 존재하지 않는 인기키워드 요청 에러 | 404 Not Found. |
| SEARCH_TYPE_NOT_FOUND | 존재하지 않는 외부 검색 요청 타입 요청 에러 | 404 Not Found. |
| EXTERNAL_REQUEST_FAILED | 외부 요청 처리 실패 에러 | 500 Internal Server Error. |
| INTERNAL_SERVER_ERROR | 내부 서버 시스템 에러 | 500 Internal Server Error. |

---

# 외부 라이브러리

<table>
    <tr>
      <td>사용한 모듈 명</td>
      <td>라이브러리 및 설명</td>
    </tr>
    <tr>
      <td>module-api</td>
      <td>implementation 'org.springframework.boot:spring-boot-starter-validation' </br>
        - @NotBlank와 같은 valid annotation을 사용하기 위해 추가하였습니다.</td>
    </tr>
    <tr>
      <td>module-api, module-client, module-domain</td>
      <td>compileOnly 'org.projectlombok:lombok' </br>
        annotationProcessor 'org.projectlombok:lombok' </br>
        - Lombok을 이용하면 Getter, Setter, ToString, hashCode 등의 메소드들을 간편하게 사용하기 위해 추가하였습니다.</td>
    </tr>
    <tr>
      <td>module-client</td>
      <td>implementation 'org.apache.httpcomponents:httpclient' </br>
        - RestTemplate 사용 시 readTimeout, connectTimeout 설정을 위해 추가하였습니다.</td>
    </tr>
    <tr>
      <td>module-domain</td>
      <td>implementation 'org.springframework.boot:spring-boot-starter-data-jpa' </br>
        - Java에서 ORM(Object-Relational Mapping) 기술 표준으로 사용하는 인터페이스 모음을 사용하기 위해 추가하였습니다.</td>
    </tr>
    <tr>
      <td>module-domain</td>
      <td>runtimeOnly 'com.h2database:h2' </br>
        - h2 커넥션을 위해 추가하였습니다.</td>
    </tr>
  </table>
