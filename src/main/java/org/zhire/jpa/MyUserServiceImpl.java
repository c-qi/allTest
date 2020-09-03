package org.zhire.jpa;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
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
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class MyUserServiceImpl implements MyUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryTest userRepositoryTest;

    @Override
    public void insert() {
        for (int i = 100; i < 10000; i++) {
            User user = new User();
            user.setNickName("cq" + i);
            user.setId(Long.parseLong(i + ""));
            userRepository.save(user);
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
        zp.setFromType(ZpUserBusiness.FROMTYPE.TEST);
        userRepositoryTest.save(zp);
        Optional<ZpUserBusiness> first = userRepositoryTest.findFirstByFromType(fromType);
        ZpUserBusiness zpUserBusiness = first.get();
        return zpUserBusiness;
    }

    @Override
    public void findAllList() {
        int pageNumber = 0;
        int pageSize = 100;
        while (true) {
            System.out.println("num " + pageNumber + "size " + pageSize);
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<User> listPage = userRepository.findAll(pageable);
            List<User> list = listPage.get().collect(Collectors.toList());
            CountDownLatch countDownLatch = new CountDownLatch(list.size());
            System.out.println("countl:" + countDownLatch);
            if (list.size() == 0) {
                return;
            }
            updateImage(pageNumber++, pageSize, list, countDownLatch);
        }
    }


    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("线程-%d").build();
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() * 2,
            Runtime.getRuntime().availableProcessors() * 2,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(100),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy());

    @Test
    public void updateImage(int pageNumber, int pageSize, List<User> list, CountDownLatch countDownLatch) {
        System.out.println("num " + pageNumber + "size " + pageSize);
        list.forEach(l -> {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " " + l.getId());
                l.setNickName(l.getId() + "cqq");
                userRepository.save(l);
                countDownLatch.countDown();
            });
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完成");
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
