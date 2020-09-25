package org.zhire.design.template.callback;

class BClass {
    void process(ICallback callback) {
        System.out.println("1111");
        callback.methodToCallback();
        System.out.println("2222");
    }
}

