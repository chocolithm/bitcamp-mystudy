package bitcamp.myapp.util;

public abstract class AbstractList02 implements List {
    protected int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator iterator() {
         return new ListIterator();
    }

    // nested class
    // static nested를 non-static nested로 바꾸면 list의 주소를 따로 받을 필요가 없음 = inner class
    // 컴파일러가 바깥 클래스의 인스턴스 주소를 젇날하는 코드로 자동 변환
    class ListIterator implements Iterator {
        private int cursor;

        @Override
        public boolean hasNext() {
            // 바깥 클래스의 인스턴스를 사용하려면
            // 바깥클래스명.this 로 지정
            // return cursor < AbstractList.this.size();
            
            // 중첩 클래스 안에 해당 필드나 메서드가 없다면
            // 바깥클래스명.this 생략 가능
            return cursor < size();
        }

        @Override
        public Object next() {
            // AbstractList.this 생략 가능
            return get(cursor++);
        }
    }
}
