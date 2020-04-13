package org.zhire.design.observer.observer2;

/**
 * 具体观察者角色
 *
 * @Author: chenqi
 * @Date: 2019.9.23 10:10
 */
public class PeopleA implements People {
    @Override
    public void update(News news) {
        System.out.println("这个新闻真好看");
    }
}
