package com.example.ckglixt.configer;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5 {
    public static void main(String[] args) {
        Md5Hash md5Hash2 = new Md5Hash("123","xo*7ps",1024);
        System.out.println(md5Hash2);

    }

}
