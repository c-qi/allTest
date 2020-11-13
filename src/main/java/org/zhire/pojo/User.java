package org.zhire.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class User implements Serializable{
    private String name;
    private String password;
    private String userName;
    private String passWord;
    private String email;
    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
