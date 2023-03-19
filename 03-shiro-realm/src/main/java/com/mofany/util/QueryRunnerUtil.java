package com.mofany.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author MoFany-J
 * @date 2022/11/27
 * @description QueryRunnerUtil 数据库操作工具类
 */

public class QueryRunnerUtil {
    /**
     * 数据源
     * */
    private static DataSource dataSource;

    /**
     * 禁止创建此类的对象
     * */
    private QueryRunnerUtil(){
        if (dataSource!=null){
            new RuntimeException("禁止通过反射创建此类的实例!");
        }
    }

    /**
     * 获取查询执行器的方法
     * */
    public static QueryRunner getQueryRunner(){
        //加载数据源文件
        InputStream resourceAsStream = QueryRunnerUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
        //创建属性对象
        Properties properties=new Properties();
        try{
            //将资源加载到属性对象中
            properties.load(resourceAsStream);
            //创建数据源对象
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        }  catch (Exception e) {
            e.printStackTrace();
        }

        //返回查询执行器对象
        return new QueryRunner(dataSource);
    }
}
