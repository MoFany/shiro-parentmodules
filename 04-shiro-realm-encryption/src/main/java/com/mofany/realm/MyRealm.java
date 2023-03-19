package com.mofany.realm;

import com.mofany.dao.UserDao;
import com.mofany.dao.impl.UserDaoImpl;
import com.mofany.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author MoFany-J
 * @date 2022/11/28
 * @description MyRealm
 */
public class MyRealm extends AuthorizingRealm {
    /**
     * 创建一个用户对象的实例成员
     * */
    private UserDao userDao=new UserDaoImpl();

    /**
     * 自定义授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 自定义认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //创建用户token对象
        UsernamePasswordToken userToken=(UsernamePasswordToken) authenticationToken;
        //获取由subject.login(userToken)传过来的用户名，即请求参数对应的用户名
        String userName=userToken.getUsername();
        //查询该用户
        User user=userDao.queryByUserName(userName);
        //判断该用户是否为空
        if (user==null){
            throw new UnknownAccountException("未注册的用户!");
        }
        //获取密码
        String password=user.getPassword();
        //输出数据库中查询到的用户信息
        System.out.println("数据库中查询到的信息为："+user);
        //返回一个简单的认证信息
        return new SimpleAuthenticationInfo(user,password,this.getName());
    }
}
