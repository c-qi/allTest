package org.zhire.design.strategy;

/**
 * @Author: chenqi
 * @Date: 2019.7.24 20:53
 */
public class Del implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
