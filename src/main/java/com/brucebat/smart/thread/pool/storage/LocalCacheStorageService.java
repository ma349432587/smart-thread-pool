package com.brucebat.smart.thread.pool.storage;

import com.brucebat.smart.thread.pool.common.ThreadPoolConfig;
import com.brucebat.smart.thread.pool.listener.impl.LocalThreadPoolConfigListener;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Objects;

/**
 * 本地缓存保存服务
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/29 10:24 AM
 */
public class LocalCacheStorageService {

    /**
     * 这里需要创建一个应用内全局唯一的缓存管理器
     */
    private final Cache<String, ThreadPoolConfig> CACHE_CONTAINER;

    private volatile static LocalCacheStorageService localCacheStorageService;

    private LocalCacheStorageService() {
        this.CACHE_CONTAINER = CacheBuilder.newBuilder().removalListener(new LocalThreadPoolConfigListener()).build();
    }

    public static LocalCacheStorageService getInstance() {
        if (Objects.isNull(localCacheStorageService)) {
            synchronized (LocalCacheStorageService.class) {
                if (Objects.isNull(localCacheStorageService)) {
                    localCacheStorageService = new LocalCacheStorageService();
                }
            }
        }
        return localCacheStorageService;
    }

    /**
     * 进行线程池配置信息保存到本地缓存中
     *
     * @param key              线程池本地缓存key
     * @param threadPoolConfig 线程池配置信息
     */
    public void put(String key, ThreadPoolConfig threadPoolConfig) {
        CACHE_CONTAINER.put(key, threadPoolConfig);
    }

    /**
     * 根据线程池配置信息本地缓存key进行缓存删除
     *
     * @param key 线程池配置本地缓存key
     */
    public void delete(String key) {
        CACHE_CONTAINER.invalidate(key);
    }

    /**
     * 根据本地缓存key获取线程池配置信息
     *
     * @param key 本地缓存key
     * @return 线程池配置信息
     */
    public ThreadPoolConfig get(String key) {
        return CACHE_CONTAINER.getIfPresent(key);
    }
}
