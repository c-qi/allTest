package org.zhire.design.observer.observer2;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题角色
 *
 * @Author: chenqi
 * @Date: 2019.9.23 10:10
 */
public class NewsSubject implements Subject {

    List<People> peopleList = new ArrayList<>();


    @Override
    public void add(People people) {
        peopleList.add(people);
    }

    @Override
    public void remove(People people) {
        peopleList.remove(people);
    }

    @Override
    public void update() {
        for (People people : peopleList) {
            News news = new News();
            news.setContent("news news news");
            news.setTitle("title title title");
            people.update(news);
        }
    }

}
