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
 * @date 2022/11/27
 * @description MyRealm
 */
public class MyRealm extends AuthorizingRealm {

    /**
     * 创建用户操作的实现
     * */
    private UserDao userDao = new UserDaoImpl();

    /**
     * 自定义认证
     * @param authenticationToken 从servlet中请求参数获取的用户名密码token字符串，有subject.login(token)传递而来
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取认证的token，强转为子类类型的token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从token中获取用户名
        String userName = token.getUsername();
        //查询用于登录的用户是否存在于数据库中
        User user = userDao.queryByUserName(userName);
        //判断用户是否存在
        if (user == null){
            throw new UnknownAccountException("未知的用户");
        }

        /**构造器参数介绍
         *
         * 1. principal 可以是从数据库中查询出的用户 也可以是指定的用户名
         * 2. credentials 密码
         * 3. credentialsSalt 盐，加密的盐，即加密的规则或者佐料
         * 4. realmName securityManager 去找谁认证（不指定时默认匹配当前自定义的认证规则）
         * */
        System.out.println("进行了数据库查询："+user);

        //存在则返回一个简单认证信息SimpleAuthenticationInfo对象
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
    }

    /**
     * 授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
