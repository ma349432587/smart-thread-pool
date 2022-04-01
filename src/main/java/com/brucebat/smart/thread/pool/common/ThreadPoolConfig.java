package com.brucebat.smart.thread.pool.common;

import com.brucebat.smart.thread.pool.factory.DefaultThreadFactory;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * 线程池配置信息
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/17 5:24 PM
 */
public class ThreadPoolConfig implements Serializable {

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 线程池名称
     */
    private String threadPoolName;
    /**
     * 线程池中核心线程数
     */
    private Integer corePoolSize;
    /**
     * 线程池中最大线程数
     */
    private Integer maxPoolSize;
    /**
     * 空闲线程存活时间，此处空闲线程指的是线程池内数量超过corePoolSize部分的线程
     */
    private Long keepAliveTime;
    /**
     * 线程存活时间的单位
     */
    private TimeUnit timeUnit;
    /**
     * 工作队列
     */
    private BlockingQueue<Runnable> workQueue;
    /**
     * 线程拒绝策略处理器
     */
    private RejectedExecutionHandler rejectedExecutionHandler;
    /**
     * 线程工程
     */
    private ThreadFactory threadFactory;

    public ThreadPoolConfig() {
    }

    /**
     * 创建线程池配置信息对象
     *
     * @param corePoolSize             核心线程数量
     * @param maxPoolSize              线程池所能维持的最大线程数量
     * @param keepAliveTime            线程数量超过核心线程数量部分的空闲线程存活时间
     * @param timeUnit                 存活时间的时间单位
     * @param workQueue                工作队列
     * @param rejectedExecutionHandler 线程池中线程数量和工作队列数量均达到最大时的拒绝策略执行器
     * @param threadFactory            线程创建工厂
     */
    public ThreadPoolConfig(Integer corePoolSize, Integer maxPoolSize, Long keepAliveTime, TimeUnit timeUnit, BlockingQueue<Runnable> workQueue,
                            RejectedExecutionHandler rejectedExecutionHandler, ThreadFactory threadFactory) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.workQueue = workQueue;
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        this.threadFactory = threadFactory;
    }

    /**
     * 创建线程池配置信息对象
     *
     * @param corePoolSize             核心线程数量
     * @param maxPoolSize              线程池所能维持的最大线程数量
     * @param keepAliveTime            线程数量超过核心线程数量部分的空闲线程存活时间
     * @param timeUnit                 存活时间的时间单位
     * @param queueCapacity            工作队列容量
     * @param rejectedExecutionHandler 线程池中线程数量和工作队列数量均达到最大时的拒绝策略执行器
     * @param threadFactory            线程创建工厂
     */
    public ThreadPoolConfig(Integer corePoolSize, Integer maxPoolSize, Long keepAliveTime, TimeUnit timeUnit, Integer queueCapacity,
                            RejectedExecutionHandler rejectedExecutionHandler, ThreadFactory threadFactory) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.workQueue = Objects.nonNull(queueCapacity) && queueCapacity > 0 ? new LinkedBlockingQueue<>(queueCapacity) : new SynchronousQueue<>();
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        this.threadFactory = threadFactory;
    }

    /**
     * 创建线程池配置信息对象
     *
     * @param corePoolSize             核心线程数量
     * @param maxPoolSize              线程池所能维持的最大线程数量
     * @param keepAliveTime            线程数量超过核心线程数量部分的空闲线程存活时间
     * @param timeUnit                 存活时间的时间单位
     * @param rejectedExecutionHandler 线程池中线程数量和工作队列数量均达到最大时的拒绝策略执行器
     * @param threadNamePrefix         线程名称前缀
     * @param threadGroup              线程组
     */
    public ThreadPoolConfig(Integer corePoolSize, Integer maxPoolSize, Long keepAliveTime, TimeUnit timeUnit,
                            RejectedExecutionHandler rejectedExecutionHandler, String threadNamePrefix, ThreadGroup threadGroup) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        this.threadFactory = new DefaultThreadFactory(threadNamePrefix, threadGroup);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getThreadPoolName() {
        return threadPoolName;
    }

    public void setThreadPoolName(String threadPoolName) {
        this.threadPoolName = threadPoolName;
    }

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public BlockingQueue<Runnable> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
    }

    public String getWorkQueueName() {
        return this.workQueue.getClass().getName();
    }

    public RejectedExecutionHandler getRejectedExecutionHandler() {
        return rejectedExecutionHandler;
    }

    public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
    }

    public String getRejectedExecutionHandlerName() {
        return this.rejectedExecutionHandler.getClass().getName();
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public String getThreadPoolFactoryName() {
        return this.threadFactory.getClass().getName();
    }
}
