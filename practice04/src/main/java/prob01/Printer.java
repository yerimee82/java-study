package prob01;

public class Printer {
	Object variable;
	
	public void println(Object variable) {
		System.out.println(variable);
	}

	public <T> void println2(T t) {
		System.out.println(t);
	}
	@SafeVarargs
	public final <T> void println2(T... ts) {
		for (T t :
				ts) {
			System.out.println(t);
		}
	}

	public int sum(Integer...nums) {
		int s = 0;
		for (Integer n :
				nums) {
			s += n;
		}

		return s;
	}
}

