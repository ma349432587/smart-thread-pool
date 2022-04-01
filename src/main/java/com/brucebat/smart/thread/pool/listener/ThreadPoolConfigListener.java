package com.brucebat.smart.thread.pool.listener;

import com.brucebat.smart.thread.pool.common.ThreadPoolConfig;

/**
 * 线程池监听器，用于监听动态配置信息变更，根据动态配置变更情况而变更线程池中实际的配置
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/3/28 11:34 AM
 */
public interface ThreadPoolConfigListener {

    /**
     * 根据应用名称、线程池名称以及线程池配置信息进行线程池修改
     *
     * @param appName          应用名称
     * @param threadPoolName   线程池名称
     * @param threadPoolConfig 待更新线程池配置信息
     */
    void modifyThreadPool(String appName, String threadPoolName, ThreadPoolConfig threadPoolConfig);

}
