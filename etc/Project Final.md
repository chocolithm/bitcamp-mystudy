# 
<img src="README_images/logo.png" alt="로고">

<!-- 오류 및 해결 / 팀원간 갈등 / 아키텍쳐 구조도 -->

### 프로젝트 소개
- 주제 : 
  - 비트캠프 최종프로젝트
- 개발기간
  - 팀 결성: 2024-09-06(금)
  - 주제 선정: 
<!-- 이후 UI, 기능 분리 -->


### 개발팀
- (NAVER Cloud) 클라우드 기반 웹 데브옵스 프로젝트 개발자 과정 14기
- 팀원 : 김재우, 강윤상, 권기윤, 김민수, 이우성, 양지윤
- 공통
- chocolithm
  -  


### 목차
1. [시작 가이드](#1-시작-가이드)
2. [기술 스택](#2-기술-스택)
3. [프로젝트 구조](#3-프로젝트-구조)
4. [주요 기능](#4-주요-기능)
5. [화면 구성 및 기능](#5-화면-구성-및-기능)
6. [오류 및 해결](#6-오류-및-해결)
7. [개선 필요사항](#7-개선-필요사항)

<!-- 
  프로젝트 로고, 배포 주소
https://velog.io/@luna7182/%EB%B0%B1%EC%97%94%EB%93%9C-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-README-%EC%93%B0%EB%8A%94-%EB%B2%95
 -->


<br><br>
# 1. 시작 가이드

### Requirements
- JDK 21

### Installation
```
$ git clone "https://github.com/chocolithm/bitcamp-project4"
$ cd bitcamp-project4
```

### Run
#### Server
```
$ cd app-server
$ gradle run
```
#### Client
```
$ cd app-client
$ gradle run
```

<!-- Backend, Frontend -->



<br><br>
# 2. 기술 스택
<!-- https://github.com/Ileriayo/markdown-badges?tab=readme-ov-file#badges -->

### Environment
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

### Development
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![LibreOffice](https://img.shields.io/badge/LibreOffice-%2318A303?style=for-the-badge&logo=LibreOffice&logoColor=white)

### Communication
![KakaoTalk](https://img.shields.io/badge/kakaotalk-ffcd00.svg?style=for-the-badge&logo=kakaotalk&logoColor=000000)



<!-- 상황에 따라 config, test, deploy 등 추가 -->


<!-- 채택한 개발 기술과 브랜치 전략 -->

<br><br>
# 3. 프로젝트 구조
### app-server
<pre>
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂bitcamp
 ┃ ┃ ┃ ┗ 📂myapp
 ┃ ┃ ┃ ┃ ┣ 📂command
 ┃ ┃ ┃ ┃ ┃ ┗ 📜GameCommand.java
 ┃ ┃ ┃ ┃ ┣ 📂dao
 ┃ ┃ ┃ ┃ ┃ ┣ 📂skel
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HistoryDaoSkel.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDaoSkel.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ListHistoryDao.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ListUserDao.java
 ┃ ┃ ┃ ┃ ┣ 📂listener
 ┃ ┃ ┃ ┃ ┃ ┗ 📜InitApplicationListener.java
 ┃ ┃ ┃ ┃ ┗ 📜ServerApp.java
 ┃ ┗ 📂resources
 ┃ ┃ ┗ 📜README.md
 ┗ 📂test
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📜README.md
 ┃ ┗ 📂resources
 ┃ ┃ ┗ 📜README.md
</pre>

### app-client
<pre>
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂bitcamp
 ┃ ┃ ┃ ┗ 📂myapp
 ┃ ┃ ┃ ┃ ┣ 📂listener
 ┃ ┃ ┃ ┃ ┃ ┗ 📜InitApplicationListener.java
 ┃ ┃ ┃ ┃ ┗ 📜ClientApp.java
 ┃ ┗ 📂resources
 ┃ ┃ ┗ 📜README.md
 ┗ 📂test
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📜README.md
 ┃ ┗ 📂resources
 ┃ ┃ ┗ 📜README.md
</pre>

### app-common
<pre>
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂bitcamp
 ┃ ┃ ┃ ┣ 📂command
 ┃ ┃ ┃ ┃ ┣ 📂history
 ┃ ┃ ┃ ┃ ┃ ┗ 📜HistoryListCommand.java
 ┃ ┃ ┃ ┃ ┗ 📜Command.java
 ┃ ┃ ┃ ┣ 📂context
 ┃ ┃ ┃ ┃ ┗ 📜ApplicationContext.java
 ┃ ┃ ┃ ┣ 📂listener
 ┃ ┃ ┃ ┃ ┣ 📜ApplicationListener.java
 ┃ ┃ ┃ ┃ ┗ 📜StartApplicationListener.java
 ┃ ┃ ┃ ┣ 📂myapp
 ┃ ┃ ┃ ┃ ┣ 📂dao
 ┃ ┃ ┃ ┃ ┃ ┣ 📂stub
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HistoryDaoStub.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDaoStub.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜HistoryDao.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDao.java
 ┃ ┃ ┃ ┃ ┗ 📂vo
 ┃ ┃ ┃ ┃ ┃ ┣ 📜History.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
 ┃ ┃ ┃ ┣ 📂net
 ┃ ┃ ┃ ┃ ┗ 📜ResponseStatus.java
 ┃ ┃ ┃ ┗ 📂util
 ┃ ┃ ┃ ┃ ┣ 📜Ansi.java
 ┃ ┃ ┃ ┃ ┗ 📜Prompt.java
 ┃ ┗ 📂resources
 ┃ ┃ ┗ 📜README.md
 ┗ 📂test
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📜README.md
 ┃ ┗ 📂resources
 ┃ ┃ ┗ 📜README.md
</pre>

<br><br>
# 4. 주요 기능

### Tic Tac Toe
- 서버-클라이언트간 Tic Tac Toe 게임 제공
- 가위바위보를 통해 선후공 결정
- 게임 종료 후 다시하기, 전적보기 기능 제공


<br><br>
# 5. 화면 구성 및 기능

[플레이어 입력](#플레이어-입력)  
[가위바위보](#가위바위보)  
[게임화면](#게임화면)  
[게임종료](#게임종료)  
[게임 종료 메뉴](#게임-종료-메뉴)

### 플레이어 입력
- 서버에서 사용할 플레이어명을 입력한 후 클라이언트 연결을 기다립니다.
- 클라이언트에서 사용할 플레이어명을 입력하면 서버와 소켓 통신을 시작합니다.

<img src="README_images/player.png" alt="플레이어 입력">

### 가위바위보
- 가위바위보를 통해 선공을 결정합니다.
- 비기거나 입력값이 잘못된 경우 가위바위보를 다시 진행합니다.

<img src="README_images/RPS.png" alt="가위바위보">

### 게임화면
- 선공 플레이어부터 게임을 시작합니다.
- 1~9로 표시된 칸 중 표시하고자 하는 칸을 번호로 선택합니다.
- 입력한 값이 유효하지 않거나 이미 채워진 칸이라면 경고 출력 후 번호를 다시 선택합니다.
- 입력한 값이 유효하다면 상대방에게 턴이 넘어갑니다.

[서버]

<img src="README_images/server_play.png" alt="서버 게임화면">

[클라이언트]

<img src="README_images/client_play.png" alt="클라이언트 게임화면">

### 게임종료
- 게임판의 가로, 세로, 대각선 중 한 줄이 특정 플레이어로 모두 채워지면 승자를 선언합니다.
- 게임판이 모두 채워질 때까지 승자가 나오지 않으면 무승부를 선언합니다.
- 승자나 무승부가 선언되면 개인 전적과 게임 종료 메뉴를 출력합니다.

[게임 종료]

<img src="README_images/gameover.png" alt="게임종료">

[무승부]

<img src="README_images/draw.png" alt="무승부">

### 게임 종료 메뉴
- 게임 종료 후 클라이언트는 종료, 다시하기, 전적보기를 선택할 수 있습니다.
- [종료] : 서버와 클라이언트를 모두 종료합니다.
- [다시하기] : 가위바위보부터 게임이 다시 진행됩니다.
- [전적보기] : 클라이언트 플레이어의 전적을 보여준 후 서버와 클라이언트를 종료합니다.
- 서버가 종료될 때 .xlsx 형식으로 플레이어와 전적을 저장합니다.

<img src="README_images/after_menu.png" alt="게임 종료 메뉴">


<br><br>
# 6. 오류 및 해결
### '다시하기' 전적갱신 오류
[현상]
- '다시하기'로 추가 게임을 플레이할 시, 클라이언트의 전적이 갱신되지 않는 오류 발생
- 서버에서는 전적을 갱신하여 송신하지만 클라이언트에서는 갱신 전 전적을 수신

[원인]
- OutputStream 사용 시. 기존에 송신한 내용을 캐시로 저장하여 재활용할 수 있음
- 따라 Client User의 정보가 갱신되지 않은 채로 클라이언트에게 전달됨

[해결]
- '다시하기' 시 서버와 클라이언트 모두 out.reset()으로 버퍼 초기화


<br><br>
# 7. 개선 필요사항
### 서버 멀티스레딩 이슈
- 게임을 시작할 때, 서버에서는 별도 스레드를 생성하여 게임 프로세스 진행
- 하지만 현재 게임은 서버-클라이언트의 1대1 구조이므로, 스레딩 처리가 불필요
- 서버는 게임 중계를 담당하고, 클라이언트끼리 게임을 진행하는 구조로 개선 필요

### 데이터 저장 이슈
- 현재 데이터 저장은 서버가 종료될 때 일괄 처리되기에 실시간 갱신 불가
- 향후 다중 클라이언트를 고려하여 실시간 데이터 처리가 가능한 DB로 전환 필요


<!-- 아키텍쳐 구조, 개발 일지, 회고 블로그 링크, 트러블 슈팅, 개선 목표, 후기 -->
