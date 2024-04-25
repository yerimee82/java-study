package prob06;

public abstract class AbstractArith implements Arith{
	protected int a;
    protected int b;
    
    @Override
    public void setValue(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void printResult(int result) {
        System.out.println(">> " + result);
    }
}
