package com.mofany.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author MoFany-J
 * @date 2022/11/28
 * @description QueryRunnerUtil 负责与数据建立链接
 */
public class QueryRunnerUtil {
    /**
     * 数据源，类变量
     * */
    private static DataSource dataSource;

    /**
     * 禁止创建该类的实例，禁止通过反射创建该类的实例
     * */
    private QueryRunnerUtil(){
        if(dataSource!=null){
            throw new RuntimeException("禁止反射获取工具类QueryRunnerUtil实例!");
        }
    }

    /**
     * 静态成员方法，获取关于数据库的查询执行器实例
     * */
    public static QueryRunner getQueryRunner(){
        //加载数据源
        InputStream resourceAsStream = QueryRunnerUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
        //创建属性对象
        Properties properties=new Properties();
        try{
            //将数据源装入属性对象
            properties.load(resourceAsStream);
            //通过DruidDataSourceFactory工厂创建数据源实例
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回数据库查询执行器对象
        return new QueryRunner(dataSource);
    }
}
