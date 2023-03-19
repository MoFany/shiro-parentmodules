package com.mofany.dao.impl;

import com.mofany.dao.UserDao;
import com.mofany.entity.User;
import com.mofany.util.QueryRunnerUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @author MoFany-J
 * @date 2022/11/27
 * @description UserDaoImpl
 */
public class UserDaoImpl implements UserDao {
    /**
     * 获取数据库查询执行器
     * */
    private QueryRunner queryRunner= QueryRunnerUtil.getQueryRunner();

    /**
     * 按用户名查询
     *
     * @param userName 用户名
     */
    @Override
    public User queryByUserName(String userName) {
        User user=null;
        try {
            //返回查询结果
            user=queryRunner.query("select * from t_user where username = ?",
                    new BeanHandler<>(User.class),userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
