package org.zhire.demo.guava.event;

import lombok.Data;

/**
 * @Date 2020/12/28 11:14
 * @Author by chenqi
 */
@Data
public class TestEvent {
    private Integer id;
    private String name;

    public TestEvent(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


}
