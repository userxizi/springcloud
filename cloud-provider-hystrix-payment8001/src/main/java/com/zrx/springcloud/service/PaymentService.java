package com.zrx.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);
}
