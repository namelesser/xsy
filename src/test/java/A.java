import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A {
    public static void main(String[] args) throws FileNotFoundException {
        List<BasicNameValuePair> basicNameValuePairs = new ArrayList<BasicNameValuePair>();
        HttpPost httpPost = null;
        File file = new File("D:\\a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(fileInputStream);
        boolean flag = true;
        while (scanner.hasNext()) {
            String str = scanner.next();
            if(flag)
            {

                basicNameValuePairs=new ArrayList<BasicNameValuePair>();
                System.out.println(str);
            }
            else {
                String[] splits = str.split("&");
                for(String split:splits)
                {
                    String[] split1 = split.split("=");
                    String name=split1[0];
                    String value="";
                    if(split1.length==2)
                    {
                        value=split1[1];
                    }
                    basicNameValuePairs.add(new BasicNameValuePair(name,value));                }
                System.out.println(basicNameValuePairs);
            }

            flag = !flag;
        }


    }


}

