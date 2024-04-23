package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		/* 코드 작성 */
		int inputNum;
		int result = 0;
		
		System.out.println("숫자를 입력하세요: ");
		inputNum = scanner.nextInt();
		
		for(int i=0; i<inputNum+1; i++) {
			if ( inputNum % 2 == 0 && i % 2 == 0) { // 짝수 
				result += i;
			} else if(inputNum % 2 != 0 && i % 2 == 1) { // 홀
				result += i;
			}
		}
		System.out.println("결과 값 : " + result);
		scanner.close();
	}
}
