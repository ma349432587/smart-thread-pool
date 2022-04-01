package com.brucebat.smart.thread.pool.common.utils;

import com.brucebat.smart.thread.pool.common.ThreadPoolConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/4/1 10:39 AM
 */
public class ThreadPoolUtils {


    public static ThreadPoolConfig assembleConfig(String appName, String threadPoolName, ThreadPoolExecutor threadPoolExecutor) {
        if (StringUtils.isBlank(appName) || StringUtils.isBlank(threadPoolName) || Objects.isNull(threadPoolExecutor)) {
            throw new IllegalArgumentException("missing required parameters: appName, threadPoolName or threadPoolExecutor");
        }
        // 进行线程池基本信息组装
        ThreadPoolConfig threadPoolConfig = new ThreadPoolConfig();
        threadPoolConfig.setAppName(appName);
        threadPoolConfig.setThreadPoolName(threadPoolName);
        threadPoolConfig.setCorePoolSize(threadPoolExecutor.getCorePoolSize());
        threadPoolConfig.setMaxPoolSize(threadPoolExecutor.getMaximumPoolSize());
        threadPoolConfig.setKeepAliveTime(threadPoolExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS));
        threadPoolConfig.setTimeUnit(TimeUnit.MILLISECONDS);
        threadPoolConfig.setWorkQueue(threadPoolExecutor.getQueue());
        threadPoolConfig.setThreadFactory(threadPoolExecutor.getThreadFactory());
        threadPoolConfig.setRejectedExecutionHandler(threadPoolExecutor.getRejectedExecutionHandler());
        return threadPoolConfig;
    }

    /**
     * 根据配置信息进行线程池调整
     *
     * @param threadPoolExecutor 待修改线程池配置信息
     * @param corePoolSize       待修改核心线程数
     * @param maxPoolSize        待修改最大线程数
     * @param workQueueSize      待修改工作队列长度
     */
    @SuppressWarnings("unchecked")
    public static void modifyThreadPool(ThreadPoolExecutor threadPoolExecutor, Integer corePoolSize, Integer maxPoolSize, Integer workQueueSize) {
        if (Objects.isNull(threadPoolExecutor) || Objects.isNull(corePoolSize) || Objects.isNull(maxPoolSize) || Objects.isNull(workQueueSize)) {
            throw new IllegalArgumentException("missing required parameters: threadPoolExecutor, corePoolSize, maxPoolSize or workQueueSize");
        }
        BlockingQueue<Runnable> oldWorkQueue = threadPoolExecutor.getQueue();
        try {
            Class<?> workQueueClass = Class.forName(oldWorkQueue.getClass().getName());
            BlockingQueue<Runnable> newWorkQueue = (BlockingQueue<Runnable>) workQueueClass.newInstance();
            modifyThreadPool(threadPoolExecutor, corePoolSize, maxPoolSize, newWorkQueue);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据给出的线程池主要配置对待修改的线程池进行内部属性修改
     *
     * @param threadPoolExecutor 待修改的线程池
     * @param corePoolSize       待修改核心线程数
     * @param maxPoolSize        待修改的最大线程数
     * @param workQueue          待修改的工作队列
     */
    public static void modifyThreadPool(ThreadPoolExecutor threadPoolExecutor, Integer corePoolSize, Integer maxPoolSize, BlockingQueue<Runnable> workQueue) {
        threadPoolExecutor.setCorePoolSize(corePoolSize);
        threadPoolExecutor.setMaximumPoolSize(maxPoolSize);
        // 进行阻塞队列内的待处理线程迁移
    }
}
