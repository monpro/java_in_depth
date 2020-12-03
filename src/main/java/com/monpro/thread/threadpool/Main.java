package com.monpro.thread.threadpool;


import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static void getThreadPoolInfo(ThreadPoolExecutor threadPoolExecutor)
    {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            System.out.println("========");
            System.out.println("Pool Size: " + threadPoolExecutor.getPoolSize());
            System.out.println("Active Threads: " + threadPoolExecutor.getActiveCount());
            System.out.println("Number of tasks Completed: " + threadPoolExecutor.getCompletedTaskCount());
            System.out.println("Number of tasks in Queue: " + threadPoolExecutor.getQueue().size());
            System.out.println("========");
        }, 0, 1, TimeUnit.SECONDS);
    }

    public static void oomWithExecutor() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
//        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        getThreadPoolInfo(threadPoolExecutor);

        for(int i = 0; i < 100000000; i++){
            threadPoolExecutor.execute(() -> {
                String payLoad = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();

                try{
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(payLoad);
            });
        }

        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(1, TimeUnit.HOURS);
    }

    public static int threadPoolCounter() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 5, 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), r -> new Thread(r),
                new ThreadPoolExecutor.AbortPolicy());
        getThreadPoolInfo(threadPoolExecutor);
        IntStream.rangeClosed(1, 20).forEach(i -> {
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int id = atomicInteger.incrementAndGet();
            try{
                threadPoolExecutor.submit(() -> {
                    System.out.println("started " + id);
                    try{
                        TimeUnit.SECONDS.sleep(10);
                    }catch (InterruptedException e){
                        System.out.println(e);
                    }

                });
            }catch (Exception e){
                System.out.println(e);
                atomicInteger.decrementAndGet();
            }
        });

        TimeUnit.SECONDS.sleep(60);
        return atomicInteger.intValue();
    }


    public static int threadPoolActiveThreadsFirst() {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10) {
            @Override
            public boolean offer(Runnable runnable) {
                //return false, let thread pool increase threads first
                return false;
            }
        };

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2, 5, 5, TimeUnit.SECONDS,
                queue, r -> new Thread(r), (r, executor) -> {
            try {
                // when reject threads, then add tasks into queue
                if (!executor.getQueue().offer(r, 0, TimeUnit.SECONDS)) {
                    throw new RejectedExecutionException("ThreadPool queue full, failed to offer " + r.toString());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        getThreadPoolInfo(threadPool);

        // repeat atomicInteger counter code
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {

    }

}
