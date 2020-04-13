package org.zhire.design.chainofResponsibility;

/**
 * 院长
 *
 * @Author: chenqi
 * @Date: 2019.10.23 10:24
 */
public class Dean extends Leader {

    public Dean(String name, Leader leader) {
        super(name, leader);
    }

    @Override
    public void handRequest(LeaveNode leaveNode) {
        if (leaveNode.getNum() <= 5) {
            System.out.println("院长处理了小于五天的请假");
        } else {
            System.out.println("超过五天，拒绝请假");
        }
    }
}
