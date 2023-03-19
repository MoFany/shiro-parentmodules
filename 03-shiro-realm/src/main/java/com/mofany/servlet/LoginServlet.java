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
 * @date 2022/11/27
 * @description LoginServlet
 */
@WebServlet(name ="LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求与响应的编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取请求参数
        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        //创建关于用户名与密码的token
        UsernamePasswordToken userToken=new UsernamePasswordToken(userName,password);
        try{
            //获取subject对象
            Subject subject=SecurityUtils.getSubject();
            /*
            执行登录，该token会传递给自定义认证的realm的doGetAuthenticationInfo(AuthenticationToken authenticationToken)方法的形参
            * */
            subject.login(userToken);
            //响应重定向到指定页面
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
