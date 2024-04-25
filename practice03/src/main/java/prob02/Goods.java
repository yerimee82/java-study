package prob02;

public class Goods {
	private String productName;
	private int price;
	private int count;

	public Goods(String productName, int price, int count) {
		this.productName = productName;
		this.price = price;
		this.count = count;
	}

	public String getProductName() {
		return productName;
	}

	public int getPrice() {
		return price;
	}

	public int getCount() {
		return count;
	}

}
