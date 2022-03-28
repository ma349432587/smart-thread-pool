package com.brucebat.smart.thread.pool.registry.impl;

import com.brucebat.smart.thread.pool.config.ThreadPoolConfig;
import com.brucebat.smart.thread.pool.registry.ThreadPoolRegistrar;

import java.util.List;

/**
 * 默认线程池注册器--使用本地缓存作为线程池注册信息的存储空间
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/28 11:31 AM
 */
public class DefaultThreadPoolRegistrar implements ThreadPoolRegistrar {


    @Override
    public void register(String appName, String threadPoolName) {

    }

    @Override
    public void deregister(String appName, String threadPoolName) {

    }

    @Override
    public ThreadPoolConfig getConfig(String appName, String threadPoolName) {
        return null;
    }

    @Override
    public List<ThreadPoolConfig> getAllThreadPools(String appName) {
        return null;
    }
}
