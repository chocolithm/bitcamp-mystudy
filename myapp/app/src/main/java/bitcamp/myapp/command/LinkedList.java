package bitcamp.myapp.command;

public class LinkedList {

  Node first;
  Node last;
  int size;

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.append("홍길동");
    list.append("임꺽정");
    list.append("유관순");
    list.append("안중근");
    list.append("윤봉길");
    list.append("김구");

    list.delete(2);
    list.printAll();
    list.delete(2);
    list.printAll();
    list.delete(2);
    list.printAll();
    list.delete(2);
    list.printAll();
    list.delete(2);
    list.printAll();
  }

  public void append(Object value) {
    Node newNode = new Node(value);

    if (first == null) {
      last = first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }
    size++;
  }

  public Object getValue(int index) {
    if (index < 0 || index >= size) {
      return null;
    }

    Node cursor = first;
    int currentIndex = 0;

    while (cursor != null) {
      if (currentIndex == index) {
        return cursor.value;
      }
      currentIndex++;
      cursor = cursor.next;
    }

    return null;
  }

  public Object delete(int index) {
    if (index < 0 || index >= size) {
      return null;
    }

    Node deletedNode = null;
    size--;

    if (index == 0) {
      deletedNode = first;
      first = first.next;
      if (first == null) {
        last = null;
      }
      return deletedNode.value;
    }

    Node cursor = first;
    int currentIndex = 0;

    // 리스트를 순회하며 인덱스를 찾음
    while (cursor != null) {
      if (currentIndex == index - 1) {
        break;
      }
      cursor = cursor.next;
      currentIndex++;
    }

    deletedNode = cursor.next;
    cursor.next = cursor.next.next;

    if (cursor.next == null) {
      last = cursor;
    }

    return deletedNode.value;
  }

  public int index(Object value) {
    Node cursor = first;
    int currentIndex = 0;

    while (cursor != null) {
      if (cursor.value == value) {
        return currentIndex;
      }
      currentIndex++;
      cursor = cursor.next;
    }

    return -1;
  }

  public Object[] getArray() {
    Object[] arr = new Object[size];

    Node cursor = first;

    for (int i = 0; i < size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }

    return arr;
  }

  public int size() {
    return size;
  }

  public void printAll() {
    Node cursor = first;
    while (cursor != null) {
      System.out.print(cursor.value + ",");
      cursor = cursor.next;
    }
    System.out.println();
  }
}
