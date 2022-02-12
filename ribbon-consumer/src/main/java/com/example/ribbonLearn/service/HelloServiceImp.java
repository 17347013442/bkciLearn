package com.example.ribbonLearn.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/** @Author Mu_Mu @Description @Date 2022/1/8 */
@Service
public class HelloServiceImp {
  @Autowired
  RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod ="fallback",commandKey = "helloKey")
  public String helloService(){
    return restTemplate.
            getForEntity("http://hello-service/eurekaConsumer/hello?age=12",String.class).getBody();
  }
  public String fallback(){
   return "error";
  }
}
