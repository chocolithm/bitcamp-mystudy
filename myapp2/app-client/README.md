# 43. SQL 삽입 공격 차단하기

## 학습목표

- SQL 삽입 공격을 이해하고 설명할 수 있다.
- SQL 삽입 공격을 방어하는 코드를 작성할 수 있다.
- 트랜잭션을 다룰 수 있다.

## 요구사항

- SQL 삽입 공격이 불가능하도록 JDBC 코드를 개선하라.

## 실행 결과

- 이전과 같다.

## 작업

- SQL을 실행할 때 Statement 대신 PreparedStatement를 사용
  - BoardDaoImpl, ProjectDaoImpl, UserDaoImpl 클래스 변경
- 트랜잭션 제어
  - ProjectAddCommand, ProjectUpdateCommand, ProjectDeleteCommand 클래스 변경
  - InitApplicationListener 클래스 변경

## 소스 파일

