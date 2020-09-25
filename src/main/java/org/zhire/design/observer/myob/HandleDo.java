package org.zhire.design.observer.myob;

import lombok.Data;

@Data
public class HandleDo {

    private Integer aa;

    void get(Handle handle) {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                handle.handle((int) Math.pow(aa, 2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    Integer get2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (int) Math.pow(aa, 2);
    }

    void get3(Handle handle) {
        new Thread(() -> {
            try {
                handle.handle(get2());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


}
