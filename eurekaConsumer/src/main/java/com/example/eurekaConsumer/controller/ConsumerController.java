package com.example.eurekaConsumer.controller;

import com.netflix.discovery.DiscoveryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Mu_Mu
 * @Description
 * @Date 2021/12/30
 */
@RestController
@RequestMapping("/eurekaConsumer")
public class ConsumerController {
   public static final  Logger Log = LoggerFactory.getLogger("ConsumerController");
   @RequestMapping("/hello")
    public String index(){
       return "hallo";
   }

}
