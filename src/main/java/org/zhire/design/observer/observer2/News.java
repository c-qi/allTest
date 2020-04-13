package org.zhire.design.observer.observer2;

/**
 * 此类不属于观察者模式必须的类，用来存放事件的信息
 *
 * @Author: chenqi
 * @Date: 2019.9.23 09:51
 */
public class News {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
