package com.mofany.dao;

import com.mofany.entity.Department;

import java.util.List;

/**
 * @author MoFany-J
 * @date 2022/12/1
 * @description DepartmentDao
 */
public interface DepartmentDao {
    /**
     * @description 查询所有部门信息
     * */
    List<Department> queryAll();
}
