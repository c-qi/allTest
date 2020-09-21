package org.zhire.design.decorator;

import org.zhire.pojo.User;

import java.util.List;

public class DecoratorUserImplPlus implements DecoratorUser {

    private DecoratorUser decoratorUser;

    public DecoratorUserImplPlus(DecoratorUser decoratorUser) {
        this.decoratorUser = decoratorUser;
    }

    @Override
    public List<User> findAll() {
        System.out.println("开始对方法增强");
        List<User> all = decoratorUser.findAll();
        all.forEach(System.out::println);
        System.out.println("结束对方法增强");
        return all;
    }

}
