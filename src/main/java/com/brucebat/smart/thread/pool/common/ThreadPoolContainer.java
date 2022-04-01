package com.brucebat.smart.thread.pool.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池工厂类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/28 4:51 PM
 */
public class ThreadPoolContainer {

    private static final ConcurrentHashMap<String, ThreadPoolExecutor> THREAD_POOL_CONTAINER = new ConcurrentHashMap<>();

    private static final String NAME_SPLIT = "#";

    /**
     * 进行线程池添加
     *
     * @param appName        待添加线程池应用名称
     * @param threadPoolName 待添加线程池明名称
     */
    public static void addThreadPool(String appName, String threadPoolName, ThreadPoolExecutor threadPoolExecutor) {
        String threadPoolKey = appName + NAME_SPLIT + threadPoolName;
        if (THREAD_POOL_CONTAINER.containsKey(threadPoolKey)) {
            throw new IllegalArgumentException("当前应用中已经存在该线程池名称: " + threadPoolName);
        }
        THREAD_POOL_CONTAINER.put(threadPoolKey, threadPoolExecutor);
    }

    /**
     * 根据应用名称和线程池名称获取对应的线程池实例
     *
     * @param appName        线程池所属应用名称
     * @param threadPoolName 线程池名称
     * @return 线程池
     */
    public static ThreadPoolExecutor getThreadPool(String appName, String threadPoolName) {
        return THREAD_POOL_CONTAINER.get(appName + NAME_SPLIT + threadPoolName);
    }

    /**
     * 根据进行线程池内部属性调整
     *
     * @param appName        待处理线程池应用名称
     * @param threadPoolName 待处理线程池名称
     * @param corePoolSize   待修改核心线程数
     * @param maxPoolSize    待修改最大线程数
     * @param workQueueSize  待修改工作队列长度
     */
    public static void modifyThreadPool(String appName, String threadPoolName, Integer corePoolSize, Integer maxPoolSize, Integer workQueueSize) {
        // 进行线程池修改
    }

    /**
     * 根据进行线程池内部属性调整
     *
     * @param appName        待处理线程池应用名称
     * @param threadPoolName 待处理线程池名称
     * @param corePoolSize   待修改核心线程数
     * @param maxPoolSize    待修改最大线程数
     * @param workQueue      待修改工作队列
     */
    public static void modifyThreadPool(String appName, String threadPoolName, Integer corePoolSize, Integer maxPoolSize, BlockingQueue<Runnable> workQueue) {
        // 进行线程池修改
    }

    /**
     * 根据应用名称和线程池名称获取删除对应的线程池实例
     *
     * @param appName        待删除线程池的应用名称
     * @param threadPoolName 待删除线程池的名称
     */
    public static void removeThreadPool(String appName, String threadPoolName) {
        removeSingleThreadPool(appName + NAME_SPLIT + threadPoolName);
    }

    /**
     * 进行全量线程池删除操作
     */
    public static void removeAllThreadPools() {
        if (THREAD_POOL_CONTAINER.isEmpty()) {
            return;
        }
        for (String threadPoolKey : THREAD_POOL_CONTAINER.keySet()) {
            removeSingleThreadPool(threadPoolKey);
        }
    }

    /**
     * 进行单个线程池删除
     *
     * @param threadPoolKey 待删除线程池key
     */
    private static void removeSingleThreadPool(String threadPoolKey) {
        ThreadPoolExecutor removedThreadPoolExecutor = THREAD_POOL_CONTAINER.remove(threadPoolKey);
        removedThreadPoolExecutor.shutdown();
    }
}
