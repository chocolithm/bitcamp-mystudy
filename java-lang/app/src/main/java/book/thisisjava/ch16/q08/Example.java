package book.thisisjava.ch16.q08;

public class Example {
  private static Student[] students = {
      new Student("홍길동", 90, 96),
      new Student("신용권", 95, 93),
      new Student("강윤상", 85, 99),
  };

  private static double avg(Function<Student> f) {
    int sum = 0;
    for(Student s : students) {
      sum += f.apply(s);
    }

    return sum / students.length;
  }

  public static void main(String[] args) {
    double englishAvg = avg(s -> s.getEnglishScore());
    System.out.println("영어 평균 점수 : " + englishAvg);

    double mathAvg = avg(s -> s.getMathScore());
    System.out.println("수학 평균 점수 : " + mathAvg);
  }
}
