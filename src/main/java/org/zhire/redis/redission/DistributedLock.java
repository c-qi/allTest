package org.zhire.redis.redission;

import java.util.function.Supplier;

public interface DistributedLock {

    <T> T lockAndExec(String lockKey, Supplier<T> supplier);

    <T> T lockAndExecOnce(String lockKey, Supplier<T> supplier);

    <T> T fairLockAndExec(String lockKey, Supplier<T> supplier);

    <T> T multiLockAndExec(String lockKey, String typeLockKey, Supplier<T> supplier);

}
