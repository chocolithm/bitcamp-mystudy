# 미니프로젝트
- 주제 : Tictactoe
  팀원 : 이가람 이민석 강윤상
  발표 : 



# 클래스 구조
<Project Server>

<Project Common>
  <Package command>
    <Package user>
    <Package game>
  <Pakcage vo>
    <Class User>  

<Project Client>

# 화면예시

-----------------------------------------------------

## 로그인
1. 로그인  //Login -> login()
2. 회원가입  //UserCommand -> addUser()
0. 종료

-----------------------------------------------------

## 메인
[연체 중인 도서가 있을 시 알림 표출]
1. 도서대출  //LibraryCommand -> searchBook(title)
2. 도서반납  //LibraryCommand -> returnBook()
2. 신간도서  //LibraryCommand -> searchBook(date)
3. 대출현황  //LibraryCommand -> showStatus()
4. 이용안내  //LibraryCommand -> showGuide()
0. 로그아웃  //Login -> logout()

## 관리자 화면
1. 사용자관리  //UserCommand
2. 도서관리  //BookCommand
<!-- 3. 대출기록   -->
0. 로그아웃  //Login -> logout()

-----------

# 서버 시작
사용자를 입력하세요 : 강윤상
서버 대기 중...

# 클라이언트 접속
사용자를 입력하세요 : 이민석
게임을 시작합니다.
(만약 회원이 없으면 자동으로 등록)

# 게임 시작
┌───┬───┬───┐
| 1 | 2 | 3 │
├───┼───┼───┤
| 4 | 5 | 6 |
├───┼───┼───┤
| 7 | 8 | 9 │
└───┴───┴───┘

이민석> 4

┌───┬───┬───┐
| 1 | 2 | 3 │
├───┼───┼───┤
| O | 5 | 6 |
├───┼───┼───┤
| 7 | 8 | 9 │
└───┴───┴───┘

강윤상> 4
Warning! 이미 입력된 칸입니다.

강윤상> 5
┌───┬───┬───┐
| 1 | 2 | 3 │
├───┼───┼───┤
| O | X | 6 |
├───┼───┼───┤
| 7 | 8 | 9 │
└───┴───┴───┘



1차원 배열
{0, 1, 2 ,3, 4, 5, 6 ,7, 8}
{
  {"", "", ""}
  {"", "", ""}
  {"", "", ""}
}