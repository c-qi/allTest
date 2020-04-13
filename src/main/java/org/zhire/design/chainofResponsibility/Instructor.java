package org.zhire.design.chainofResponsibility;

/**
 * 辅导员
 *
 * @Author: chenqi
 * @Date: 2019.10.23 10:21
 */
public class Instructor extends Leader {
    public Instructor(String name, Leader leader) {
        super(name, leader);
    }

    @Override
    public void handRequest(LeaveNode leaveNode) {
        if (leaveNode.getNum() <= 3) {
            System.out.println("辅导员处理了小于三天的请假");
        } else {
            if (leader != null) {
                leader.handRequest(leaveNode);
            }
        }
    }
}
