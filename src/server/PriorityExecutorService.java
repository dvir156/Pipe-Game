package server;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;

public class PriorityExecutorService{
    private ExecutorService threadPool;
    private PriorityBlockingQueue<Runnable> Queue;

    private boolean stop;
    public PriorityExecutorService(int numofthreads, int queueSize, Comparator<Runnable> comparator) {
        this.threadPool = Executors.newFixedThreadPool(numofthreads);
        this.Queue = new PriorityBlockingQueue<Runnable>(queueSize, comparator);
        ExecutorService pick = Executors.newSingleThreadExecutor();
        this.stop = false;
        pick.execute(() -> {
            while (!stop) {
                try {
                    Runnable current = Queue.take();
                   // System.out.println("executing" + current);
                    this.threadPool.execute(current);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
    }

    public void addRunnable(Runnable runnable) {
        this.Queue.add(runnable);
    }

    public void stop() {
        this.stop = true;
        threadPool.shutdown();
    }
}
