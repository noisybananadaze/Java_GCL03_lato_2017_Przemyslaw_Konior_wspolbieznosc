package app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("asd3");
        list.add("asd3");
        list.add("asd3");

        Monitor monitor = new Monitor(3, list);
        try {
            monitor.run();
        } catch (MonitorException e) {
            e.printStackTrace();
        }


        ParallelLogger parallelLogger = new ParallelLogger();
        List<Crawler> crawlers = monitor.getCrawlerList();
        for (Crawler c : crawlers) {
            c.addCrawlerListener((source, info) -> parallelLogger.log((String) info));
        }
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(parallelLogger);



        try {
            TimeUnit.SECONDS.sleep(30);
            monitor.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
//            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(25, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
//            System.out.println("shutdown finished");
        }


//        ReentrantLock lock = new ReentrantLock();
//        Runnable asd = new Crawler(lock);
//
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        executor.submit(new Crawler(lock));
//        executor.submit(new Crawler(lock));
//        executor.submit(new Crawler(lock));
    }

}
