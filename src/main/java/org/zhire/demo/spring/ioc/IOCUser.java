package org.zhire.demo.spring.ioc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IOCUser {
    private Integer id;
    private String name;
    private String address;
    private Long ctime;
    private STATUS status;

    public IOCUser(int i, String s, String s1) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IOCUser iocUser = (IOCUser) o;
        return Objects.equals(id, iocUser.id) &&
                Objects.equals(name, iocUser.name) &&
                Objects.equals(address, iocUser.address) &&
                Objects.equals(ctime, iocUser.ctime) &&
                status == iocUser.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, ctime, status);
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
