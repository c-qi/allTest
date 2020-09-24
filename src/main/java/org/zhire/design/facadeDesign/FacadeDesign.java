package org.zhire.design.facadeDesign;

/**
 * 门面模式
 * ⻔⾯模式为⼦系统提供⼀组统⼀的接⼝，定义⼀组⾼层接⼝让⼦系统更易⽤。
 * 假设有⼀个系统A，提供了a、b、c、d四个接⼝。
 * 系统B完成某个业务功能，需要调⽤A系统的a、b、d接 ⼝。
 * 利⽤⻔⾯模式，我们提供⼀个包裹a、b、d接⼝调⽤的⻔⾯接⼝x，给系统B直接使⽤。
 * 1.解决易⽤性问题
 * 2.解决性能问题
 * 3.解决分布式事务问题（有A服务端分别调B服务的c,d俩接口，需要同时回滚，可以让B服务端提供e接口封装c，d接口，这样就能回滚）
 */
public class FacadeDesign {
    public static void main(String[] args) {

    }
}
