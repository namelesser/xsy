package cn.namelesser.urils;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class HttpUtil {

    private static final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();






static {
    cm.setMaxTotal(10000);
    cm.setDefaultMaxPerRoute(10000);
}




    public static CloseableHttpResponse doGet(String url,CookieStore cookieStore) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultCookieStore(cookieStore).build();


        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36");
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response=null;

        try {

             response = httpClient.execute(httpGet);

        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }


        return response;
    }

    public  static CloseableHttpResponse doPost(String url, List<NameValuePair> param, String charset,CookieStore cookieStore) {

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000).build();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultCookieStore(cookieStore).build();


        UrlEncodedFormEntity urlEncodedFormEntity = null;
        try {

            urlEncodedFormEntity = new UrlEncodedFormEntity(param, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(urlEncodedFormEntity);
        httpPost.setConfig(requestConfig);


        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();

        }

        return  response;

    }

}
