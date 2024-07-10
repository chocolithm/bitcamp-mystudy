package bitcamp.myapp.util;

public abstract class AbstractList03 implements List {
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
        // anonymous class
        // 익명 클래스를 만들고 그 인스턴스를 생성한 후 주소를 리턴
        return new Iterator() {
            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor < size();
            }

            @Override
            public Object next() {
                return get(cursor++);
            }
        };
    }
}
