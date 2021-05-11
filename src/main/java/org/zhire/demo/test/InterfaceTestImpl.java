package org.zhire.demo.test;

import org.springframework.stereotype.Service;

/**
 * @Date 2021/5/11 14:22
 * @Author by chenqi
 */
@Service
public class InterfaceTestImpl implements InterfaceTest {

    @Override
    public String getName() {
        return null;
    }

    /**
     * Interface类里面可以写加方法体了，但是只能加static或者default
     * 实现类不能重写static方法，
     * 可以选择性重写default方法，如果Interface里的default方法够用就没必要在实现类重写了
     */
//    @Override
//    public String getAddress(Integer a) {
//        return "beijing";
//    }
    public static void main(String[] args) {
        InterfaceTest t = new InterfaceTestImpl();
        String address = t.getAddress(1);
        System.out.println(address);
    }

}
