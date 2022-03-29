package com.brucebat.smart.thread.pool.registry;

import com.brucebat.smart.thread.pool.common.ThreadPoolConfig;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/29 11:21 AM
 */
public abstract class AbstractThreadPoolRegistrar implements ThreadPoolRegistrar {

    private static final String KEY_SPLIT = "#";


    /**
     * 根据线程池应用名称和线程池名称以及线程池进行注册
     *
     * @param appName            线程池应用名称
     * @param threadPoolName     线程池名称
     * @param threadPoolExecutor 线程池
     */
    @Override
    public void register(String appName, String threadPoolName, ThreadPoolExecutor threadPoolExecutor) {
        ThreadPoolConfig threadPoolConfig = new ThreadPoolConfig();
        // TODO 根据线程池进行线程池配置信息组装
        registerToStorage(appName + KEY_SPLIT + threadPoolName, threadPoolConfig);
    }

    /**
     * 根据线程池应用名称和线程池名称进行线程池注销
     *
     * @param appName        线程池应用名称
     * @param threadPoolName 线程池名称
     */
    @Override
    public void deregister(String appName, String threadPoolName) {
        deregisterFromStorage(appName + KEY_SPLIT + threadPoolName);
    }

    /**
     * 根据线程池应用名称和线程池名称获取线程池配置信息
     *
     * @param appName        线程池应用名称
     * @param threadPoolName 线程池名称
     * @return 线程池配置信息
     */
    @Override
    public ThreadPoolConfig getConfig(String appName, String threadPoolName) {
        String threadPoolKey = appName + KEY_SPLIT + threadPoolName;
        ThreadPoolConfig remoteConfig = getRemoteConfig(threadPoolKey);
        // 如果远程没有获取到则获取本地的配置信息
        return null;
    }

    /**
     * 获取所有的线程池配置信息
     *
     * @param appName 获取当前pod当中所有线程池的配置信息
     * @return 获取所有线程池配置信息
     */
    @Override
    public List<ThreadPoolConfig> getAllThreadPools(String appName) {
        return null;
    }

    /**
     * 将线程池key注册到注册中心存储中
     *
     * @param threadPoolKey    线程池key
     * @param threadPoolConfig 待注册线程池配置信息
     */
    protected abstract void registerToStorage(String threadPoolKey, ThreadPoolConfig threadPoolConfig);

    /**
     * 将线程池配置信息从注册中心存储中删除
     *
     * @param threadPoolKey 线程池key
     */
    protected abstract void deregisterFromStorage(String threadPoolKey);

    /**
     * 根据线程池key获取注册中心中线程池配置信息
     *
     * @param threadPoolKey 线程池对应key
     * @return 线程池配置信息
     */
    protected abstract ThreadPoolConfig getRemoteConfig(String threadPoolKey);
}
