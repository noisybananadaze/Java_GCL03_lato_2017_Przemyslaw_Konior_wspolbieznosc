package app;

import app.event.CustomEvent;
import app.event.ICustomListener;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Crawler implements Runnable {

    private boolean isRunning;
    private ReentrantLock lock;
    private CustomEvent crawlerEvent = new CustomEvent();


    public Crawler(ReentrantLock lock) {
        this.lock = lock;
    }

    public void run() {


        isRunning = true;
        while (isRunning) {

            lock.lock();

            Random generator = new Random();
            Student student = new Student(
                    (generator.nextDouble()*5),
                    ("Jan"+Integer.toString(generator.nextInt(100))),
                    ("Kowalski"+Integer.toString(generator.nextInt(100))),
                    generator.nextInt(100)
            );
//            System.out.println(Thread.currentThread().getName());

            crawlerEvent.fire(this, Thread.currentThread().getName());

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }

            lock.unlock();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


//        lock.unlock();
//
//        lock.lock();
//        System.out.println(Thread.currentThread().getName());
//
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        lock.unlock();

    }

    public void addCrawlerListener(ICustomListener listener) {
        crawlerEvent.add(listener);
    }


    public void postCancel() {
        isRunning = false;
    }
}
