package org.zhire.jpa;

import org.zhire.utils.R;

import java.util.List;

public interface MyUserService {
    void insert();

    User findByUserName(String userName);

    User findByUserNameOrEmail(String userName, String email);

    List<User> findAll(int page, int pageSize);

    List<UserAndInfo> findAllInfo(int page, int pageSize);

    ZpUserBusiness findFirst(ZpUserBusiness.FROMTYPE fromType);

    R update(Integer id, String userName, String password);
}
