package thread;

public class UpperCaseAlphabetRunnable extends UppercaseAlphabet implements Runnable {

    @Override
    public void run() {
        print();
    }
}
