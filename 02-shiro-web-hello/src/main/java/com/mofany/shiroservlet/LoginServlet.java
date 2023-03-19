package com.mofany.shiroservlet;

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
 * @date 2022/11/26
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
        //设置请求与响应的编码格式
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取请求参数，即用户名与密码
        String userName=request.getParameter("username");
        String password=request.getParameter("password");

        try{
            //生成token
            UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
            //获取subject用户
            Subject subject=SecurityUtils.getSubject();
            //利用token（用户信息加密后的字符串）执行登录
            subject.login(token);
            //登录成功重定向到首页
            response.sendRedirect("success.jsp");
        }catch (UnknownAccountException uae) {
            //username wasn't in the system, show them an error message?
            System.out.println("用户找不到！");
        } catch (IncorrectCredentialsException ice) {
            //password didn't match, try again?
            System.out.println("密码错误！");
        } catch (LockedAccountException lae) {
            //account for that username is locked - can't login.  Show them a message?
            System.out.println("账户被锁定！");
        } catch (AuthenticationException ae) {
            //unexpected condition - error?
            System.out.println("登录失败：未知错误！");
        }
    }
}
