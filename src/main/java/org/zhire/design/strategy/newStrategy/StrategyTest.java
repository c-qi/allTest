package org.zhire.design.strategy.newStrategy;

/**
 * 模板模式
 * 把⼀个算法中不变的流程抽象到⽗类的模板⽅法templateMethod()中，
 * 将可变的部分 method1()、method2()留给⼦类ContreteClass1和ContreteClass2来实现。
 * 所有的⼦类都可以复⽤⽗类中 模板⽅法定义的流程代码。
 * Java IO类库中，有很多类的设计⽤到了模板模式，⽐如InputStream、OutputStream、Reader、Writer
 */
public class StrategyTest {
    public static void main(String[] args) {
        AbstractClass s = new ContreteClass1();
        s.templateMethod();
        s.method1();
        s.method2();
    }
}
