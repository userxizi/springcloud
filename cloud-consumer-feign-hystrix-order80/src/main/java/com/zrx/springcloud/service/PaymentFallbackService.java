package com.zrx.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK ,这里是消费者80 对方服务器已经宕机 (┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "paymentInfo_TimeOut 这里是消费者80 对方服务器已经宕机 QAQ";
    }
}
