package com.dam.wonder;

import cn.hutool.http.HttpUtil;

public class HttpTest {
    public static void main(String[] args) {
        String s = HttpUtil.get("http://62.234.29.109");
        System.out.println(s);
    }

}
