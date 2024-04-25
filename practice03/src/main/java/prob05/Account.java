package prob05;

public class Account {
	private String accountNo;
	private int balance;

	public Account(String accountNo) {
		this(accountNo, 0);
	}

	public Account(String accountNo, int balance) {
		this.accountNo = accountNo;
		this.balance = balance;

		System.out.println(accountNo + " 계좌가 개설되었습니다.");
	}

	public String getAccountNo() {
		return accountNo;
	}

	public int getBalance() {
		return balance;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void save(int i) {
		this.balance += i;
		System.out.println(accountNo + " 계좌에 " + i + "만원이 입금되었습니다.");
	}

	public void deposit(int i) {
		this.balance -= i;
		System.out.println(accountNo + " 계좌에 " + i + "만원이 출금 되었습니다.");
	}

}
