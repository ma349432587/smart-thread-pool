package com.brucebat.smart.thread.pool.registry;

import com.brucebat.smart.thread.pool.common.ThreadPoolConfig;
import com.brucebat.smart.thread.pool.common.ThreadPoolContainer;
import com.brucebat.smart.thread.pool.common.utils.ThreadPoolUtils;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 抽象线程池注册器
 *
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
        ThreadPoolContainer.addThreadPool(appName, threadPoolName, threadPoolExecutor);
        ThreadPoolConfig threadPoolConfig = ThreadPoolUtils.assembleConfig(appName, threadPoolName, threadPoolExecutor);
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
        ThreadPoolContainer.removeThreadPool(appName, threadPoolName);
        deregisterFromStorage(appName + KEY_SPLIT + threadPoolName);
    }

    @Override
    public void refresh(String appName, String threadPoolName, ThreadPoolExecutor threadPoolExecutor) {
        ThreadPoolConfig threadPoolConfig = ThreadPoolUtils.assembleConfig(appName, threadPoolName, threadPoolExecutor);
        refreshToStorage(appName + KEY_SPLIT + threadPoolName, threadPoolConfig);
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
     * 根据线程池key和待更新线程池配置信息进行远程存储中的线程池配置信息更新
     *
     * @param threadPoolKey    线程池key
     * @param threadPoolConfig 线程池配置信息
     */
    protected abstract void refreshToStorage(String threadPoolKey, ThreadPoolConfig threadPoolConfig);

}
