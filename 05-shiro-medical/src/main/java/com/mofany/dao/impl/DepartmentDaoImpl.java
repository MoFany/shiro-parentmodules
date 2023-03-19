package com.mofany.dao.impl;

import com.mofany.dao.DepartmentDao;
import com.mofany.entity.Department;
import com.mofany.util.QueryRunnerUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author MoFany-J
 * @date 2022/12/1
 * @description DepartmentDaoImpl
 */
public class DepartmentDaoImpl implements DepartmentDao {
    /**
     * 私有实例成员变量，数据库查询执行器
     * */
    private QueryRunner queryRunner= QueryRunnerUtil.getQueryRunner();

    /**
     * @description 查询所有部门信息
     */
    @Override
    public List<Department> queryAll() {
        //所有部门集
        List<Department> departmentList=null;
        try{
            departmentList=queryRunner.query("select * from sys_dept",new BeanListHandler<>(Department.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return departmentList;
    }
}
