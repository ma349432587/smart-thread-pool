package com.brucebat.smart.thread.pool.registry;

import com.brucebat.smart.thread.pool.common.ThreadPoolConfig;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池注册器，用于将线程池信息注册到动态配置中心，
 * 方便后续在注册中心中进行对应的动态配置操作
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/28 10:35 AM
 */
public interface ThreadPoolRegistrar {

    /**
     * 根据应用名称和线程池名称进行线程池注册，并注册动态监听器
     *
     * @param appName            应用名称
     * @param threadPoolName     线程池名称
     * @param threadPoolExecutor 待注册线程池
     */
    void register(String appName, String threadPoolName, ThreadPoolExecutor threadPoolExecutor);

    /**
     * 根据应用名称和线程池名称进行线程池注销
     *
     * @param appName        应用名称
     * @param threadPoolName 线程池名称
     */
    void deregister(String appName, String threadPoolName);

    /**
     * 根据应用名称和线程池名称获取线程池配置信息
     *
     * @param appName        应用名称
     * @param threadPoolName 线程池名称
     * @return 线程池配置信息
     */
    ThreadPoolConfig getConfig(String appName, String threadPoolName);

    /**
     * 根据应用名称获取所有线程池配置信息
     *
     * @param appName 应用名称
     * @return 线程池配置信息
     */
    List<ThreadPoolConfig> getAllThreadPools(String appName);
}
