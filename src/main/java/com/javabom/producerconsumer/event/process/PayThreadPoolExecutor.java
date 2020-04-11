package com.javabom.producerconsumer.event.process;

import com.javabom.producerconsumer.event.message.PayType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Semaphore;

//todo 그레이스풀 셧다운
@Slf4j
public class PayThreadPoolExecutor {
    private final Semaphore semaphore;
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public PayThreadPoolExecutor(PayType payType) {
        this.threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        this.semaphore = new Semaphore(payType.getMaxPoolSize());
        threadPoolTaskExecutor.setThreadNamePrefix("thread_" + payType.getEventClass().getSimpleName());
        threadPoolTaskExecutor.setCorePoolSize(payType.getCorePoolSize()); //수행 풀
        threadPoolTaskExecutor.setMaxPoolSize(payType.getMaxPoolSize()); // 큐에 추가되면 맥스까지 늘어남
        threadPoolTaskExecutor.setQueueCapacity(payType.getQueueCapacity()); // 대기할 수 있는 작업 수
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.initialize();
    }

    public void executeJob(Runnable receiveJob) {
        try {
            semaphore.acquire();
            threadPoolTaskExecutor.execute(() -> execute(receiveJob));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void execute(Runnable job) {
        try {
            job.run();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            semaphore.release();
        }
    }
}
