package org.zhire.design.sigle;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 基于枚举的单例模式，来保证线程安全性和实例的唯⼀性
 */
public enum Sigle5 {
    Sigle5;

    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }

    public static void main(String[] args) {
        System.out.println(Sigle5.getId());
        System.out.println(Sigle5.getId());

    }
}
