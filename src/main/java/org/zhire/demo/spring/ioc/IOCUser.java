package org.zhire.demo.spring.ioc;

import lombok.Data;

@Data
public class IOCUser {
    private Integer id;
    private String name;
    private String address;

    public IOCUser() {
    }

    public IOCUser(int i, String s, String s1) {
        this.id = i;
        this.name = s;
        this.address = s1;
    }
}
