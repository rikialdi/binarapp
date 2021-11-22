package com.binar.binar.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
@Component
public class SceduleCronJoob {
    @Autowired //backround [proccses
    @Qualifier(value = "taskExecutor")
    private TaskExecutor taskExecutor;

//    @Scheduled(cron = "${cron.expression:-}")
    @Scheduled(cron = "0/10 0/1 * 1/1 * ?") // diajlanak setiap 30 sekali : 0/10 0/1 * 1/1 * ?
    public void sendAsync() {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Method executed at every 10 detik Current time is :: " + new Date());
            }
        });
    }
}
