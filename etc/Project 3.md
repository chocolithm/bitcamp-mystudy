# 미니프로젝트
- 주제 : 도서대출 시스템
  팀원 : 백현기, 강윤상
  발표 : 7/17 수 16시


추가할 기능?
  - 통계
  - 관리자
  - 연체 관리


# 클래스 구조
<Package util>
  <Class Prompt>
  

  <Class Login>
  User loginUser;
  login();
  logout();

  <Class Dummy>
  Dummy data


<Package vo>

  <Class User>
  String id;
  String pw;
  String name;
  boolean isAdmin;
  List<Book> borrowedBookList;
  Date joinDate;

  <Class Book>
  String name;
  String author;
  String category;
  boolean isBorrowed;
  Date registerDate;
  Date borrowDate;
  Date returnDate;


<Package controller>

  <Class UserController>
  List<User> userList;
  ---------------------
  addUser()
  deleteUser()

  <Class BookController>
  List<Book> bookList;
  ---------------------
  CRUD

  <Class LibraryController>
  findBook(String text)  //검색
  borrowBook(User loginUser, Book book)  //대출
  returnBook(User loginUser, Book book)  //반납
  showStatus(User user)  //대출현황
  showGuide()  //이용안내


# 화면예시

-----------------------------------------------------

## 로그인
1. 로그인  //Login -> login()
2. 회원가입  //UserController -> addUser()
0. 종료

-----------------------------------------------------

## 메인
[연체 중인 도서가 있을 시 알림 표출]
1. 도서검색  //LibraryController -> findBook()
2. 대출현황  //LibraryController -> showStatus()
3. 이용안내  //LibraryController -> showGuide()
0. 로그아웃  //Login -> logout()

## 관리자 화면
1. 사용자관리  //UserController
2. 도서관리  //BookController
3. 대출기록  
0. 로그아웃  //Login -> logout()

-----------------------------------------------------

## 메인 > 도서검색
도서 제목? <<제목>>

번호    분류    제목            대출여부
1       과학    인터스텔라      대출가능
2       인문    군주론          대출중

대출하시겠습니까? <<y/n>>
도서 번호? <<번호>>
대출되었습니다. / 대출이 불가한 도서입니다.


## 메인 > 대출현황
사용자 : 홍길동
분류    제목        대출일      반납예정일      상태
과학    인터스텔라  2024-07-11  2024-07-25      정상
인문    군주론      2024-06-11  2024-06-25      연체


## 메인 > 이용안내
~~~~
~~~~

-----------------------------------------------------

## 관리자 > 사용자관리
1. 목록
2. 수정
3. 삭제
0. 이전