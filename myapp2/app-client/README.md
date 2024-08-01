# 38. 여러 클라이언트의 요청을 순차적으로 처리하기: Stateful vs Stateless

## 학습목표

- connection-oriented 통신 방식과 connectionless 통신 방식을 이해하고 설명할 수 있다.
- Stateful 방식과 Stateless 방식을 이해하고 비교 설명할 수 있다.
- Stateful 및 Stateless 통신을 구현할 수 있다.

## 요구사항

- Stateful 방식으로 여러 클라이언트의 요청을 처리하라.
- Stateless 방식으로 전환하여 여러 클라이언트의 요청을 처리하라.

## 실행 결과

- 이전과 같다.

## 작업

- 여러 클라이언트 요청을 처리
  - ServerApp 클래스에서 클라이언트 요청을 무한 반복하여 처리하도록 변경
- Stateless 방식으로 전환
  - app-server 프로젝트
    - ServerApp 클래스 변경
  - app-common 프로젝트
    - Stub 클래스 변경 
  - app-client 프로젝트
    - InitApplicationListener 클래스 변경
  
## 소스 파일
