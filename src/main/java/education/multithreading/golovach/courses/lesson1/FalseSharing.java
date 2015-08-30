package education.multithreading.golovach.courses.lesson1;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Created by yaroslav on 30.08.15.
 */
public class FalseSharing {
    /**
     * Long is 8 bytes. Typical cache line is 64 bytes. =>
     * If for first thread take value0 and for second thread value1 (or 2,3... which are probably
     * will be in the same cache line) then will observe False Sharing effect.
     * If take value0 and value8 => they for sure will be in different cache lines  => performance
     * much better then in first case (no false sharing effect)
     * In i5 processor I observe 7-10 times better performance without false sharing.
     */
    volatile static long value0 = 0;
    volatile static long value1 = 0;
    volatile static long value2 = 0;
    volatile static long value3 = 0;
    volatile static long value4 = 0;
    volatile static long value5 = 0;
    volatile static long value6 = 0;
    volatile static long value7 = 0;
    volatile static long value8 = 0;


    public static void main(String[] args) throws Exception {
        ExecutorService pool = newFixedThreadPool(2);
        CountDownLatch latch1 = new CountDownLatch(2);
        CountDownLatch latch2 = new CountDownLatch(2);
        pool.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                latch1.countDown();
                latch1.await();
                long t0 = System.nanoTime();
                for (int k = 0; k < 100_000_000; k++) {
                    value0 = value0 * k;
                }
                long t1 = System.nanoTime();
                System.out.println((t1-t0)/1000000 + "ms");
                latch2.countDown();
                return null;
            }
        });
        pool.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                latch1.countDown();
                latch1.await();
                long t0 = System.nanoTime();
                for (int k = 0; k < 100_000_000; k++) {
                    /**
                     * change here (value1 or value8)
                     */
                    value1 = value1 * k;
                }
                long t1 = System.nanoTime();
                System.out.println((t1 - t0) / 1000000 + "ms");
                latch2.countDown();
                return null;
            }
        });
        latch2.await();
        pool.shutdownNow();
    }
}
