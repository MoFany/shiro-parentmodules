package com.mofany.servlet;

import com.mofany.dao.DepartmentDao;
import com.mofany.dao.impl.DepartmentDaoImpl;
import com.mofany.entity.Department;
import com.mofany.util.BaseResponse;
import com.mofany.util.DepartmentUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author MoFany-J
 * @date 2022/12/2
 * @description DepartmentServlet 部门分类
 */
@WebServlet(name = "departmentServlet", urlPatterns = "/departmentServlet")
public class DepartmentServlet extends BaseServlet {
    /**
     * 对象属性
     */
    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    /**
     * 处理部门信息的分级显示
     */
    public BaseResponse queryDeptTree(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取所有部门信息
        List<Department> departmentList = departmentDao.queryAll();
        //递归生成部门分级显示(从默认的0级菜单开始)
        List<Department> departments = DepartmentUtil.makeTree(departmentList, 0);
        //自定义响应结果
        BaseResponse baseResponse = null;
        //判断
        if (departments.size() > 0) {
            //成功响应
            baseResponse=BaseResponse.ok(2007,"获取部门信息成功",departments);
        }else {
            //失败响应
            baseResponse=BaseResponse.failure();
        }
        return baseResponse;
    }
}
