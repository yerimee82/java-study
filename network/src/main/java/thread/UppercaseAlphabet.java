package thread;

public class UppercaseAlphabet {
    public void print() {
        for (char c = 'A'; c <= 'J' ; c++) {
            System.out.print(c);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
