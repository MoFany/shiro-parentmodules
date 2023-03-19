package com.mofany.servlet;

import com.alibaba.fastjson2.JSON;
import com.mofany.util.BaseResponse;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author MoFany-J
 * @date 2022/11/30
 * @description BaseServlet
 */
public class BaseServlet extends HttpServlet {

    /**
     * 处理所有基于BaseServlet或其子类对应的请求处理操作（相当于一个请求处理分发器）
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //获取请求参数action后请求的Servlet
        String action = request.getParameter("action");
        System.out.println("要执行的方法名为：" + action);
        //通过反射获取子类的对象
        Class<? extends BaseServlet> sonClass = this.getClass();

        //页面跳转
        if (this instanceof PageDispatcherServlet) {
            //直接去页面跳转
            res.setContentType("text/html;charset=utf-8");
            if (action.equals("toIndex")) {
                //响应重定向
                response.sendRedirect("index.html");
            } else {
                String pageName = action.substring(action.indexOf("to") + 2, action.length()).toLowerCase();
                //页面跳转(请求转发)
                request.getRequestDispatcher(pageName + ".html").forward(request, response);
            }
        } else {
            //反射获取要执行的方法
            try {
                System.out.println("要执行方法所属的类的全限定名称为：" + sonClass);
                //通过反射获取指定类对象的方法，action代表方法名
                Method method = sonClass.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
                System.out.println("要执行的方法的详细信息为：\n" + method);
                //反射执行方法
                Object invoke = method.invoke(this, req, res);
                //判断方法执行后的返回值类型
                if (invoke instanceof BaseResponse) {
                    //响应json数据
                    BaseResponse baseResponse = (BaseResponse) invoke;
                    //设置响应的正文类型以及字符集编码
                    res.setContentType("application/json;charset=utf-8");
                    res.setCharacterEncoding("utf-8");
                    //将响应结果对象转换为json字符串
                    String json = JSON.toJSONString(baseResponse);
                    //向前端响应结果
                    //按字符响应
                    //res.getWriter().write(json);
                    System.out.println("Json字符串响应结果为：" + json);
                    //按字节响应
                    res.getOutputStream().write(json.getBytes());
                }
                if (invoke instanceof String) {
                    //页面调整
                    //页面重定向
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
