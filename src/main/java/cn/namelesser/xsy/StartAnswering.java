package cn.namelesser.xsy;

import cn.namelesser.domain.User;
import org.apache.http.client.CookieStore;

import java.io.IOException;

/**
 * @author 42169
 */
public class StartAnswering extends Thread{

    private final CookieStore cookieStore;

    public StartAnswering(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    @Override
    public void run() {


        try {

            Xsy.a(cookieStore);
            Thread.sleep(1000);
        } catch (Exception ignored){

        }
    }
}
