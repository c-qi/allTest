package org.zhire.design.decorator;

import org.zhire.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class DecoratorUserImpl implements DecoratorUser {
    @Override
    public List<User> findAll() {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("Cq", "111"));
        list.add(new User("Cqq", "222"));
        return list;
    }
}
