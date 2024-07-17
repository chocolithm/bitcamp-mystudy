package book.designpattern.ch08.duck;

public class Duck implements Comparable<Duck> {
  String name;
  int weight;

  public Duck(String name, int weight) {
    this.name = name;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return name + " 체중: " + weight;
  }

  @Override
  public int compareTo(Duck otherDuck) {
    if(this.weight < otherDuck.weight) {
      return -1;
    } else if(this.weight == otherDuck.weight) {
      return 0;
    } else {
      return 1;
    }
  }
}
