package com.example.eurekaConsumer.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author Mu_Mu
 * @Description
 * @Date 2022/1/3
 */
@Service("consumerImpl")
public class ConsumerImpl {
    @Cacheable(value = "consumer")
    public String getPort(){

        return "hallo:1111";
    }
}
