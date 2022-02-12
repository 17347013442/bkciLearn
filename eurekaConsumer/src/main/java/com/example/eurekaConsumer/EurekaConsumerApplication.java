package com.example.eurekaConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableCaching
public class EurekaConsumerApplication {

//    @Bean
//    @LoadBalanced //开启客户端负载均衡。
//    RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

}
