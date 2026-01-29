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
