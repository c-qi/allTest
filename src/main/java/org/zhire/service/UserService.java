package org.zhire.service;

import org.zhire.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findName(String name);

    List<User> findAll();

    User login(String name, String pass);
}
