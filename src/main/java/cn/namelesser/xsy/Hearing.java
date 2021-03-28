package cn.namelesser.xsy;
import cn.namelesser.domain.User;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author 42169
 */
public class Hearing extends Thread {


   User user;
    CookieStore cookieStore;

    public Hearing(User user, CookieStore cookieStore) {
        this.user = user;
        this.cookieStore = cookieStore;
    }

    @Override


    public void run() {
            while (user.getNowTime() <= user.getAimTime()) {
                Date pDate = new Date();
                System.out.println("线程：" + getName());
                user.addNowTime(0.0041   );
                System.out.println(user.getNowTime()+":"+user.getAimTime());
                CloseableHttpResponse fangwen = Xsy.fangwen(cookieStore);
                try {
                    Thread.sleep(1000 * 15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    fangwen.close();
                    Xsy.fangwen(cookieStore).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Date nDate = new Date();
                long l = nDate.getTime()-pDate.getTime()  - 1000 * 15;
                if(l>0)
                {
                    System.out.println("超时" + l);
                    user.addNowTime(l*0.00000027);
                }else {

                    System.out.println(pDate.getTime()+":"+nDate.getTime());
                    System.out.println("l:"+l);
                }


            }
        System.out.println("线程"+getName()+"结束");



    }

}



