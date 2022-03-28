package com.brucebat.smart.thread.pool.listener.impl;

import com.brucebat.smart.thread.pool.common.ThreadPoolConfig;
import com.brucebat.smart.thread.pool.listener.ThreadPoolConfigListener;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * 本地线程池配置监听器
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/28 5:58 PM
 */
public class LocalThreadPoolConfigListener implements ThreadPoolConfigListener, RemovalListener<String, ThreadPoolConfig> {
    @Override
    public void modifyThreadPool(String appName, String threadPoolName, ThreadPoolConfig threadPoolConfig) {

    }

    @Override
    public void onRemoval(RemovalNotification<String, ThreadPoolConfig> notification) {
        if (RemovalCause.REPLACED.equals(notification.getCause())) {

        }
    }
}
