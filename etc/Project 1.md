# 미니프로젝트
- 주제 : 가계부
  팀원 : 강슬기, 이가람, 강윤상
  
  메뉴 (서브메뉴)
  수입 (등록, 조회, 변경, 삭제)
  지출 (등록, 조회, 변경, 삭제)
  카테고리 (등록, 목록, 변경, 삭제)
  조회 (이번달 조회, 월별 조회, 연도별 조회, 카테고리별 조회)
  종료

결정해야 할 사항
- 수입 지출 합쳐야 하는지
  > 월별 조회, 카테고리별 조회 등 수행 시 목록을 보여주는데,
    상세조회 기능을 구현할 거라면 수입 조회 병합 필요 (seqNo 이슈)


Package Util
  ## Interface List
  ## Class AbstractList
  ## Class LinkedList
  ## Class Prompt

Package vo
  ## Class Income
    int no;
    String date;
    int amount;
    Category category;
    String content;

  ## Class Expense
    int no;
    String date;
    int amount;
    Category category;
    String content;

  ## Class Category
    int no;
    String name;
    String transactionType; //수입인지 지출인지

Package command
  ## Class IncomeCommand
    executeIncomeCommand(String subMenuTitle);
    addIncome();
    viewIncome();
    updateIncome();
    deleteIncome();

  ## Class ExpenseCommand
    executeExpenseCommand(String subMenuTitle);
    addExpense();
    viewExpense();
    updateExpense();
    deleteExpense();

  ## Class CategoryCommand
    executeCategoryCommand(String subMenuTitle);
    addCategory();
    listCategory(String transactionType);
    updateCategory();
    deleteCategory();

  ## Class StatisticsCommand
    executeStatisticsCommand(String subMenuTitle);
    getTransactionByCategory(Category category); //카테고리별 조회
    getTransactionByDate(String year);
    getTransactionByDate(String year, String month);
    getTotalIncome(String year); //기간별 총수입 조회
    getTotalIncome(String year, String month); //기간별 총수입 조회
    getTotalExpense(String year); //기간별 총지출 조회
    getTotalExpense(String year, String month); //기간별 총지출 조회
    - 조회사항 : 총수입, 총지출, 합계, 목록



## 수입/등록
메인> 1
수입> 1
수입/등록> 날짜? 20240627
수입/등록> 금액? 40000
분류 //예시입니다
1. 월급
2. 용돈
3. 기타
수입/등록> 분류? 1
수입/등록> 항목? 비트컴퍼니
등록되었습니다.

## 수입/조회
메인> 1
수입> 2
수입/조회> 날짜? 20240627
번호 구분 금액
1 수입 40,000원
4 수입 25,000원
수입/조회> 번호(0은 이전)? 1
날짜 : 2024-06-27
구분 : 수입
금액 : 40,000원
분류 : 월급
항목 : 비트컴퍼니
수입>

## 수입/변경
메인> 1
수입> 3
수입/변경> 날짜? 20240627
번호 구분 금액
1 수입 40,000원
4 수입 25,000원
수입/변경> 번호(0은 이전)? 1
수입/변경> 금액(40,000원)? 35,000원
분류
1. 월급
2. 용돈
3. 기타
수입/변경> 분류(월급)? 1
항목(비트컴퍼니)? 비트은행
변경되었습니다.
수입>

## 수입/삭제
메인> 1
수입> 4
수입/삭제> 날짜? 20240627
번호 구분 금액
1 수입 40,000원
4 수입 25,000원
수입/삭제> 번호? 1
삭제되었습니다.
수입>