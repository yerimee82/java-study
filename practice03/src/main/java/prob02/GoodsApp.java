package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];
		
		int lines = 3;

		// 상품 입력
		for(int i=0; i<lines; i++) {
			String line = scanner.nextLine();
			String[] data = line.split(" ");
			
			// split 한 것 변수 할당 
			String name = data[0];
			int price = Integer.parseInt(data[1]);
			int count = Integer.parseInt(data[2]);
			
			// 상품 저장 
			goods[i] = new Goods(name, price, count);
			
		}
		

		// 상품 출력
		for(int i=0; i<goods.length; i++) {
			System.out.println(goods[i].getProductName()+"(가격: "+goods[i].getPrice()+"원)이 "
					+goods[i].getCount()+"개 입고 되었습니다.");
			
		}
		
		// 자원정리
		scanner.close();
	}
}
