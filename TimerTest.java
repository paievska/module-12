import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    private static int second = 0;
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask displayTime = new TimerTask() {
            @Override
            public void run() {
                second++;
                System.out.println("Минуло " + second + " сек");
            }
        };
        TimerTask showMessage = new TimerTask() {
            @Override
            public void run() {
                System.out.println("-Минуло 5 сек-");
            }
        };
        timer.scheduleAtFixedRate(displayTime, 0, 1000);
        timer.scheduleAtFixedRate(showMessage, 4000, 5000);
    }

}
