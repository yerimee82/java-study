package prob5;

import java.util.logging.Logger;

public class MyStack {
    private String[] stackArray;
    private int size;
    private int top;

    public MyStack(int size) {
        this.size = size;
        this.stackArray = new String[size];
        this.top = -1;
    }

    public void push(String value) {
        if (top == size - 1) {  // 스택이 가득 찬 경우 확장
            expandStack();
        }
        stackArray[++top] = value;
    }

    public String pop() {
        return stackArray[top--];
    }

    public boolean isEmpty() throws MyStackException {
        if (top == -1) throw new MyStackException("stack is empty");
        return false;
    }

    private void expandStack() {
        int newSize = size * 2;
        StringBuffer sb = new StringBuffer();

        for (String element: stackArray) {
            sb.append(element).append(" ");
        }

        stackArray = new String[newSize];
        String[] values = sb.toString().split(" ");
        for (int i = 0; i < values.length; i++) {
            stackArray[i] = values[i];
        }
        size = newSize;
    }


}