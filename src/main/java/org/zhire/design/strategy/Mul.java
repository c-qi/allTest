package org.zhire.design.strategy;

/**
 * @Author: chenqi
 * @Date: 2019.7.24 20:55
 */
public class Mul implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
