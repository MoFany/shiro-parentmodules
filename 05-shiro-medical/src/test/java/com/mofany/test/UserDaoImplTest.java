package com.mofany.test;
import java.time.LocalDateTime;

import com.mofany.dao.UserDao;
import com.mofany.dao.impl.UserDaoImpl;
import com.mofany.entity.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * @author MoFany-J
 * @date 2022/11/30
 * @description UserDaoImplTest 用户数据访问操作层测试类
 */
public class UserDaoImplTest {
    private UserDao userDao=new UserDaoImpl();

//    @Test
    public void findByUserName(){
        //输出查询测试的结果
        System.out.println(userDao.queryByUserName("ry"));
    }

//    @Test
    public void addUser(){
        User user = new User();
        user.setDept_id(0L);
        user.setUser_name("宋江");
        user.setNick_name("及时雨");
        user.setUser_type("00");
        user.setEmail("songjiang@163.com");
        user.setPhonenumber("1234567890");
        user.setSex((short)1);
        //密码加密
        ByteSource byteSource = ByteSource.Util.bytes("宋江");
        SimpleHash simpleHash=new SimpleHash("SHA1","123456",byteSource,1024);
        user.setPassword(simpleHash.toString());
        user.setStatus("1");
        user.setCreate_by("admin");
        user.setCreate_time(LocalDateTime.now());
        user.setUpdate_by("admin");
        user.setUpdate_time(LocalDateTime.now());
        user.setRemark("大哥");
        //添加用户
        System.out.println(userDao.addUser(user));
    }
}
