package org.zhire.design.observer.observer2;

/**
 * 观察者模式
 * 观察者模式定义了对象之间的一对多依赖关系，这样一来，当一个对象改变状态时，
 * 它的所有依赖者都会收到通知并且自动更新。
 * 在这里，发生改变的对象称之为观察目标，而被通知的对象称之为观察者。一个观察目标可以对应多个观察者，而且这些观察者之间没有相互联系，
 * 所以么可以根据需要增加和删除观察者，使得系统更易于扩展。
 * 观察者模式又称为发布-订阅模式。
 * 在观察者设计模式中，一般有四个角色：、
 * Subject：目标。他把所有对观察者对戏的引用保存在一个聚集里，每一个主题都可以有多个观察者。
 * Observer：观察者。为所有的具体观察者定义一个接口，在得到主题的通知时能够及时的更新自己。
 * ConcreteSubject：具体主题。将有关状态存入具体观察者对象。在具体主题发生改变时，给所有的观察者发出通知。
 * ConcreteObserver:具体观察者。实现抽象观察者角色所要求的更新接口，以便使本身的状态与主题状态相协调。
 *
 * @Author: chenqi
 * @Date: 2019.9.23 10:11
 */
public class Test {
    public static void main(String[] args) {
        Subject subject = new NewsSubject();
        subject.add(new PeopleA());
        subject.add(new PeopleB());
        subject.add(new PeopleC());
        subject.update();
    }
}
