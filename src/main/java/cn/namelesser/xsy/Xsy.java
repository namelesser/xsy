package cn.namelesser.xsy;


import cn.namelesser.domain.User;
import cn.namelesser.urils.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Xsy {
    private static String charset = "utf8";

    private static String host = "http://xsy.qqhru.edu.cn/";


    public static CloseableHttpResponse fangwen(CookieStore cookieStore) {
        return HttpUtil.doGet("http://xsy.qqhru.edu.cn/book/book144/index.php?Quiz=N&whichActionPage=", cookieStore);

    }

    public static int getTime(CookieStore cookieStore) {
        final String cssQuery = "#table-1 > tfoot > tr:nth-child(2) td:nth-child(2)";
        CloseableHttpResponse response = HttpUtil.doGet("http://xsy.qqhru.edu.cn/login/hponlinetime.php", cookieStore);

        HttpEntity entity = response.getEntity();
        try {
            String s = EntityUtils.toString(entity, "utf8");
            Document document = Jsoup.parse(s);
            Elements elements = document.select(cssQuery);
            System.out.println(s);



            String[] split = elements.first().text().split(":");
            response.close();
            return Integer.parseInt(split[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 999;
    }


    public static void a(CookieStore cookieStore) throws IOException, InterruptedException {


        List<NameValuePair> basicNameValuePairs = null;
        InputStream resourceAsStream = Xsy.class.getClassLoader().getResourceAsStream("english4.txt");
        assert resourceAsStream != null;
        Scanner scanner = new Scanner(resourceAsStream);
        boolean flag = true;
        String url = "";
        while (scanner.hasNext()) {
            String str = scanner.next();
            if (flag) {
                url = str;
                basicNameValuePairs = new ArrayList<NameValuePair>();

            } else {
                String[] splits = str.split("&");
                for (String split : splits) {
                    String[] split1 = split.split("=");
                    String name = split1[0];
                    String value = "";
                    if (split1.length == 2) {
                        value = split1[1].replace('+', ' ');

                    }
                    basicNameValuePairs.add(new BasicNameValuePair(name, value));

                }
                System.out.println(url + basicNameValuePairs);
                CloseableHttpResponse closeableHttpResponse = HttpUtil.doPost(url, basicNameValuePairs, charset, cookieStore);
                HttpEntity entity = closeableHttpResponse.getEntity();
                String s = EntityUtils.toString(entity, "utf8");

                closeableHttpResponse.close();
                Thread.sleep(500);
            }

            flag = !flag;
        }


    }

    public static boolean log(User user) throws IOException, InterruptedException {

        ArrayList<CookieStore> cookieStores = user.getCookieStores();
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("username", user.getAccount()));
        nameValuePairs.add(new BasicNameValuePair("password", user.getPassword()));
        nameValuePairs.add(new BasicNameValuePair("Input2", "%E7%99%BB+%E5%BD%95"));
        int i = 1;
        for (CookieStore cookieStore : cookieStores) {
            System.out.println("正在登陆" + i++);
            HttpUtil.doGet("http://xsy.qqhru.edu.cn/index.php", cookieStore).close();
            HttpResponse response = HttpUtil.doPost("http://xsy.qqhru.edu.cn/index.php", nameValuePairs, charset, cookieStore);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity, charset);


            if (s.contains("Loadin")) {
                if(i==2)
                {
                    user.setNowTime(Xsy.getTime(user.getCookieStores().get(0)));
                }

                CloseableHttpResponse closeableHttpResponse = HttpUtil.doGet("http://xsy.qqhru.edu.cn/login/hpindex_student.php", cookieStore);
                HttpEntity entity1 = closeableHttpResponse.getEntity();
                String s1 = EntityUtils.toString(entity1, "utf8");
                Document document = Jsoup.parse(s1);
                Elements elements = document.select("#BookClassDIV > table > tbody > tr > td:nth-child(2) > ul > li > a");
                System.out.println(user.getAccount()+elements.text());


                String testId = null;
                for (Element element : elements) {
                    testId = element.attr("href");
                }


                closeableHttpResponse.close();
                CloseableHttpResponse closeableHttpResponse1 = HttpUtil.doGet(host + "book/index.php?BookID=144&ClassID=" + testId + "&Quiz=N", cookieStore);


                closeableHttpResponse1.close();
                new Hearing(user, cookieStore).start();


            }else {
                return false;
            }
        }

        return true;
    }

    public static void logOut(CookieStore cookieStore) {
        HttpUtil.doGet("http://xsy.qqhru.edu.cn/login/logout.php", cookieStore);
    }


}
