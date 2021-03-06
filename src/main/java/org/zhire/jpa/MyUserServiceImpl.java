package org.zhire.jpa;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
@Service
public class MyUserServiceImpl implements MyUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryTest userRepositoryTest;
    @Autowired
    private MyUserService myUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert() {
//        for (int i = 100; i < 10000; i++) {
//            User user = new User();
//            user.setNickName("cq" + i);
//            user.setId(Long.parseLong(i + ""));
//            userRepository.save(user);
//        }
        long count = userRepository.count();
        System.out.println("前：" + count);
        User user = new User();
        user.setNickName("fqwqfw");
        User save = userRepository.save(user);
        System.out.println(save);
        long counta = userRepository.count();
        System.out.println("后" + counta);
        try {
            CompletableFuture<ZpUserBusiness> future = supplyAsync(() -> myUserService.testTran(save));
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    @Transactional
    public ZpUserBusiness testTran(User user) {
        long counta = userRepository.count();
        System.out.println("mmm:" + counta);
        ZpUserBusiness save = null;
        ZpUserBusiness zpUserBusiness = new ZpUserBusiness();
        zpUserBusiness.setUserId(user.getId());
        zpUserBusiness.setFromType(ZpUserBusiness.FROMTYPE.WORKS);
        save = userRepositoryTest.save(zpUserBusiness);
        //int a[] = {1};
        //System.out.println(a[2]);
        return save;
    }


    @Override
    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        log.info("user:{}", user);
        Optional<User> user1 = Optional.of(user);
        log.info("user1:{}", user1);
        return user;
    }

    @Override
    public User findByUserNameOrEmail(String userName, String email) {
        Optional<User> user = userRepository.findByUserNameOrEmail(userName, email);
        System.out.println("user: " + user);
        return user.orElseGet(User::new);
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


    /**
     * 使用线程池分页批量更新数据库
     * pageNumber要永远为第一页 否则会漏数据没更新
     */
    @Override
    public void findAllList() {
        int pageNumber = 0;
        while (true) {
            Pageable pageable = PageRequest.of(0, 100);
            Page<User> listPage = userRepository.findAllByFlag(pageable, 0);
            List<User> list = listPage.get().collect(Collectors.toList());
            CountDownLatch countDownLatch = new CountDownLatch(list.size());
            System.out.println("countDownLatch:" + countDownLatch);
            if (list.size() == 0) {
                System.out.println("执行完成");
                return;
            }
            updateImage(pageNumber++, list, countDownLatch);
        }
    }

    @Override
    @Transactional
    public void updateById(String id) {
        Optional<User> user = userRepository.findById(Long.parseLong(id));
        User user1 = user.get();
        user1.setUserName("update");
        userRepository.save(user1);
        while (true) {

        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User insert(User user) {
        //User save = userRepository.save(user);
        this.inOne();
        this.inTwo();
        return new User();
    }

    private void inOne() {
        User user = new User();
        user.setUserName("cq2021");
        userRepository.save(user);
    }

    private void inTwo() {
        User user = new User();
        user.setUserName("cq203321");
        userRepository.save(user);
        int i = 1 / 0;
    }

    @Override
    public void findIn() {
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024 + " M");
        ArrayList<Long> list = new ArrayList<>();
        for (Long i = 0L; i < 30000L; i++) {
            list.add(i);
        }
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024 + " M");

        System.out.println(userRepository.findAllByIdIn(list));

    }


    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("线程-%d").build();
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() * 2,
            Runtime.getRuntime().availableProcessors() * 2,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(2),
            threadFactory,
            new CustomRejectedExecutionHandler()); // 使用自定义的拒绝策略，队列满了，阻塞等待，这样可以不使用countDownLatch来阻塞线程
    // 使用拒绝策略-抛异常
    // new ThreadPoolExecutor.AbortPolicy());


    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                // 核心改造点，由blockingqueue的offer改成put阻塞方法
                System.out.println("放入了队列");
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void updateImage(int pageNumber, List<User> list, CountDownLatch countDownLatch) {
        System.out.println("num " + pageNumber);
        list.forEach(l -> {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " " + l.getId());
                supplyAsync(() -> {
                    if (l.getUserName().contains("cq")) {
                        l.setUserName("chenqi");
                    }
                    return l;
                });
                supplyAsync(() -> {
                    if (l.getNickName() == null) {
                        l.setNickName("cq");
                    }
                    return l;
                });
                supplyAsync(() -> {
                    if (l.getEmail() == null) {
                        l.setEmail("chenqi@cq.com");
                    }
                    return l;
                });
                supplyAsync(() -> {
                    if (l.getPassWord() == null) {
                        l.setPassWord("chenqichenqi");
                    }
                    return l;
                });
                l.setFlag(1);
                userRepository.save(l);
                //countDownLatch.countDown();
            });
        });
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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
