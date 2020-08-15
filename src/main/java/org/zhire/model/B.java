package org.zhire.model;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class B extends A {
    private String name;

    public static void main(String[] args) {
        A a = new A();
        a.setId(1111);
        A a2 = new A();
        a2.setId(22);
        B b = new B();
        BeanUtils.copyProperties(a,b);
        b.setName("cq");
        System.out.println(b.getId());
        System.out.println(b.getName());

        ArrayList<A> li = new ArrayList<>();
        li.add(a);
        li.add(a2);
        List<A> list = new ArrayList<>(li);
        System.out.println(list);

    }
}
