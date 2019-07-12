package com.springboot.core.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.core.base.BaseDTO;
import com.springboot.core.util.Dates;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统用户
 *
 * @version 1.0
 * @author bojiangzhou 2017-12-31
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "DLY_USER")
public class User extends BaseDTO {

    private static final long serialVersionUID = -1990702583747830617L;

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy("DESC")
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 生日
     */
    @JsonFormat(pattern = Dates.Pattern.DATE)
    private Date birthday;
    /**
     * 性别：1-男/0-女
     */
    private Integer sex;
    /**
     * 是否启用：1/0
     */
    private Integer enabled;

    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

}