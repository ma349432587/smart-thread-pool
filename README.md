# SmartThreadPool——动态线程池工具

## 简介

- 需要提供线程池创建的基本方法
- 需要提供线程池管理的能力（提供基本本地缓存、Redis、Zookeeper、Nacos以及etcd等存储的管理能力）
    - 提供线程池注册能力
    - 提供线程池配置信息动态修改能力
        - 修改线程池corePoolSize、maxPoolSize以及阻塞队列；
        - 进行阻塞队列当中任务信息迁移操作；
    - 提供线程池删除能力