package com.zrx.springcloud.controller;

import com.zrx.springcloud.domain.CommonResult;
import com.zrx.springcloud.domain.Order;
import com.zrx.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController{
    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        Order oi=new Order();
//        oi.setId(1l);
//        oi.setUserId(1l);
//        oi.setCount(1);
//        oi.setMoney(BigDecimal.valueOf(100.0));
          orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}