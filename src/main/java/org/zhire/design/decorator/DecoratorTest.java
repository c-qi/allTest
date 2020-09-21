package org.zhire.design.decorator;

/**
 * 装饰器模式对原方法增强，不创建代理类
 * 代理模式对原功能增强，创建代理类
 */
public class DecoratorTest {
    public static void main(String[] args) throws Exception {
        // InputStream in = new FileInputStream("/user/test.txt");
        // JDK的装饰器模式 BufferedInputStream 和 DataInputStream 都是继承InputStream并对其增强
        // InputStream bin = new BufferedInputStream(in);
        // DataInputStream din = new DataInputStream(bin);
        // int data = din.readInt();
        // 实现自己的装饰器模式
        DecoratorUser decoratorUser = new DecoratorUserImpl();
        DecoratorUserImplPlus d = new DecoratorUserImplPlus(decoratorUser);
        d.findAll();


    }
}
