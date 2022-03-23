package com.brucebat.smart.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/22 4:18 PM
 */
public class App {
    public static void main(String[] args) {
        System.out.println(1 << 10);
        System.out.println(~(1 << 10));
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));
        System.out.println(threadPoolExecutor.getActiveCount());
        System.out.println(threadPoolExecutor.getTaskCount());
        System.out.println(threadPoolExecutor.getCompletedTaskCount());
    }
}
