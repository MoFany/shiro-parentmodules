package com.mofany.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MoFany-J
 * @date 2022/11/28
 * @description LoginServlet
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        //封装为token
        UsernamePasswordToken userToken=new UsernamePasswordToken(userName,password);
        //获取Subject对象
        Subject subject= SecurityUtils.getSubject();

        //执行登录
        try{
            subject.login(userToken);
            response.sendRedirect("success.jsp");
        }catch (UnknownAccountException e){
            System.out.println("未注册用户!");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误!");
        }catch (LockedAccountException e){
            System.out.println("账户锁定!");
        }catch (AuthenticationException e){
            System.out.println("登录失败：未知错误!");
        }
    }
}
