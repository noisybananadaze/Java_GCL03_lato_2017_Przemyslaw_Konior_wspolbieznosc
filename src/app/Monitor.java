package app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {

    private List<String> studentsList;
    private int threads;
    private ExecutorService executor;

    private List<Crawler> crawlerList = new ArrayList<>();
//    private ParallelLogger parallelLogger = new ParallelLogger();

    public List<Crawler> getCrawlerList() {
        return crawlerList;
    }

    public Monitor(int threads, List<String> studentsList) {
        this.threads = threads;
        this.studentsList = studentsList;
    }

    public void run() throws MonitorException {
        int listSize = studentsList.size();
        if (threads < listSize) {
            throw new MonitorException("threads < studentsList.size()");
        }

        ReentrantLock lock = new ReentrantLock();
        executor = Executors.newFixedThreadPool(listSize);

        for (int i = 0; i < listSize; i++) {
            Crawler crawler = new Crawler(lock);
//            crawler.addCrawlerListener((source, info) -> System.out.println(info));
            executor.submit(crawler);
            crawlerList.add(crawler);
        }
    }

    public void cancel() {

        for (Crawler crawler : crawlerList) {
            crawler.postCancel();
        }

        try {
            System.out.println("attempt to shutdown executor");
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
            System.out.println("shutdown finished");
        }
    }
}
