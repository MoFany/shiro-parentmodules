package com.mofany.realm;

import com.mofany.dao.UserDao;
import com.mofany.dao.impl.UserDaoImpl;
import com.mofany.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author MoFany-J
 * @date 2022/11/30
 * @description MyRealm
 */
public class MyRealm extends AuthorizingRealm {
    /**
     * 私有实例成员
     * */
    private UserDao userDao=new UserDaoImpl();

    /**
     * 自定义授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 自定义认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //参数类型转换为用户的token
        UsernamePasswordToken userToken=(UsernamePasswordToken) token;
        //获取用户名
        String username=userToken.getUsername();
        //返回查询指定用户名的用户是否存在
        User user=userDao.queryByUserName(username);
        //判断查询到的用户
        if(user==null){
            throw new UnknownAccountException("用户未注册");
        }
        System.out.println("数据库中查询到的用户信息为：\n"+user);
        //获取数据库中查询到的用户密码
        String password=user.getPassword();
        System.out.println("数据库中查到的用户信息：\n"+"用户名："+user.getUser_name()+"\n用户密码："+user.getPassword());

        /**
         * 定义盐，即加密的佐料（该用户密码和账号进行再加密）
         * */
        System.out.println("*********************************************************************");
        ByteSource salt=ByteSource.Util.bytes(username);
        System.out.println("加密的盐为："+salt);
        //将加密密码写入数据库（加密的算法，初始时要加密的密码，参与本次加密的盐，要进行加密的次数）
        SimpleHash simpleHash=new SimpleHash("SHA1","123456",salt,1024);
        System.out.println("加密后的密文密码（写入数据库）为："+simpleHash);
        System.out.println("*********************************************************************");
//        if(userDao.executorEncryption(username,simpleHash.toString())){
//            System.out.println("加密密码已写入数据库！");
//        }

        //返回一个简单的认证信息
        return new SimpleAuthenticationInfo(username,password,salt,this.getName());
    }
}
