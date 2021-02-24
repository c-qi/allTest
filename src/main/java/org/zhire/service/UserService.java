package org.zhire.service;

import org.zhire.model.UserDTO;
import org.zhire.pojo.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {
    List<User> findName(String name);

    List<User> findAll();

    User login(String name, String pass);

    @NotNull
    List<UserDTO> findListDTO();
}
