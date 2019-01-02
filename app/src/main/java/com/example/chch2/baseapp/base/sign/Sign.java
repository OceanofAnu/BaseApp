package com.example.chch2.baseapp.base.sign;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签名类
 * Created by Administratori on 2016/9/18.
 */

public class Sign {
    /**
     * 使用方法：
     * 在请求参数的最后面添加
     * map.put("sign","")
     *
     * @param map 请求的参数
     * @return
     */
    private static String createSign(Map<String, String> headerMap, Map<String, String> map) {
        if (map == null) {
            map=new HashMap<>();
        }
        map.putAll(headerMap);
        map.put("key", " ");
        StringBuffer sb = new StringBuffer();
        Collection<String> keyset = map.keySet();
        List list = new ArrayList<String>(keyset);
        Collections.sort(list);
        //拼装第一个参数
        sb.append(list.get(0) + "=" + map.get(list.get(0)));
        for (int i = 1; i < list.size(); i++) {
            sb.append("&" + list.get(i) + "=" + map.get(list.get(i)));
        }
        System.out.println("签名参数=" + sb.toString());

        String sign = MD5Util.EncodingMD5(sb.toString()).toUpperCase();
        System.out.println("sign=" + sign);
        return sign ;
    }




    public static Map<String, String> headerMap(Map<String,String> map1) {
        Map<String, String> map = new HashMap<>();
       /* map.put("uid", ApiManager.getUid());

        String token="";
        if(UserManager.getInstance().isLogin()){
            token= UserManager.getInstance().getUser().getToken();
        }
        map.put("token",token);*/
        map.put("timestamp", System.currentTimeMillis()/1000 + "");
        map.put("sign", createSign(map, map1));
        map.put("Content-Type", "application/x-www-form-urlencoded");
        map.put("Connection", "close");
        return map;
    }





    private static String createSign1(Map<String, Object> headerMap, Map<String, Object> map) {
        if (map == null) {
            map=new HashMap<>();
        }
        map.putAll(headerMap);
        map.put("key", "ABcdeFGHIjklMN");
        StringBuffer sb = new StringBuffer();
        Collection<String> keyset = map.keySet();
        List list = new ArrayList<String>(keyset);
        Collections.sort(list);
        //拼装第一个参数
        sb.append(list.get(0) + "=" + map.get(list.get(0)));
        for (int i = 1; i < list.size(); i++) {
            sb.append("&" + list.get(i) + "=" + map.get(list.get(i)));
        }
        System.out.println("签名参数=" + sb.toString());


        String sign = MD5Util.EncodingMD5(sb.toString()).toUpperCase();
        System.out.println("sign=" + sign);

        return sign ;
    }


}
