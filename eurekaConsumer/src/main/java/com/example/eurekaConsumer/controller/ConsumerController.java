package com.example.eurekaConsumer.controller;

import com.example.eurekaConsumer.model.po.UserPO;
import com.example.eurekaConsumer.service.ValidType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/** @Author Mu_Mu @Description @Date 2021/12/30 */
@RestController
@RequestMapping("/eurekaConsumer")
public class ConsumerController {
  @Autowired private DiscoveryClient client;

  @Autowired private RedisTemplate redisTemplate;

  @Value("${server.port}")
  private String port;

  public static final Logger Log = LoggerFactory.getLogger("ConsumerController");

  @RequestMapping("/index")
  public String index(@RequestParam("age")String age) {
    List<String> services = client.getServices();
    Log.info("---------services-----:", Arrays.toString(services.toArray()));
    return "age:"+age+" port: "+port;
  }

  @RequestMapping("/hello")
  public String hello() {
    long startTimeMillis = System.currentTimeMillis();
     int sleepTime = new Random().nextInt(5000);
    try {
      Thread.sleep(sleepTime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    long endTimeMillis = System.currentTimeMillis();
    return "success： "+(endTimeMillis-startTimeMillis);
  }

  @RequestMapping("/test")
  public String test() {
     SetOperations setOperations = redisTemplate.opsForSet();
     HashSet<Integer> hashSet = new HashSet<Integer>(200);
     for(int i=0;i<10;i++){
       hashSet.add(i);
     }
    setOperations.add("member",hashSet);
    final Object member = setOperations.randomMember("member");
     Set member1 = setOperations.members("member");
    System.out.println(Arrays.toString(member1.toArray()));

    return "";
  }
@GetMapping("testSession")
  public void testSession(HttpServletRequest servletRequest, HttpServletResponse response){
    //会自动在cokkie中创建jsessionid，每次请求都会带上cookie。
  final HttpSession session = servletRequest.getSession();
    System.out.println(String.valueOf(session.getAttribute("test")));
  session.setAttribute("test",1111);
  System.out.println(String.valueOf(session.getAttribute("test")));
    System.out.println(session);
   Cookie cookie = new Cookie("test","tttt");
   cookie.setMaxAge(1000);
  response.addCookie(cookie);
    System.out.println(response.encodeURL("/send"));
}

@GetMapping("/testDeamThread")
  public void testDeamThread(){
    System.out.println("testDeamThread kaisi");
  Thread thread = new Thread(
          () -> {
            while (true){
              System.out.println("xianc");
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
          },
          "t1");
  thread.start();
    System.out.println("jiesu");
}
  @PostMapping("/testDeam/testVaild")
  public void testVaild(@Validated(ValidType.Add.class) @RequestBody UserPO userPO){
    System.out.println(userPO);
  }

  @GetMapping("/testDeam/testGet")
  public void testGet(@Validated(ValidType.Update.class)  UserPO userPO){
    System.out.println(userPO);
  }

}
