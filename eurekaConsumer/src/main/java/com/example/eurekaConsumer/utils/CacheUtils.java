package com.example.eurekaConsumer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/** @Author Mu_Mu @Description @Date 2022/1/18 */
public class CacheUtils {
  private static final ConcurrentHashMap<String, Object> map;
  private static final int MAX_CAPACITY = 10000;
  private static final int DEFAULT_SIZE = 1024;
  private static final ScheduledExecutorService executor;
  private static final Logger LOG =LoggerFactory.getLogger(CacheUtils.class);
  static {
    map = new ConcurrentHashMap<String, Object>(DEFAULT_SIZE);

    executor = new ScheduledThreadPoolExecutor(1);
  }

  public static Object put(String key, Object value) {
    if (checkCapacity()) {
      return map.put(key, value);
    } else {
      return null;
    }
  }

  public static Object putExpirationTimeKey(String key, Object value, Long delay, TimeUnit unit) {
    if (checkCapacity()) {
      map.put(key, value);
      executor.schedule(new ClearTask(key), delay, unit);
      return value;
    } else {
      return null;
    }
  }

  public static Object get(String key) {
    return map.get(key);
  }



  public static Object remove(String key) {
    return map.remove(key);
  }

  public static boolean checkCapacity() {
    return map.size() + 1 <= MAX_CAPACITY;
  }
    static class ClearTask implements Runnable {
        String key;

        public ClearTask(String key) {
            this.key = key;
        }

        @Override
        public void run() {
            LOG.info("开始删除key："+key);
            remove(key);
        }
    }

  public static void main(String[] args) {

   /* for (int i = 0; i < 2; i++) {
        if(i==0){
        CacheUtils.putExpirationTimeKey("test",i,2L,TimeUnit.SECONDS);
        }
      new Thread(
              new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+CacheUtils.get("test"));
                }
              },"第"+i+"个线程：")
          .start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

       ExecutorService executorService = Executors.newFixedThreadPool(4);


  }
}
