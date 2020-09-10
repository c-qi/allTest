package org.zhire.demo.spring.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary // 以我为主注入到spring容器 或者在 @Autowired 注解下面加 @Qualifier("cat") 按名称注入
public class DIDog implements DIAnimal {
    @Override
    public void use() {
        System.out.println("wang");
    }
}
