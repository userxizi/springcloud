package com.zrx.springcloud.controller;

import com.zrx.springcloud.entities.CommonResult;
import com.zrx.springcloud.entities.Payment;
import com.zrx.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL="http://localhost:8001";
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("=========客户端调用服务端 添加=========="+payment);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") long id){
        log.info("=========客户端调用服务端 查询=========="+id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") long id){
        log.info("=========客户端调用服务端 查询=========="+id);
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
           return new CommonResult<>(444,"查询操作失败");
        }
    }

//    @GetMapping("/consumer/payment/postForEntity")
//    public CommonResult<Payment> create1(Payment payment){
//        log.info("=========客户端调用服务端 添加=========="+payment);
//        CommonResult body = restTemplate.postForEntity(PAYMENT_URL + "/payment/get/", payment, CommonResult.class).getBody();
//        log.info("=========客户端调用服务端 添加=========="+body);
//        return body;
//    }
//
//    @GetMapping("/consumer/payment/postForObject")
//    public CommonResult<Payment> create2(Payment payment){
//        log.info("=========客户端调用服务端 添加=========="+payment);
//        CommonResult body = restTemplate.postForObject(PAYMENT_URL + "/payment/get/", payment, CommonResult.class);
//        log.info("=========客户端调用服务端 添加=========="+body);
//        return body;
//    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(PAYMENT_URL+"/payment/lb",String.class);
    }


    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }
}
