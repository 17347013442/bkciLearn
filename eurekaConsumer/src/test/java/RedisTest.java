/**
 * @Author Mu_Mu
 * @Description
 * @Date 2022/1/25
 */

public class RedisTest {
  public static void main(String[] args) {
    System.out.println("kaisi");
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
//      thread.setDaemon(true);
      thread.start();
      System.out.println("jiesu");
  }
}
