package com.mofany.entity;

import lombok.*;

/**
 * @author MoFany-J
 * @date 2022/11/27
 * @description User 用户表的实体类
 *
 * +----------+-------------+------+-----+---------+----------------+
 * | Field    | Type        | Null | Key | Default | Extra          |
 * +----------+-------------+------+-----+---------+----------------+
 * | id       | int         | NO   | PRI | NULL    | auto_increment |
 * | username | varchar(50) | YES  |     | NULL    |                |
 * | password | varchar(50) | YES  |     | NULL    |                |
 * | email    | varchar(50) | YES  |     | NULL    |                |
 * +----------+-------------+------+-----+---------+----------------+
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
}
