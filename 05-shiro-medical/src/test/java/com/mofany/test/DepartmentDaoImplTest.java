package com.mofany.test;


import cn.hutool.core.bean.BeanUtil;
import com.mofany.dao.DepartmentDao;
import com.mofany.dao.impl.DepartmentDaoImpl;
import com.mofany.entity.Department;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author MoFany-J
 * @date 2022/12/1
 * @description DepartmentDaoImplTest 部门数据访问层测试类
 */
public class DepartmentDaoImplTest {
    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Test
    public void test() {
        //数据库中查询所有部门集合
        List<Department> departmentList = departmentDao.queryAll();
        //初始时默认先用0作为一级菜单的父级
        List<Department> departments = makeTree(departmentList, 0);
        System.out.println(departments);
    }

    /**
     * 递归调用并分级显示
     * */
    public List<Department> makeTree(List<Department> departmentList, long id) {
        //用于存储部门的集合
        List<Department> list=new ArrayList<>();
        //递归获取所有二级菜单
        departmentList.stream()
                //过滤非空
                .filter(Objects::nonNull)
                //获取父id并判断其是否与参数代表的id相等
                .filter(dept -> dept.getParent_id() == id)
                //遍历
                .forEach(dept -> {
                    //新建一个部门实例
                    Department department = new Department();
                    //进行对象之间的属性复制
                    BeanUtil.copyProperties(dept,department);
                    //递归调用(用于存储当前部门下剩余的其它子部门的集合)
                    List<Department> children = makeTree(departmentList, dept.getDept_id());
                    //有子集则设置
                    if (children.size() > 0) {
                        //设置父结点
                        department.setChildren(children);
                    }
                    //将指定的部门加入当前List集合中
                    list.add(department);
                });
        return list;
    }
}
