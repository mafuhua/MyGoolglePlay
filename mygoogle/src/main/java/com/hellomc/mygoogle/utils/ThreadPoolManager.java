package com.hellomc.mygoogle.utils;

import android.annotation.SuppressLint;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理类
 *
 * @author wangdh
 */
public class ThreadPoolManager {
    private static ThreadPoolManager instance = new ThreadPoolManager();

    //单例
    private ThreadPoolManager() {
    }

    public static ThreadPoolManager getInstance() {
        return instance;
    }

    /**
     * 操作本地耗时操作的线程池
     * cpu*2+1
     *
     * @return
     */
    public ThreadPoolPoxy createLoaclThreadPool() {
        ThreadPoolPoxy threadPoolPoxy = new ThreadPoolPoxy(3, 3, 5 * 1000);
        return threadPoolPoxy;
    }

    /**
     * 操作网络耗时操作的线程池
     *
     * @return
     */
    public ThreadPoolPoxy createNetThreadPool() {
        ThreadPoolPoxy threadPoolPoxy = new ThreadPoolPoxy(5, 5, 5 * 1000);
        return threadPoolPoxy;
    }

    //线程池代理类
    public class ThreadPoolPoxy {
        private ThreadPoolExecutor threadPool;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        public ThreadPoolPoxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        @SuppressLint("NewApi")
        public void execute(Runnable runnable) {
            if (threadPool == null) {
                /**
                 * corePoolSize:线程池大小，内部维护几个线程 3
                 * maximumPoolSize：当前存储任务代码块的集合都满了之后，会额外开启的线程数量
                 * keepAliveTime：当前没有可执行任务代码块Runnable，还可以存活多久
                 * unit: 存活时间单位
                 * LinkedBlockingDeque:当前线程池中的线程都处于工作中是，剩余的任务存储的集合（可存储个数：10）
                 */
                threadPool = new ThreadPoolExecutor(
                        corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS, //毫秒
                        new LinkedBlockingDeque<Runnable>(10));
            }
            threadPool.execute(runnable);
        }
    }

}
