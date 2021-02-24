package org.zhire.service.impl;

import org.springframework.beans.BeanUtils;
import org.zhire.jpa.User;
import org.zhire.model.UserDTO;
import org.zhire.service.DTOConvert;

/**
 * @Date 2021/2/24 14:05
 * @Author by chenqi
 */
public class UserDTOConvert implements DTOConvert<UserDTO, User> {

    @Override
    public User convert(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

}
