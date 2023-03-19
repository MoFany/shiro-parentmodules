package com.mofany.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * @author MoFany-J
 * @date 2022/12/1
 * @description PageDispatcherServlet 专用于页面跳转的Servlet
 */
@WebServlet(name = "pageDispatcher",urlPatterns = "/pageDispatcher")
public class PageDispatcherServlet extends BaseServlet{
    /**
     * 跳转到指定页面，不处理业务逻辑
     * */
}
