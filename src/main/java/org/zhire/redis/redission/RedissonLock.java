//package org.zhire.redis.redission;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.RedissonMultiLock;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//
//import java.util.concurrent.TimeUnit;
//import java.util.function.Supplier;
//
//@Slf4j
//@AllArgsConstructor
//public class RedissonLock implements DistributedLock {
//
//    private RedissonClient redissonClient;
//
//    @Override
//    public <T> T lockAndExec(String lockKey, Supplier<T> supplier) {
//        String threadName = Thread.currentThread().getName();
//        RLock rLock = redissonClient.getLock(lockKey);
//        try {
//            rLock.lock(10, TimeUnit.SECONDS);
//            log.info("ThreadName:{}--获取lock:{}成功", threadName, lockKey);
//            T t = supplier.get();
//            log.info("==lock==result={}", t);
//            return t;
//        } finally {
//            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
//                rLock.unlock();
//                log.info("ThreadName:{}--释放lock:{}", threadName, lockKey);
//            }
//        }
//    }
//
//    @Override
//    public <T> T lockAndExecOnce(String lockKey, Supplier<T> supplier) {
//        String threadName = Thread.currentThread().getName();
//        RLock rLock = redissonClient.getLock(lockKey);
//        try {
//            boolean isLocked = rLock.tryLock();
//            if (isLocked) {
//                log.info("ThreadName:{}--获取lock:{}成功", threadName, lockKey);
//                T t = supplier.get();
//                log.info("==lock==result={}", t);
//                return t;
//            } else {
//                return null;
//            }
//        } finally {
//            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
//                rLock.unlock();
//                log.info("ThreadName:{}--释放lock:{}", threadName, lockKey);
//            }
//        }
//    }
//
//    @Override
//    public <T> T multiLockAndExec(String lockKey, String typeLockKey, Supplier<T> supplier) {
//        String threadName = Thread.currentThread().getName();
//        RLock rLock = redissonClient.getLock(lockKey);
//        RLock accountTypeLock = redissonClient.getLock(typeLockKey);
//        RedissonMultiLock multiLock = new RedissonMultiLock(rLock, accountTypeLock);
//        try {
//            multiLock.lock(10, TimeUnit.SECONDS);
//            log.info("ThreadName:{}--获取lock:{}成功", threadName, lockKey);
//            T t = supplier.get();
//            log.info("==lock==result={}", t);
//            return t;
//        } finally {
//            if (multiLock.isLocked() && multiLock.isHeldByCurrentThread()) {
//                multiLock.unlock();
//                log.info("ThreadName:{}--释放lock1:{},lock2:{}", threadName, lockKey, typeLockKey);
//            }
//        }
//    }
//
//    @Override
//    public <T> T fairLockAndExec(String lockKey, Supplier<T> supplier) {
//        String threadName = Thread.currentThread().getName();
//        RLock rLock = redissonClient.getFairLock(lockKey);
//        try {
//            rLock.lock(10, TimeUnit.SECONDS);
//            log.info("ThreadName:{}--获取lock:{}成功", threadName, lockKey);
//            T t = supplier.get();
//            log.info("==lock==result={}", t);
//            return t;
//        } finally {
//            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
//                rLock.unlock();
//                log.info("ThreadName:{}--释放lock:{}", threadName, lockKey);
//            }
//        }
//    }
//
//}
//
//
