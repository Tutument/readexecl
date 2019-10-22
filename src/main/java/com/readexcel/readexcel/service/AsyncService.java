package com.readexcel.readexcel.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    public static void haha(){
        System.out.println("内部类对象调用静态方法");
    }
    @Async
    public void testNoRespNoParamAsync() {
        try {
            Thread.sleep(1000);
            System.out.println("haha");
        } catch (InterruptedException e) {

            return;
        }
    }

}
