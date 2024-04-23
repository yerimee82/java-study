package prob03;

public class Prob03 {
	public static void main(String args[]) {
		char c[] = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 'p', 'e', 'n', 'c', 'i', 'l', '.' };

		// 원래 배열 원소 출력
		printCharArray(c);

		// 공백 문자 바꾸기
		char[] new_c = replaceSpace(c);

		// 수정된 배열 원소 출력
		printCharArray(new_c);
	}

	public static char[] replaceSpace(char a[]) {
		String re_a = new String(a);
		
		re_a = re_a.replace(' ', ',');
		
		return re_a.toCharArray();
	}

	public static void printCharArray(char a[]) {
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i]);
		}
		System.out.println();
		
	}

}
