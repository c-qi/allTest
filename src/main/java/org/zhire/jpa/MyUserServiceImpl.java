package org.zhire.jpa;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MyUserServiceImpl implements MyUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryTest userRepositoryTest;
    @Override
    public void insert() {
        ZpUserBusiness user = new ZpUserBusiness();
        user.setFromType(ZpUserBusiness.FROMTYPE.TEST);
        user.setUserId(123123123L);
        user.setId(0L);
        userRepositoryTest.save(user);
        if (user.getFromType().equals(ZpUserBusiness.FROMTYPE.TEST)){
            System.out.println("12312312312312312");
        }
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
        // 单表查询可用自带分页
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, pageSize, sort);
        Page<User> all = userRepository.findAll(pageable);
        System.out.println("使用自带分页结果：" + all);
        all.forEach(l -> {
            System.out.println(l);
        });
        return userRepository.findAllUser(page, pageSize);
    }

    @Override
    public List<UserAndInfo> findAllInfo(int page, int pageSize) {
        // 多表查询可手写分页
        page = (page - 1) * pageSize;
        long count = userRepository.count();
        System.out.println("All count：" + count);
        List<Map<String, Object>> userInfo = userRepository.findAllUserInfo(page, pageSize);
        List<UserAndInfo> userAndInfos = JSON.parseArray(JSON.toJSONString(userInfo), UserAndInfo.class);
        System.out.println(userAndInfos);
        return userAndInfos;
    }

    @Override
    public ZpUserBusiness findFirst(ZpUserBusiness.FROMTYPE fromType) {
        ZpUserBusiness zp = new ZpUserBusiness();
        zp.setUserId(System.currentTimeMillis());
        zp.setFromType(ZpUserBusiness.FROMTYPE.WORKS);
        userRepositoryTest.save(zp);
        Optional<ZpUserBusiness> first = userRepositoryTest.findFirstByFromType(fromType);
        ZpUserBusiness zpUserBusiness = first.get();
        return zpUserBusiness;
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
