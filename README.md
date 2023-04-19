# 📝게시판 만들기
## 💻 사용 기술 
### #Back-End
#### Core
* JAVA 11
* Spring MVC
* Spring Boot
* Spring Data JPA
* Spring Security
* Apache Tomcat 9.0

#### Build Tool
* Maven

#### Database
* Oracle

### #Front-End
* Javascript
* Thymeleaf
* BootStrap

## 💻 개발환경
* Github(Git bash)

-----------------------------------
## 주요 기능 소개
### version 1.0(초기 버전)

* [로그인/로그아웃/회원가입](https://github.com/MinChul-Son/My-Board/issues/1)
* [게시글 작성](https://github.com/MinChul-Son/My-Board/issues/2)
* [게시글 삭제/수정](https://github.com/MinChul-Son/My-Board/issues/4)
  - 자신의 게시글만 삭제/수정 가능
* [특정 게시물 조회, 게시글 목록 조회](https://github.com/MinChul-Son/My-Board/issues/3)
  - 카테고리별 조회
  - 내가 작성한 게시글 조회
* [게시글 검색](https://github.com/MinChul-Son/My-Board/issues/7)
  - 제목으로 검색
  - 작성자로 검색
  - 내용으로 검색 
* [조회수 기능](https://github.com/MinChul-Son/My-Board/issues/5)
* [페이징 기능](https://github.com/MinChul-Son/My-Board/issues/6)





----------------------------------
## 추가하면 좋을 기능 구상 중!(점진적으로 기능 추가 + 업데이트 예정)
* 추가적으로 관리자 페이지를 설정할 예정
  - 관리자는 회원의 게시물을 삭제 가능
  - 관리자는 회원 목록을 관리 가능
    + 추방, 조회

* 메시지, 국제화 기능 적용시킬 예정
* 게시물 검증 기능 추가 예정
  - 게시물 작성할 때 제목은 꼭 있어야하고, 내용은 몆 글자 이상 등등...

* 게시물에 추천을 누를 수 있도록 하여 일정 추천수 도달시 HOT 게시판으로 이동
* 예외처리도 추가해보자(이번에 공부한 `BasicErrorController`를 사용해서) ==> API통신을 추가하면 `@ExceptionHandler`을 사용하면됨.
* 추가 프로젝트로 **API 서버**로 변경시켜보자(나중에..)
  - 패키지 구조를 변경해야할 수도 있음. 아예 별도의 프로젝트로 생성하는 것이 좋을 듯 


----------------------------------
--------------------------------
# 날짜별 To-Do-List
### 6/20
* 프로젝트 생성
* 게시글 작성, DB에 저장 기능 추가

### 6/21 to-do-list
* `게시글 목록 조회` ~~예정~~ **완료**
* `게시글 리스트 UI 적용` ~~예정~~ **완료**
* `게시물 조회` 기능 추가 ~~예정~~ **완료**
* `게시물 삭제` 기능 추가 ~~예정~~ **완료**
* `게시물 수정` 기능 추가 ~~예정~~ **완료**

### 6/22 to-do-list
* `게시물 조회수` 기능 추가 ~~예정~~ **완료**
* `게시물 페이징` 기능 추가 ~~예정~~ **완료**
* `로그인/로그아웃, 회원가입` 기능 추가 예정 **내일 이어서 진행**
  - `Spring Security`를 사용
  - 기본으로 제공하는 login 페이지 사용 
  - 로그인 안한 사용자는 index페이지만 접속 가능하도록 구현 **완료**
  - 각 회원이 어떤 권한을 가지는지에 대한 Role 엔티티를 추가하고 Member와 다대일로 양방향 매핑 **완료**
* `Member` 엔티티 추가, `Post` 엔티티에서 `1:N` 관계로 `단방향 연관관계`로 매핑 ~~예정~~ **완료**
  - **누가 게시물을 작성했는지를 입력하는 것이 아니라 로그인한 회원의 정보를 가지고 값을 자동으로 넣을 것이기 때문에 Member 입장에서는 Post를 알 필요가 없다고 생각하여 단방향 연관관계**로 설정하였다. 추후에 만약 내 생각이 틀렸다고 판단되면 변경할 수 도 있는 부분임

----------------------------------

### 6/30 to-do-list
* `Querydsl`을 사용하여 `동적쿼리 기능` **완료!**
  - `BooleanExpression`을 사용하여 파라미터로 넘어온 값(category, mypost, searchDto, username)을 처리하고` where절에서 결과를 필터링`한다.
  - `Querydsl`을 사용하니 굉장히 편리하게 값을 필터링할 수 있는 것 같다.
  - 추가된 클래스
    + `QuerydslConfig` : `JPAQueryFactory`를 스프링 빈으로 등록
    + `BoardRepositoryCustom` : 커스텀 인터페이스
    + `BoardRepositoryImpl` : 커스텀 인터페이스 구현체)
* list.html에서 searchDto의 값들을 페이지를 이동할 때도 계속 가지고와 사용할 수 있도록 하였다.
  - 검색창에 검색을 한 상태에서 다음 페이지 버튼을 누르면 검색창으로 검색한 결과에 대한 다음 페이지로 이동 
* 현재 초기에 구상했던 게시판의 기능은 모두 구현을 했다.

-----------------------------------------------------------------
