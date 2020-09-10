package org.zhire.demo.spring.di;

import org.springframework.stereotype.Service;

@Service
public class DICat implements DIAnimal {
    @Override
    public void use() {
        System.out.println("miao");
    }
}
