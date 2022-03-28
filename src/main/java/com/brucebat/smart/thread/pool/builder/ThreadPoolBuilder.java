package com.brucebat.smart.thread.pool.builder;

import com.brucebat.smart.thread.pool.factory.DefaultThreadFactory;
import com.brucebat.smart.thread.pool.registry.ThreadPoolRegistrar;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * 线程池创建者类，用于进行线程池创建
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/23 11:05 AM
 */
public class ThreadPoolBuilder implements Serializable {

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
    private Integer keepAliveTime;
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

    /**
     * 线程池注册器
     */
    private ThreadPoolRegistrar threadPoolRegistrar;

    private static final int DEFAULT_QUEUE_CAPACITY = 1000;

    public ThreadPoolBuilder() {
    }

    /**
     * 获取线程池创建者对象
     *
     * @return 线程池创建者对象
     */
    public static ThreadPoolBuilder builder() {
        return new ThreadPoolBuilder();
    }

    public ThreadPoolBuilder corePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public ThreadPoolBuilder maxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    public ThreadPoolBuilder keepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public ThreadPoolBuilder timeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    public ThreadPoolBuilder workQueue(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        return this;
    }

    public ThreadPoolBuilder rejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        return this;
    }

    public ThreadPoolBuilder threadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    public ThreadPoolBuilder threadPoolRegistrar(ThreadPoolRegistrar threadPoolRegistrar) {
        this.threadPoolRegistrar = threadPoolRegistrar;
        return this;
    }

    /**
     * 进行线程池的实际创建
     *
     * @return 创建完成线程池
     */
    public ThreadPoolExecutor build() {
        if (Objects.isNull(this.corePoolSize) || this.corePoolSize < 0) {
            throw new IllegalArgumentException("corePoolSize doesn't exist or corePoolSize is less than 0");
        }
        if (Objects.isNull(this.maxPoolSize) || this.maxPoolSize < 0) {
            throw new IllegalArgumentException("maxPoolSize doesn't exist or maxPoolSize is less than 0");
        }
        if (Objects.isNull(keepAliveTime) || this.keepAliveTime < 0) {
            throw new IllegalArgumentException("keepAliveTime doesn't exist or keepAliveTime is less than 0");
        }
        // 进行线程池创建
        if (null == this.timeUnit) {
            this.timeUnit = TimeUnit.SECONDS;
        }
        if (null == this.workQueue) {
            this.workQueue = new LinkedBlockingQueue<>(DEFAULT_QUEUE_CAPACITY);
        }
        if (null == this.rejectedExecutionHandler) {
            this.rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        }
        if (null == this.threadFactory) {
            this.threadFactory = new DefaultThreadFactory(null, null);
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(this.corePoolSize, this.maxPoolSize, this.keepAliveTime, this.timeUnit, this.workQueue, this.threadFactory, this.rejectedExecutionHandler);
        if (Objects.nonNull(threadPoolRegistrar)) {
            // TODO 是否应该将注册的逻辑放置在这里
            threadPoolRegistrar.register(this.appName, this.threadPoolName);
        }
        return threadPoolExecutor;
    }
}
