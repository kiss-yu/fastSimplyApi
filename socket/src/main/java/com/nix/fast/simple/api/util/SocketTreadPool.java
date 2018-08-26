package com.nix.fast.simple.api.util;

import com.nix.fast.simple.api.util.log.LogKit;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author Kiss
 * @date 2018/08/23 14:01
 */
public final class SocketTreadPool {
    private final static Logger LOGGER = LogKit.getLog(SocketTreadPool.class);
    private static final LinkedBlockingDeque<Runnable> BLOCKING_DEQUE = new LinkedBlockingDeque<>(10000);
    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER = (r, executor) -> {
        LOGGER.warn("请求响应失败");
    };
    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(500, 500, 10, TimeUnit.SECONDS,
            BLOCKING_DEQUE,r -> {
        Thread thread = new Thread(r);
        thread.setName("socket");
        return thread;
    },REJECTED_EXECUTION_HANDLER);

    static {
        //建立hook 保证线程池关闭
        Runtime.getRuntime().addShutdownHook(new Thread(SocketTreadPool::shutdown));
    }

    /**
     * 提交任务
     * */
    public static <T> Future<T> submit(AcceptTask task, T result) {
        return POOL_EXECUTOR.submit(task,result);
    }

    /**
     * 执行任务
     * */
    public static void execute(AcceptTask task) {
        POOL_EXECUTOR.execute(task);
    }

    /**
     * 线程池关闭
     * */
    public static void shutdown() {
        List<Runnable> list = POOL_EXECUTOR.shutdownNow();
        if (list != null && !list.isEmpty()) {
            LOGGER.warn("未完结任务个" + list.size());
        }
        LOGGER.info("threadPool shutdown ");
    }
}
