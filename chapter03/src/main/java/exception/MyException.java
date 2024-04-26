package exception;

@SuppressWarnings("serial")  // 객체를 그대로 파일에 저장함. 
public class MyException extends Exception {
	public MyException(String message) {
		super(message);
	}
	
	public MyException() {
		super("My Exception Thrown");
	}
}
