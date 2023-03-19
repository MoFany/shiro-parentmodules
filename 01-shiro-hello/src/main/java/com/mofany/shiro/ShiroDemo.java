package com.mofany.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author MoFany-J
 * @date 2022/11/25
 * @description ShiroDemo shiro初体验
 */
public class ShiroDemo {
    public static void main(String[] args) {

        //1.根据初始化的配置文件获取SecurityManager的工厂
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");

        //2.根据工厂获取SecurityManager
        SecurityManager securityManager = factory.getInstance();

        //3.注册SecurityManager
        SecurityUtils.setSecurityManager(securityManager);

        //4.获取Subje
        Subject subject = SecurityUtils.getSubject();

        //5.准备要一个要验证的用户名和密码的token（token为密码的加密字符串表示）
        //正确用户信息的验证测试
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        //错误用户信息的验证测试
        //UsernamePasswordToken token = new UsernamePasswordToken("root", "123456");

        //6.执行登录操作并捕获登录异常的情况
        try {
            subject.login(token);
            System.out.println("登录成功！");
        } catch (UnknownAccountException uae) {
            //username wasn't in the system, show them an error message?
            System.out.println("用户找不到！");
        } catch (IncorrectCredentialsException ice) {
            //password didn't match, try again?
            System.out.println("密码错误！");
        } catch (LockedAccountException lae) {
            //account for that username is locked - can't login.  Show them a message?
            System.out.println("账户被锁定！");
        } catch (AuthenticationException ae) {
            //unexpected condition - error?
            System.out.println("登录失败：未知错误！");
        }
    }
}
