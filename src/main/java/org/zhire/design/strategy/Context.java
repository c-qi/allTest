package org.zhire.design.strategy;

/**
 * @Author: chenqi
 * @Date: 2019.7.24 20:56
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int exe(int num1, int num2) {
        return strategy.doOperation(num1,num2);

    }
}
