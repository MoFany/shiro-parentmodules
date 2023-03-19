package com.mofany.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author MoFany-J
 * @date 2022/12/1
 * @description Department 部门实体
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Department {
    private long dept_id;
    private long parent_id;
    private String ancestors;
    private String dept_name;
    private int order_num;
    private String leader;
    private String phone;
    private String email;
    private int status;
    private int del_flag;
    private String create_by;
    private LocalDateTime create_time;
    private String update_by;
    private LocalDateTime update_time;

    /**
     * 子部门的集合
     * */
    List<Department> children;

}
