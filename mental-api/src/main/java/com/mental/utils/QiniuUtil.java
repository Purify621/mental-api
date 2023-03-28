package com.mental.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class QiniuUtil {

    @Value("${qiniu.domain}")
    private String domain;

    public String getUrl(String fileName) {
        try{
            String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            String url = String.format("%s/%s", domain, encodedFileName);
            System.out.println(url);
            return url;
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }
    }
}
