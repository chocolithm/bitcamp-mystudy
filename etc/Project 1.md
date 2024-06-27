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
    executeIncomeCommand();
    addIncome();
    viewIncome();
    updateIncome();
    deleteIncome();

  ## Class ExpenseCommand
    executeExpenseCommand();
    addExpense();
    viewExpense();
    updateExpense();
    deleteExpense();

  ## Class CategoryCommand
    executeCategoryCommand();
    addCategory();
    listCategory(String transactionType);
    updateCategory();
    deleteCategory();

  ## Class StatisticsCommand
    executeStatisticsCommand();
    getByYear(String year); //연도별 조회
    getByMonth(String year, String month); //이번달 조회, 월별 조회
    getByCategory(); //카테고리별 조회
    getTotalIncome(); //총수입 조회
    getTotalExpense(); //총지출 조회
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