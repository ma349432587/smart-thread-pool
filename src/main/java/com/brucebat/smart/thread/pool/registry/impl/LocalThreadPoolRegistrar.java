package com.brucebat.smart.thread.pool.registry.impl;

import com.brucebat.smart.thread.pool.common.ThreadPoolConfig;
import com.brucebat.smart.thread.pool.registry.AbstractThreadPoolRegistrar;
import com.brucebat.smart.thread.pool.storage.LocalCacheStorageService;

/**
 * 默认线程池注册器--使用本地缓存作为线程池注册信息的存储空间
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/28 11:31 AM
 */
public class LocalThreadPoolRegistrar extends AbstractThreadPoolRegistrar {

    private final LocalCacheStorageService localCacheStorageService;

    public LocalThreadPoolRegistrar(LocalCacheStorageService localCacheStorageService) {
        this.localCacheStorageService = localCacheStorageService;
    }

    @Override
    protected void registerToStorage(String threadPoolKey, ThreadPoolConfig threadPoolConfig) {
        localCacheStorageService.put(threadPoolKey, threadPoolConfig);
    }

    @Override
    protected void deregisterFromStorage(String threadPoolKey) {
        localCacheStorageService.delete(threadPoolKey);
    }

    @Override
    protected ThreadPoolConfig getRemoteConfig(String threadPoolKey) {
        return localCacheStorageService.get(threadPoolKey);
    }
}
