package org.zhire.jpa;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MyUserServiceImpl implements MyUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void insert() {
        User user = new User();
        user.setUserName("cq");
        user.setId(0L);
        user.setNickName("ccc");
        user.setEmail("cc@cc.cc");
        user.setPassWord("1312");
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByUserNameOrEmail(String userName, String email) {
        return userRepository.findByUserNameOrEmail(userName, email);
    }

    @Override
    public List<User> findAll(int page, int pageSize) {
        page = (page - 1) * pageSize;
        long count = userRepository.count();
        System.out.println("All count：" + count);
        return userRepository.findAllUser(page, pageSize);
    }

    @Override
    public List<UserAndInfo> findAllInfo(int page, int pageSize) {
        page = (page - 1) * pageSize;
        long count = userRepository.count();
        System.out.println("All count：" + count);
        List<Map<String, Object>> userInfo = userRepository.findAllUserInfo(page, pageSize);
        List<UserAndInfo> userAndInfos = JSON.parseArray(JSON.toJSONString(userInfo), UserAndInfo.class);
        System.out.println(userAndInfos);
        return userAndInfos;
    }

    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> hashMap = Maps.newConcurrentMap();
        Map<String, Object> hashMap2 = Maps.newConcurrentMap();
        hashMap.put("id", 1);
        hashMap.put("userName", "chenqi");
        hashMap2.put("id", 2);
        hashMap2.put("userName", "chenqi2");
        list.add(hashMap);
        list.add(hashMap2);
        List<User> users = JSON.parseArray(JSON.toJSONString(list), User.class);
        System.out.println(list);
        System.out.println(users);
    }
}
