package prob2;

public class SmartPhone extends MusicPhone {
    @Override
    public void execute(String function) {
        if(function.equals("음악")) {
            playMusic(function);
        } else if (function.equals("통화")) {
            super.execute(function);
        } else {
            openApp();
        }
    }

    private void playMusic(String function) {
        System.out.println("다운로드해서 "+function+"재생");
    }

    private void openApp() {
        System.out.println("앱 실행");
    }
}
