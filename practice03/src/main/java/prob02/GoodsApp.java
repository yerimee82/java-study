package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		// 상품 입력

		// 상품 출력
		String line = scanner.nextLine();
		String[] data = line.split(" ");
		
		String name = data[0];
		int price = Integer.parseInt(data[1]);
		
		// 자원정리
		scanner.close();
	}
}
