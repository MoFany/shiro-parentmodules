package com.mofany.dao;

import com.mofany.entity.User;

/**
 * @author MoFany-J
 * @date 2022/11/28
 * @description UserDao 数据访问层接口
 */
public interface UserDao {
    /**
     * 按用户名查找
     * @param userName  传入要查询的用户名
     * */
    User queryByUserName(String userName);

}
