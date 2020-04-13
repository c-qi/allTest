package org.zhire.design.chainofResponsibility;

/**
 * 抽象处理者
 *
 * @Author: chenqi
 * @Date: 2019.10.23 10:17
 */

public abstract class Leader {
    public String name;
    public Leader leader;

    public Leader(String name, Leader leader) {
        this.name = name;
        this.leader = leader;
    }

    public abstract void handRequest(LeaveNode leaveNode);
}
