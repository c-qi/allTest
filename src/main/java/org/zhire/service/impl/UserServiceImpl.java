package org.zhire.service.impl;

import org.zhire.mapper.UserMapper;
import org.zhire.pojo.User;
import org.zhire.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper mapper;

    @Override
    public List<User> findName(String name) {
        return null;
    }

    @Override
    public List<User> findAll() {
        int i = 1 / 0;
        return null;
    }

    @Override
    public User login(String name, String pass) {
        return mapper.selectByParam(name, pass);
    }
    // @Autowired
    // private UserMapper mapper;

    // @Override
    // @CacheEvict(value = "userCache", allEntries = true) // @CacheEvict：删除缓存
    // 属性 allEntries 指定是否清空整个缓存区。
//    public List<User> findName(String name) {
//        System.out.println("Redis is delete");
//        return mapper.findByName(name);
//    }

    //  @Override
    // @Cacheable(value = "userCache", key = "'user.findAll'") //添加/使用缓存
    // 属性 value 是缓存的名字，在执行的时候，会找叫这个名字的缓存使用/删除
    // 属性 key 默认情况下是空串””,是 Spring 的一种表达式语言 SpEL，我们这里可以随意指定，但是需要注意一定要加单引号
    //public List<User> findAll() {
    //    return mapper.findAll();
    //  }
}
