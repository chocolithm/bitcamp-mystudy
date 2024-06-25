package bitcamp.myapp.command;

public class LinkedList {

  Node first;
  Node last;
  int size;

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
  }

  public void append(Object value) {
    Node newNode = new Node(value);

    if(first == null) {
      last = first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }
    size++;
  }

  public Object getValue(int index) {
    if (index < 0 || index >= size) {
      throw null;
    }

    Node cursor = first;
    int currentIndex = 0;

    while(cursor != null) {
      if(currentIndex == index) {
        return cursor.value;
      }
      cursor = cursor.next;
      currentIndex++;
    }

    return null;
  }

  public Object delete(int index) {
    if (index < 0 || index >= size) {
      throw null;
    }

    Node deletedNode;
    size--;

    if(index == 0) {
      deletedNode = first;
      first = first.next;
      if(first == null) {
        last = null;
      }
      return deletedNode.value;
    }

    Node cursor = first;
    int currentIndex = 0;

    while(cursor != null) {
      if(currentIndex == index - 1) {
        break;
      }
      cursor = cursor.next;
      currentIndex++;
    }

    deletedNode = cursor.next;
    cursor.next = cursor.next.next;

    if(cursor.next == null) {
      last = cursor;
    }

    return deletedNode.value;
  }

  public Object[] getArray() {
    Object[] arr = new Object[size];

    Node cursor = first;
    for(int i = 0; i < size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

  public void printAll() {
    Node cursor = first;
    while (cursor != null) {
      System.out.print(cursor.value + ",");
      cursor = cursor.next;
    }
    System.out.println();
  }

  public int size() {
    return size;
  }

  public int index(Object value) {
    Node cursor = first;
    int currentIndex = 0;

    while (cursor != null) {
      if (cursor.value == value) {
        return currentIndex;
      }
      cursor = cursor.next;
      currentIndex++;
    }
    return -1;
  }
}
