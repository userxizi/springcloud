package com.zrx.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zrx.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_OK,id:        "+ id +"\t"+"呵呵=-=";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //3秒钟以内就是正常的业务逻辑
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")

    })
    public String paymentInfo_TimeOut(Integer id) {

        int timeNumber=5;
        //int 10=10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_TimeOut服务端,id:        "+ id +"\t"+"呵呵=-="+"耗时(秒)"+timeNumber;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_TimeOutHandler服务端,id:        "+ id +"\t"+"QAQ服务器繁忙，请稍后再试！！！";
    }
}
