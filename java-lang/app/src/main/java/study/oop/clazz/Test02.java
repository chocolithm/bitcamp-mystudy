package study.oop.clazz;

import study.oop.clazz.domain.Score;

// 연습: 클래스 문법을 데이터 타입 정의에 사용
//1) 클래스 사용 전
//2) 데이터 타입 정의: 성적 데이터를 저장할 메모리를 새로 정의: Score 클래스
//3) 리팩토링: printScore() 메서드
//4) 리팩토링: compute() 메서드
//5) GRASP 패턴: compute() 메서드 이동
//6) 인스턴스 메서드: compute() 메서드를 non-static으로 만듦
//7) 클래스를 역할에 따라 패키지로 분류: vo 패키지 생성 및 Score 클래스를 vo 패키지로 이동, import문
//8) Score 멤버의 접근제어 변경: public modifier
//9) 생성자 도입: Score() 생성자
//10) private과 getter 도임: sum, aver 필드는 private으로 한 후 getter 추가
//11) 국, 영. 수 점수 변경 후 합계 평균 자동 계산: kor, eng, math 필드 private, setter/getter 추가
//12) 코딩의 일관성을 위해 다른 필드도 getter/setter로 접근

public class Test02 {
  static Score[] scores = new Score[] {
      new Score("홍길동", 100, 90, 85),
      new Score("임꺽정", 90, 80, 75),
      new Score("유관순", 80, 70, 65)
  };

  public static void main(String[] args) {
    printScore();
  }

  static void printScore() {
    for(Score score : scores) {
      System.out.printf("%s: %d, %d, %d, %d, %.1f\n", score.getName(), score.getKor(), score.getEng(), score.getMath(), score.getSum(), score.getAver());
    }
  }
}