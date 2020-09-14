package org.zhire.demo.spring.ioc;

import lombok.Data;

@Data
public class IOCUser {
    private Integer id;
    private String name;
    private String address;
    private Long ctime;
    private STATUS status;

    public IOCUser(int i, String s, String s1) {
    }

    public enum STATUS{
        Default,
        NO
    }

    public IOCUser() {
    }

    public IOCUser(int i, String s, String s1, Long ctime, IOCUser.STATUS status) {
        this.id = i;
        this.name = s;
        this.address = s1;
        this.ctime =ctime;
        this.status = status;
    }
}
