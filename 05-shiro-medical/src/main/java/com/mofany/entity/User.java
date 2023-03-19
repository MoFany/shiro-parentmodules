package com.mofany.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author MoFany-J
 * @date 2022/11/30
 * @description User 该实体类可序列化
 *
 * +-------------+--------------+------+-----+---------+----------------+
 * | Field       | Type         | Null | Key | Default | Extra          |
 * +-------------+--------------+------+-----+---------+----------------+
 * | user_id     | bigint       | NO   | PRI | NULL    | auto_increment |
 * | dept_id     | bigint       | YES  |     | NULL    |                |
 * | user_name   | varchar(30)  | NO   |     | NULL    |                |
 * | nick_name   | varchar(30)  | NO   |     | NULL    |                |
 * | user_type   | varchar(2)   | YES  |     | 00      |                |
 * | email       | varchar(50)  | YES  |     |         |                |
 * | phonenumber | varchar(11)  | YES  |     |         |                |
 * | sex         | char(1)      | YES  |     | 0       |                |
 * | avatar      | varchar(100) | YES  |     |         |                |
 * | password    | varchar(100) | YES  |     |         |                |
 * | status      | char(1)      | YES  |     | 0       |                |
 * | del_flag    | char(1)      | YES  |     | 0       |                |
 * | login_ip    | varchar(128) | YES  |     |         |                |
 * | login_date  | datetime     | YES  |     | NULL    |                |
 * | create_by   | varchar(64)  | YES  |     |         |                |
 * | create_time | datetime     | YES  |     | NULL    |                |
 * | update_by   | varchar(64)  | YES  |     |         |                |
 * | update_time | datetime     | YES  |     | NULL    |                |
 * | remark      | varchar(500) | YES  |     | NULL    |                |
 * +-------------+--------------+------+-----+---------+----------------+
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class User implements Serializable {
    private long user_id;
    private long dept_id;
    private String user_name;
    private String nick_name;
    private String user_type;
    private String email;
    private String phonenumber;
    private short sex;
    private String avatar;
    private String password;
    private String status;
    private short del_flag;
    private String login_ip;
    private LocalDateTime login_date;
    private String create_by;
    private LocalDateTime create_time;
    private String update_by;
    private LocalDateTime update_time;
    private String remark;
}
