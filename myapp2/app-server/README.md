# 49. 웹 애플리케이션 서버 구조로 전환하기 - 웹 기술 도입

## 학습목표

- 웹 애플리케이션 아키텍처를 이해하고 구동 원리를 설명할 수 있다.
- Servlet 기술 명세에 따라 웹 컴포넌트를 만들 수 있다.

## 요구사항

- 기존의 애플리케이션을 Java EE 기술 명세에 맞춰 웹 애플리케이션으로 전환하라.

## 실행 결과

- 웹 브라우저를 이용하여 애플리케이션 사용

## 작업

- 톰캣 임베디드 라이브러리 추가
  - build.gradle 변경 및 재로딩
- Tomcat 서버 구동
  - ServerApp 클래스 변경
- 웹애플리케이션 루트 폴더 생성
  - src/main/webapp 폴더 생성 및 index.html 파일 생성
- InitApplicationListener를 ServletContextListener 규격에 맞춰 변경한다.
  - 클래스명 변경: ContextLoaderListener
- Command 구현체를 Servlet 규격에 맞춰 변경
  - Servlet 인터페이스 구현


## 소스 파일

