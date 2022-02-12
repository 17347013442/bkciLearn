package com.example.ribbonLearn;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Mu_Mu
 * @Description
 * @Date 2021/12/30
 */
@SpringCloudApplication
public class RibbonConsumerApplication {
    @Bean
    @LoadBalanced//开启客户端负载均衡。
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }
}


