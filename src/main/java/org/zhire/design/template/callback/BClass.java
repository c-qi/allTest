package org.zhire.design.template.callback;

class BClass {
    void process(ICallback callback) {
        new Thread(() -> {
            System.out.println("1111");
            callback.methodToCallback();
            System.out.println("2222");
        }).start();
    }
}

