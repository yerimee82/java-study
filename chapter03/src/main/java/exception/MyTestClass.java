package exception;

import java.io.IOException;

public class MyTestClass {

	public static void main(String[] args) {
		try {
			new MyClass().danger();
			
		}catch (MyException e) {
			// TODO: handle exception
			System.out.println("error" + e);
		} 
		catch (IOException e) {
//			System.out.println("error" + e);
			e.printStackTrace(); // 예외가 발생한 스택 상태를 그대로 보여줌 
		}
	}

}
