package com.mofany.servlet;

import com.mofany.dao.UserDao;
import com.mofany.dao.impl.UserDaoImpl;
import com.mofany.entity.Page;
import com.mofany.entity.User;
import com.mofany.util.BaseResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author MoFany-J
 * @date 2022/12/1
 * @description UserServlet
 */
@WebServlet(name = "userServlet", urlPatterns = "/userServlet")
public class UserServlet extends BaseServlet {
    /**
     * 实例成员变量
     */
    private UserDao userDao = new UserDaoImpl();

    /**
     * 处理分页查询
     */
    public BaseResponse queryByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //获取数据
        List<User> all = userDao.queryAll();
        //分页查询数据
        Page page = new Page(Integer.parseInt(currentPage), all.size(), Integer.parseInt(pageSize));
        List<User> byPage = userDao.queryByPage(page);

        System.out.println(byPage);
        //向页面响应
        BaseResponse baseResponse = null;
        if (byPage.size() > 0) {
            //查询成功
            //baseResponse=BaseResponse.ok(2005,"获取用户信息成功",byPage);
            Map<String, Object> map = new HashMap<>();
            map.put("userList", byPage);
            map.put("page", page);
            baseResponse = BaseResponse.ok(2005, "获取用户信息成功", map);
        } else {
            //查询失败
            baseResponse = BaseResponse.failure();
        }
        return baseResponse;
    }

    /**
     * 处理新增用户
     */
    public BaseResponse addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String userName = request.getParameter("user_name");
        String nickName = request.getParameter("nick_name");
        String sex = request.getParameter("sex");
        String deptId = request.getParameter("dept_id");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phonenumber");
        String remark = request.getParameter("remark");
        System.out.println(userName + "---" + nickName + "---" + deptId + "----" + sex);
        //将请求参数设置给用户对象属性
        User user = new User();
        user.setDept_id(Long.parseLong(deptId));
        user.setUser_name(userName);
        user.setNick_name(nickName);
        user.setEmail(email);
        user.setPhonenumber(phoneNumber);
        user.setSex(Short.parseShort(sex));
        //设置账户的状态(即默认状态为：1工作)
        user.setStatus("1");
        user.setCreate_by("admin");
        user.setCreate_time(LocalDateTime.now());
        user.setUpdate_by("admin");
        user.setUpdate_time(LocalDateTime.now());
        user.setRemark(remark);
        //进行反馈响应操作
        int result = userDao.addUser(user);
        BaseResponse baseResponse = null;
        if (result > 0) {
            //成功响应反馈
            baseResponse = BaseResponse.ok(2008, "添加用户成功");
        } else {
            //失败响应反馈
            baseResponse = BaseResponse.failure();
        }
        return baseResponse;
    }

    /**
     * 处理按删除单个用户
     */
    public BaseResponse deleteUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String id = request.getParameter("id");
        //类型转换并执行删除指定id的用户
        int i = userDao.deleteById(Long.parseLong(id));
        //自定义响应结果
        BaseResponse baseResponse = null;
        if (i > 0) {
            baseResponse = BaseResponse.ok(2006, "删除用户成功");
        } else {
            baseResponse = BaseResponse.failure();
        }
        return baseResponse;
    }

    /**
     * 处理批量删除用户
     */
    public BaseResponse deleteUserByIds(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取请求参数，该参数是一个列表
        String[] ids = request.getParameterValues("ids");
        //将请求字符串数组转换为字符串，然后替换两边括号即可
        String idsStr = Arrays.toString(ids);
        //输出该对象
        System.out.println("请求参数的对象为：" + ids);
        System.out.println("请求参数的值为：" + idsStr);
        //响应反馈
        BaseResponse baseResponse = null;
        //数据库中执行批量删除操作
        int result = userDao.deleteByIds(idsStr);
        if (result > 0) {
            //成功
            baseResponse = BaseResponse.ok(2009, "批量删除成功");
        } else {
            //失败
            baseResponse = BaseResponse.failure();
        }
        return baseResponse;
    }

    /**
     * 处理按用户名查找用户操作
     */
    public BaseResponse queryByUsername(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName=request.getParameter("username");
        User user=userDao.queryByUserName(userName);
        BaseResponse baseResponse=null;
        if (user!=null){
            //成功
            baseResponse=BaseResponse.ok(2011,"获取用户信息成功",user);
        }else{
            //失败
            baseResponse=BaseResponse.failure();
        }

        return baseResponse;
    }

    /**
     * 处理头像文件上传
     */
    public BaseResponse doAvatarUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取复合表单中指定的请求参数
        Part part=request.getPart("file");
        System.out.println("11111111111"+request.getPart("file"));
        System.out.println("22222222222"+request.getParts());


//        String header=part.getHeader("content-disposition");
//        //获取真实的上下文路径
//        String upload=request.getServletContext().getRealPath("upload");
//        //路径字符串拼接
//        String newPath=upload.concat("/").concat(LocalDate.now().toString());
//        File file=new File(newPath);
//        //如果该目录不存在则创建
//        if (!file.exists()){
//            //创建包含子孙关系的目录
//            file.mkdirs();
//        }
//        //获取提交的文件的名字
//        String fileName=part.getSubmittedFileName();
//        String extName=fileName.substring(fileName.lastIndexOf("."),fileName.length());
//        //通过唯一序列去拼接并生成新的文件名
//        String newFileName= UUID.randomUUID().toString().
//                replace("-","")+System.currentTimeMillis()+extName;
//        //上传文件
//        part.write(file.toString()+"/"+newFileName);
        return BaseResponse.ok(2012,"头像上传成功");
    }
}
