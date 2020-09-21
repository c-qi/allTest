package org.zhire.design.bulider.bulidernew;

import lombok.Data;

@Data
public class ResourcePoolConfig {

    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;
    private String name;
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle = DEFAULT_MAX_IDLE;
    private int minIdle = DEFAULT_MIN_IDLE;

    ResourcePoolConfig(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        ResourcePoolConfig r = new ResourcePoolConfig("asdas");
        System.out.println(r);
    }

}
