import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzzTest {
    private static BlockingQueue<String> queue;
    public static ArrayList<Thread> threads = new ArrayList<>();
    private static AtomicInteger atomic;

    public static void main(String[] args) throws InterruptedException {
        queue = new LinkedBlockingQueue<>();
        System.out.print("Enter n = ");
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        atomic = new AtomicInteger(n);
        Thread A = new Thread(() -> {
            int i;
            while((i = atomic.get()) > 0){
                if (i % 3 == 0 && i % 5 != 0) {
                    queue.add("fizz");
                    atomic.decrementAndGet();
                }
            }
        });
        Thread B = new Thread(() -> {
            int i;
            while((i = atomic.get()) > 0){
                if (i % 3 != 0 && i % 5 == 0) {
                    queue.add("buzz");
                    atomic.decrementAndGet();
                }
            }
        });
        Thread C = new Thread(() -> {
            int i;
            while((i = atomic.get()) > 0){
                if (i % 3 == 0 && i % 5 == 0) {
                    queue.add("fizzbuzz");
                    atomic.decrementAndGet();
                }
            }
        });
        Thread D = new Thread(() -> {
            int i;
            while((i = atomic.get()) > 0){
                if (i % 3 != 0 && i % 5 != 0) {
                    queue.add(String.valueOf(i));
                    atomic.decrementAndGet();
                }
            }
        });
        threads.add(A);
        threads.add(B);
        threads.add(C);
        threads.add(D);
        for (Thread thread: threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        while(!queue.isEmpty()){
            System.out.println(queue.take());
        }
    }
}
