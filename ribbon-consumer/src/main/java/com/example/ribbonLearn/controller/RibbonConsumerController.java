package com.example.ribbonLearn.controller;

import com.example.ribbonLearn.service.HelloServiceImp;
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

   @Autowired
    HelloServiceImp helloServiceImp;

   @RequestMapping("/index")
    public String index(){
       String msg = restTemplate.
               getForEntity("http://hello-service/eurekaConsumer/hello?age=12",String.class).getBody();
       return msg;
   }

   @RequestMapping("/hello")
    public String hello(){
       return helloServiceImp.helloService();
   }
}
