package prob5;

public class MyStack03<T> {
    private T[] buffer;
    private int size;
    private int top;

    @SuppressWarnings("unchecked")
    public MyStack03(int size) {
        top = -1;
        buffer = (T[]) new Object[size];    // T[] 로 작성하면 오류남 -> 실행할 때 타입을 모르기 때문.
//        buffer = (T[]) Array.newInstance(getClass(), size);
    }

    public void push(T s) {
        if (top == buffer.length - 1) {
            resize();
        }

        buffer[++top] = s;
    }

    public T pop() throws MyStackException {
        if (isEmpty()) {
            throw new MyStackException("stack is empty");
        }

        T result = buffer[top];
        buffer[top--] = null;

        return result;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    @SuppressWarnings("unckecked")
    private void resize() {
        T[] temp = (T[]) new Object[buffer.length * 2];
        for (int i = 0; i <= top; i++) {
            temp[i] = buffer[i];
        }

        buffer = temp;
    }


}