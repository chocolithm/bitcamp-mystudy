# 48. 멀티스레드 환경에서 DB 커넥션을 공유할 때의 문제점 확인 및 해결

## 학습목표

- DB 커넥션(SqlSession)을 공유할 때 문제점을 이해하고 재현해 볼 수 있다.
- 각 스레드 별로 커넥션(SqlSession) 객체를 따로 관리할 수 있다.

## 요구사항

- 데이터 변경 작업이 클라이언트 간에 영향을 끼치지 않도록 하라.

## 실행 결과

- 이전과 같다.

## 작업

- SqlSessionFactory의 기능을 변경
  - GoF의 Proxy 패턴을 적용하여 SqlSessionFactoryProxy 클래스 정의
  - SqlSessionFactory의 openSession(boolean) 메서드의 기능 변경
    - 스레드 별로 같은 SqlSession 객체를 리턴
- DaoFactory 클래스 변경
  - Dao 구현체에서 작업할 때 SqlSessionFactory를 통해 SqlSession 객체를 얻어 사용하도록 변경
- Command 구현체 변경
  - commit()/rollback()을 수행할 때 SqlSessionFactory를 통해 SqlSession을 얻어 사용하도록 변경
- InitApplicationListener 클래스 변경
  - SqlSessionFactoryProxy 객체 생성
  - DAO 객체 및 Command 객체에 주입


## 소스 파일

