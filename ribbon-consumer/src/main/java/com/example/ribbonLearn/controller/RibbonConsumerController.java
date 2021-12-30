package com.example.ribbonLearn.controller;

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
@RequestMapping("/ribbonConsumer")
public class RibbonConsumerController {
   public static final  Logger Log = LoggerFactory.getLogger("ConsumerController");
   @Autowired
    private RestTemplate restTemplate;

   @RequestMapping("/index")
    public String index(){
       String msg = restTemplate.
               getForObject("http://HELLO-SERVICE/eurekaConsumer/hello",String.class);
       return msg;
   }

}
