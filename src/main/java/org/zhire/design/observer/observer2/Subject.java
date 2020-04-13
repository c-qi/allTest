package org.zhire.design.observer.observer2;

/**
 * 抽象主题角色
 *
 * @Author: chenqi
 * @Date: 2019.9.23 09:52
 */
public interface Subject {
//    List<People> peopleList = new ArrayList<>();
//
//   default void add(People people) {
//        peopleList.add(people);
//    }
//   default void remove(People  people){
//        peopleList.remove(people);
//    }
//    void update();

    void add(People people);

    void remove(People people);

    void update();
}
