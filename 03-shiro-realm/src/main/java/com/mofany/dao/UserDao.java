package com.mofany.dao;

import com.mofany.entity.User;

/**
 * @author MoFany-J
 * @date 2022/11/27
 * @description UserDao
 */
public interface UserDao {
    /**
     * 按用户名查询
     * */
    User queryByUserName(String userName);
}
