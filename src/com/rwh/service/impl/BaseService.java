package com.rwh.service.impl;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class BaseService {

    /**
     *  将base64编码字符串转换为图片
     * @param file base64
     * @param path 文件路径
     * @param request
     * @return
     */
    public static String generateImage(String file, String path, HttpServletRequest request) {

        // 解密
        try {


            // 项目绝对路径,找到img文件夹
            String savePath = request.getServletContext().getRealPath("/img/");
            // 图片分类路径+图片名+图片后缀
//            UUID.randomUUID().toString()
//            javaJDK提供的一个自动生成主键的方法。UUID(Universally Unique Identifier)全局唯一标识符
//            是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的，是由一个十六位的数字组成,表现出来的形式
//            文件名和后缀
            String imgClassPath = path.concat(UUID.randomUUID().toString()).concat(".png");
//            String imgClassPath = path.concat(".png");
            // 解密
            System.out.println(savePath);
            System.out.println(imgClassPath);
            Base64.Decoder decoder = Base64.getDecoder();
            // 去掉base64前缀 data:image/jpeg;base64,
            file = file.substring(file.indexOf(",", 1) + 1, file.length());
            byte[] b = decoder.decode(file);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            // 保存图片
            OutputStream out = new FileOutputStream(savePath.concat(imgClassPath));
            out.write(b);
            out.flush();
            out.close();
            // 返回图片的路径
            return savePath+imgClassPath;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 随机生成20个字符的字符串
     * @return
     */
    public String randomstr(int n){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random1=new Random();
        //指定字符串长度，拼接字符并toString
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < n; i++) {
            //获取指定长度的字符串中任意一个字符的索引值
            int number=random1.nextInt(str.length());
            //根据索引值获取对应的字符
            char charAt = str.charAt(number);
            sb.append(charAt);
        }
        String str1 = String.valueOf(sb);
        return str1;
    }
    public String randomNum(int n){
        String str="0123456789";
        Random random1=new Random();
        //指定字符串长度，拼接字符并toString
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < n; i++) {
            //获取指定长度的字符串中任意一个字符的索引值
            int number=random1.nextInt(str.length());
            //根据索引值获取对应的字符
            char charAt = str.charAt(number);
            sb.append(charAt);
        }
        String str1 = String.valueOf(sb);
        return str1;
    }
}
