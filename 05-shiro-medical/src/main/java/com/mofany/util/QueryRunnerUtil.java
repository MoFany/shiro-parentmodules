package com.mofany.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author MoFany-J
 * @date 2022/11/30
 * @description QueryRunnerUtil
 */
public class QueryRunnerUtil {
    /**
     * 数据源类变量
     * */
    private static DataSource dataSource;

    /**
     * 私有构造器：禁止创建该类实例
     * */
    private QueryRunnerUtil(){
        if(dataSource!=null){
            throw new RuntimeException("静止反射创建QueryRunnerUtil类实例！");
        }
    }

    /**
     * 获取查询执行器的方法
     * */
    public static QueryRunner getQueryRunner(){
        //加载数据源
        InputStream resourceAsStream =
                QueryRunnerUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
        //创建属性对象
        Properties properties=new Properties();

        try{
            //向属性对象装载数据源
            properties.load(resourceAsStream);
            //通过DruidDataSourceFactory工厂创建数据源实例
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //返回查询执行器对象
        return new QueryRunner(dataSource);
    }
}
