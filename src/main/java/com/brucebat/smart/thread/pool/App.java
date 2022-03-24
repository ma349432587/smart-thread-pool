package com.brucebat.smart.thread.pool;

import com.brucebat.smart.thread.pool.builder.ThreadPoolBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 应用测试
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/22 4:18 PM
 */
public class App {
    public static void main(String[] args) {
        System.out.println(1 << 10);
        System.out.println(~(1 << 10));
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolBuilder.builder().corePoolSize(5).maxPoolSize(10).workQueue(new LinkedBlockingQueue<>(100)).keepAliveTime(10).build();
        // 获取当前线程池内的线程数量
        System.out.println(threadPoolExecutor.getPoolSize());
        // 获取当前线程池内正在执行任务的线程数
        System.out.println(threadPoolExecutor.getActiveCount());
        // 获取当前已安排进行执行的任务总数
        System.out.println(threadPoolExecutor.getTaskCount());
        // 获取当前已经执行完成的任务总数
        System.out.println(threadPoolExecutor.getCompletedTaskCount());
    }
}
