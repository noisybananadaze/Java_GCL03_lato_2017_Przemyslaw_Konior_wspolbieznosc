package app;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by przem on 02.05.17.
 */
public class ParallelLogger implements Logger, Runnable {

    private LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();

    public void log(String data) {
        System.out.println(data);
        deque.add(data);
    }

    public void run() {
//        while (true) {
//            System.out.println("try to log");
//            System.out.println(deque.getLast());
//            try {
//                TimeUnit.SECONDS.sleep(7);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
