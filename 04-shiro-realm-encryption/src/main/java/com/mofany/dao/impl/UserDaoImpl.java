package com.mofany.dao.impl;

import com.mofany.dao.UserDao;
import com.mofany.entity.User;
import com.mofany.util.QueryRunnerUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @author MoFany-J
 * @date 2022/11/28
 * @description UserDaoImpl
 */
public class UserDaoImpl implements UserDao {
    /**
     * 查询执行器实例成员变量
     * */
    private QueryRunner queryRunner= QueryRunnerUtil.getQueryRunner();

    /**
     * 按用户名查找
     *
     * @param userName 传入要查询的用户名
     */
    @Override
    public User queryByUserName(String userName) {
        User user=null;
        try {
            //执行sql语句并将结果集映射到一个JavaBean对象中
            user=queryRunner.query("select * from t_user where username = ?",new BeanHandler<>(User.class),userName);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //返回查询结果
        return user;
    }
}
