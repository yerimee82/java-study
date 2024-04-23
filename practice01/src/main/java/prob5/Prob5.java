package prob5;

public class Prob5 {

	public static void main(String[] args) {
		String numberString = "";
		for(int i=1; i<100; i++) {
			numberString = String.valueOf(i);
			int res = Prob5.count369(numberString);
			if (res > 0) {
				System.out.print(i + " ");
				for(int j =0; j<res; j++) {
					System.out.print("짝");
				}
				System.out.println();
			}
			
		}
	}
	
	public static int count369(String numString) {
		int count = 0;
		for (int j=0; j<numString.length(); j++) {
			if (numString.charAt(j) == '3' | numString.charAt(j) == '6' | numString.charAt(j) == '9') {
				count++;
			}
		}
		return count;
	}
	
}
