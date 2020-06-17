package com.zrx.springcloud.controller;

import com.zrx.springcloud.service.PaymentFeignService;
import com.zrx.springcloud.entities.CommonResult;
import com.zrx.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //oepnfeign-ribbon 客户端默认一秒钟拿到结果
        return paymentFeignService.paymentFeignTimeout();
    }
}
