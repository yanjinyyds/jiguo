package com.xt.jiguo.entity;

import com.xt.jiguo.valid.UserLoginValidation;
import com.xt.jiguo.valid.UserRegisterValidation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    @NotNull(groups = {UserRegisterValidation.class, UserLoginValidation.class})
    @Pattern(groups = {UserRegisterValidation.class,UserLoginValidation.class}, regexp="1\\d{10}")
    private String phone;
    @NotNull(groups = {UserRegisterValidation.class})
    private String name;
    @NotNull(groups = {UserRegisterValidation.class,UserLoginValidation.class})
    private String password;

    private String image;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}