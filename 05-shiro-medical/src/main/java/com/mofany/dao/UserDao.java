package com.mofany.dao;

import com.mofany.entity.Page;
import com.mofany.entity.User;

import java.util.List;

/**
 * @author MoFany-J
 * @date 2022/11/30
 * @description UserDao
 */
public interface UserDao {
    /**
     * @param userName 要查询的用户名
     * @description 按用户名查询
     * */
    User queryByUserName(String userName);

    /**
     * @param userName 要更新的用户名
     * @description 更新用户密码
     * */
    boolean executorEncryption(String userName,String password);

    /**
     * @description 查询总记录
     * */
    List<User> queryAll();

    /**
     * @param page 要查询指定页
     * @description 查询指定页
     * */
    List<User> queryByPage(Page page);


    /**
     * @param id 被删除用户的id
     * @description 按指定id删除用户
     * */
    int deleteById(long id);

    /**
     * @param ids 多个用户的id
     * @description 批量删除多个用户
     * */
    int deleteByIds(String ids);

    /**
     * @param user 要插入数据库的新用户
     * @description 新增用户
     * */
    int addUser(User user);
}
