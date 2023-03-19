package com.mofany.dao.impl;

import com.mofany.dao.UserDao;
import com.mofany.entity.Page;
import com.mofany.entity.User;
import com.mofany.util.QueryRunnerUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.sql.SQLException;
import java.util.List;

/**
 * @author MoFany-J
 * @date 2022/11/30
 * @description UserDaoImpl
 */
public class UserDaoImpl implements UserDao {
    /**
     * 实例成员变量是一个查询执行器
     * */
    private QueryRunner queryRunner= QueryRunnerUtil.getQueryRunner();

    /**
     * @param userName 要查询的用户名
     * @description 按用户名查询
     */
    @Override
    public User queryByUserName(String userName) {
        User user=null;
        try{
            user=queryRunner.query("select * from sys_user where user_name = ?",new BeanHandler<>(User.class),userName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    /**
     * @param userName 要更新的用户名
     * @description 更新用户密码
     */
    @Override
    public boolean executorEncryption(String userName,String password) {
        try{
            queryRunner.update("update sys_user set password = ? where user_name = ?",password,userName);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * @description 查询总记录
     */
    @Override
    public List<User> queryAll() {
        List<User> userList=null;
        try{
            userList=queryRunner.query("select * from sys_user",new BeanListHandler<>(User.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    /**
     * @param page 要查询指定页
     * @description 查询指定页
     */
    @Override
    public List<User> queryByPage(Page page) {
        List<User> userList=null;
        try{
            userList=queryRunner.query("select * from sys_user limit ?,?",
                    new BeanListHandler<>(User.class),
                    page.getStartIndex(),
                    page.getPageSize());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    /**
     * @param id 被删除用户的id
     * @description 按指定id删除用户
     */
    @Override
    public int deleteById(long id) {
        int result=0;
        try{
            result=queryRunner.update("delete from sys_user where user_id = ?",id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * @param ids 多个用户的id
     * @description 批量删除多个用户
     */
    @Override
    public int deleteByIds(String ids) {
        int result=0;
        //级联调用方法：将字符串的括号替换为圆括号
        ids=ids.replace('[','(').replace(']',')');
        //sql拼接
        String sql="delete from sys_user where user_id in"+ids;
        System.out.println("输出要执行的SQl语句："+sql);
        try{
            result=queryRunner.update(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * @param user 要插入数据库的新用户
     * @description 新增用户
     */
    @Override
    public int addUser(User user) {
        //新增用户时进行密码加密
        ByteSource salt = ByteSource.Util.bytes(user.getUser_name());
        //初始密码为123456
        SimpleHash simpleHash=new SimpleHash("SHA1","123456",salt,1024);
        int result=0;
        try{
            result=queryRunner.update(
                    "insert into sys_user (dept_id,user_name,nick_name,email,phonenumber,sex,password,status,create_by,create_time,update_by,update_time,remark) values (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    user.getDept_id(),
                    user.getUser_name(),
                    user.getNick_name(),
                    user.getEmail(),
                    user.getPhonenumber(),
                    user.getSex(),
                    simpleHash.toString(),
                    user.getStatus(),
                    user.getCreate_by(),
                    user.getCreate_time(),
                    user.getUpdate_by(),
                    user.getUpdate_time(),
                    user.getRemark()
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
