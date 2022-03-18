package com.brucebat.smart.thread.pool.factory;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 默认线程创建工厂
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/18 2:07 PM
 */
public class DefaultThreadFactory implements ThreadFactory {

    /**
     * 线程名称前缀
     */
    private final String namePrefix;
    /**
     * 线程组
     */
    private final ThreadGroup threadGroup;
    /**
     * 创建线程数量
     */
    private final AtomicInteger threadCount = new AtomicInteger(0);

    public DefaultThreadFactory(String namePrefix, ThreadGroup threadGroup) {
        SecurityManager s = System.getSecurityManager();
        ThreadGroup defaultThreadGroup = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = null == namePrefix || namePrefix.length() == 0 ? "defaultThread" : namePrefix;
        this.threadGroup = Objects.nonNull(threadGroup) ? threadGroup : defaultThreadGroup;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(threadGroup, r, namePrefix + "-" + threadCount.getAndIncrement(), 0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        return thread;
    }
}
