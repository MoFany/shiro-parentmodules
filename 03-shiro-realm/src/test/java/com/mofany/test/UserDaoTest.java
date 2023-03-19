package com.mofany.test;

import com.mofany.dao.UserDao;
import com.mofany.dao.impl.UserDaoImpl;
import org.junit.Test;

/**
 * @author MoFany-J
 * @date 2022/11/27
 * @description UserDaoTest
 */
public class UserDaoTest {
    private UserDao userDao=new UserDaoImpl();

    @Test
    public void findByUserName(){
        System.out.println(userDao.queryByUserName("jhon"));
    }

}
