package cn.namelesser;

import cn.namelesser.domain.User;
import cn.namelesser.urils.ReadAccount;
import cn.namelesser.xsy.Account;
import cn.namelesser.xsy.Hearing;
import cn.namelesser.xsy.StartAnswering;
import cn.namelesser.xsy.Xsy;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import sun.jvm.hotspot.gc_implementation.g1.HeapRegion;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author huangzhenyong
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {


        ArrayList<Account> accounts = ReadAccount.getAccounts();
        System.out.println(accounts);
        ArrayList<User> users = new ArrayList<User>();
        for (Account account : accounts) {
            User user = new User(account.getAccount(), account.getPassword(), account.getAimTime(), account.getTreadNumb());
            users.add(user);
            for (int i = 1; i <= account.getTreadNumb(); i++) {
                ArrayList<CookieStore> cookieStores = user.getCookieStores();
                cookieStores.add(new BasicCookieStore());
            }

            if (!Xsy.log(user)) {
                users.remove(user);
            }


        }



        for (User user : users) {
            CookieStore cookieStore = user.getCookieStores().get(0);
            new StartAnswering(cookieStore).start();
        }

        Thread.sleep(1000*60);

    }
}
