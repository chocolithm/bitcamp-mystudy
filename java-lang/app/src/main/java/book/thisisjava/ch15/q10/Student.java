package book.thisisjava.ch15.q10;

public class Student implements Comparable<Student> {
  public String id;
  public int score;

  public Student(String id, int score) {
    this.id = id;
    this.score = score;
  }

  @Override
  public int compareTo(Student o) {
    if(score == o.score) {
      return 0;
    }
    if(score > o.score) {
      return 1;
    }
    return -1;
  }
}
