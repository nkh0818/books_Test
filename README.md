# README

## 사용자 이름 , 이메일 설정
- git config --global user.name "내이름"
- git config --global user.email "내이메일@example.com"

## 사용자 DB설정 mysql yaml 파일 설정
- 디비 아이디 비밀번호 확인
- 디비 이름 확인
- 미설정/확인 시 에러발생 가능성

## BaseLink 링크 
- http://localhost:8080

## Dependencies 설정
- Spring Boot DevTools
- Thymeleaf
- Spring Web 
- MySQL Driver
- Spring Data JPA
- Lombok
- AOP 

## 실행하는방법
- mvnw spring-boot:run
## 테스트용 실행
- mvnw test                             // 모든테스트 실행
- mvnw test -Dtest=클래스명            // 특정테스트 특정 실행
- mvnw test -Dtest=클래스명#메서드명    // 특정테스트에서 특정 메서드 실행
mvnw test -Dtest=BooksApplication#테스트_확인
## 해야할것
- AOP설정
- 메서드가 실행전 syso("메서드실행전")입니다.뜨게
- 그리고 around 설정으로 메서드 앞뒤에 메서드 실행 전, 후 중간 매개변수랑 반환값 출력도
- 컨트롤러는 기본적으로 도서 등록(add)/수정(update)/조회(select)
- Junit 해보기
- entity 설계랑 레포지토리 설계해서
- 코드 구현
- 그러고 나서 mock는 진짜 가짜 객체를 이용해서 테스트
- ㅡ
- /add 와 /update를 코드로 구현
- Junit5로 테스트를 하는 코드까지 구현
- select 조회아님 update를 할 때 기본적으로 아래 내용들이 
- update 구현 기본적인 것에 select 가 포함되어있음
- 도서명(title)에 특정 키워드가 포함된 도서 검색
- 특정 카테고리의 도서 목록 조회
- 특정 가격 이하의 도서 조회 (저가순 정렬)
- 판매 상태(SellStatus)에 따른 도서 조회
- 작가 이름으로 도서 찾기
- 책추가해서 해보기|ㅣ||ㅣ|ㅣ|ㅣ|||ㅣㅣ

- 추가 삭제 조회 수정? 끝?
# AWS 에 이미지 저장 + 링크 만 불러오기해야햄 DB에 저장 키없어야함..