package com.mofany.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author MoFany-J
 * @date 2022/11/28
 * @description User 实体类
 *
 * 通过lombok注解添加setter、getter、toString、无参构造器
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
}
