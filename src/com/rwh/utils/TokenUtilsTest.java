package com.rwh.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class TokenUtilsTest {

    @Test
    public void token() {
    }



    @Test
    public void verify2() {
    }

    @Test
    public void verify() {
        String token = "eyJUeXBlIjoiSnd0IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VyTmFtZSI6IuWwj-mYriIsImV4cCI6MTY1MzMxNjUzNH0.KOxjJDi5PxBZdY5kuHodPXI1ZiA015pNGTMlh7DdPX8";
        if(TokenUtils.verify(token))
            System.out.println("111");
        else System.out.println("222");
        if(!TokenUtils.verify(token))
        System.out.println(333);
        else  System.out.println("444");
    }
}