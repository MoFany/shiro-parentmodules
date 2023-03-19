package com.mofany.servlet;

import cn.hutool.core.bean.BeanUtil;
import com.mofany.util.BaseResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author MoFany-J
 * @date 2022/11/30
 * @description LoginServlet
 */
@WebServlet(name = "handlerLogin", urlPatterns = "/handlerLogin")
public class LoginServlet extends BaseServlet {
    /**
     * 处理登录操作
     */
    public BaseResponse doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        System.out.println("前端输入的用户信息为：\n" + "用户名："+userName+"\n密码：" + password+"\n验证码：" + captcha);
        //进行验证码判断，先获取Session中设置的关于captcha的属性值
        String captcha1 = (String) request.getSession().getAttribute("captcha");
        //返回自定义的响应
        BaseResponse baseResponse = null;
        //验证判断(不区分大小写)逻辑
        if (!captcha1.equalsIgnoreCase(captcha)) {
            //验证失败
            baseResponse = BaseResponse.failure(3001, "验证码错误");
        } else {
            //验证成功
            UsernamePasswordToken userToken = new UsernamePasswordToken(userName, password);
            //获取Subject对象进行登录
            Subject subject = SecurityUtils.getSubject();
            //登录,并捕获登录失败的原因
            try {
                //执行登录操作
                subject.login(userToken);
                baseResponse=BaseResponse.ok(2001,"登录成功",userName);
            } catch (UnknownAccountException e) {
                baseResponse = BaseResponse.failure(3002, "用户未注册");
            } catch (IncorrectCredentialsException e) {
                baseResponse = BaseResponse.failure(3003, "密码错误");
            } catch (LockedAccountException e) {
                baseResponse = BaseResponse.failure(3004, "账户禁用");
            } catch (AuthenticationException e) {
                baseResponse = BaseResponse.failure(3005, "登录失败：未知异常！");
            }
        }
        return baseResponse;
    }


    /**
     * 处理登出操作
     * */
    public BaseResponse doLogout(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        //通过shiro处理登出操作，先获取Subject对象
        Subject subject=SecurityUtils.getSubject();
        //然后执行登出操作
        subject.logout();
        //返回json的响应结果
        return BaseResponse.ok(2010,"登出成功");
    }

}
