package org.zhire.pojo;


import lombok.Data;

import java.util.Objects;

@Data
public class UserCopy extends User{
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserCopy userCopy = (UserCopy) o;
        return Objects.equals(id, userCopy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }


}
