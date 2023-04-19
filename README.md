📝게시판 만들기
💻 사용 기술
#Back-End
Core
JAVA 11
Spring MVC
Spring Boot
Spring Data JPA
Spring Security
Querydsl
Build Tool
Gradle
Database
Mysql
#Front-End
Javascript
Thymeleaf
BootStrap
💻 개발환경
IntelliJ
Postman
Github(Git bash)
주요 기능 소개
version 1.0(초기 버전)
로그인/로그아웃/회원가입
게시글 작성
게시글 삭제/수정
자신의 게시글만 삭제/수정 가능
특정 게시물 조회, 게시글 목록 조회
카테고리별 조회
내가 작성한 게시글 조회
게시글 검색
제목으로 검색
작성자로 검색
내용으로 검색
조회수 기능
페이징 기능
추가하면 좋을 기능 구상 중!(점진적으로 기능 추가 + 업데이트 예정)
추가적으로 관리자 페이지를 설정할 예정

관리자는 회원의 게시물을 삭제 가능
관리자는 회원 목록을 관리 가능
추방, 조회
메시지, 국제화 기능 적용시킬 예정

게시물 검증 기능 추가 예정

게시물 작성할 때 제목은 꼭 있어야하고, 내용은 몆 글자 이상 등등...
게시물에 추천을 누를 수 있도록 하여 일정 추천수 도달시 HOT 게시판으로 이동

예외처리도 추가해보자(이번에 공부한 BasicErrorController를 사용해서) ==> API통신을 추가하면 @ExceptionHandler을 사용하면됨.

추가 프로젝트로 API 서버로 변경시켜보자(나중에..)

패키지 구조를 변경해야할 수도 있음. 아예 별도의 프로젝트로 생성하는 것이 좋을 듯
날짜별 To-Do-List
