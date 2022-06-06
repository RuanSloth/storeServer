package com.rwh.servlet;

import com.rwh.utils.TokenUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")
public class test extends BaseServlet{
    protected void testToken(HttpServletRequest req, HttpServletResponse resp){
        String accessToken = req.getHeader("AccessToken");
        System.out.println(TokenUtils.verify2(accessToken));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("111");
    }
}
