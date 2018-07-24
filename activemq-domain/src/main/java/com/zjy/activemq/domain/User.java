package com.zjy.activemq.domain;

import java.io.Serializable;

/**
 * @author zhangjiuyang
 * @create 2018/7/24
 * @since 1.0.0
 */
//用户测试类
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String username;

    private String password;

    private String sex;

    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + ", sex=" + sex + ", age=" + age + "]";
    }
}
