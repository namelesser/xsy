package cn.namelesser.urils;

import cn.namelesser.xsy.Account;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 42169
 */
public class ReadAccount {

    public static  ArrayList<Account> getAccounts()
    {
        InputStream inputStream = ReadAccount.class.getClassLoader().getResourceAsStream("account.txt");
        Scanner scanner = new Scanner(inputStream);
        ArrayList<Account> accounts = new ArrayList<Account>();
        while(scanner.hasNext())
        {
            accounts.add(new Account(scanner.next(),scanner.next(),scanner.nextInt(),scanner.nextInt()));
        }
        return accounts;

    }
}
