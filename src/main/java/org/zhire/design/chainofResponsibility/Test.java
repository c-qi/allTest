package org.zhire.design.chainofResponsibility;

import lombok.extern.slf4j.Slf4j;

/**
 * 责任链模式
 *   避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止，这就是职责链模式。
 * ​ 在职责链模式中最关键的一点就是客户提交请求后，请求沿着链往下传递直到有一个处理者处理它，在这里客户无需关心它的请求是哪个处理者来处理，反正总有一个处理者会处理它的请求。
 * ​ 在这里客户端和处理者都没有对方明确的信息，同时处理者也不知道职责链中的结构。所以职责链可以简化对象的相互连接，他们只需要保存一个指向其后续者的引用，而不需要保存所有候选者的引用。
 * ​ 在职责链模式中我们可以随时随地的增加或者更改一个处理者，甚至可以更改处理者的顺序，增加了系统的灵活性。处理灵活性是增加了，但是有时候可能会导致一个请求无论如何也得不到处理，它会被放置在链末端，这个既是职责链的优点也是缺点。
 *
 *  以请假为例 学生请假 小于三天辅导员处理 大于三天院长处理
 *  学生是申请者，其余的都是请求处理者。职责链可以将请求的处理者组织成一条链，并且将请求沿着链传递，如果某个请求处理者能够处理请求则处理，否则将该请求交由上级处理。
 *
 * 三个角色：
 * Handler: 抽象处理者。定义了一个处理请求的方法。所有的处理者都必须实现该抽象类。
 * ConcreteHandler: 具体处理者。处理它所负责的请求，同时也可以访问它的后继者。如果它能够处理该请求则处理，否则将请求传递到它的后继者。
 * Client: 客户类。
 *
 * 优点
 * ​ 1、降低耦合度。它将请求的发送者和接受者解耦。
 * ​ 2、简化了对象。使得对象不需要知道链的结构。
 * ​ 3、增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。
 * ​ 4、增加新的请求处理类很方便。
 *
 * 缺点
 * ​ 1、不能保证请求一定被接收。
 * ​ 2、系统性能将受到一定影响，而且在进行代码调试时不太方便；可能会造成循环调用。
 * ​ 3、可能不容易观察运行时的特征，有碍于除错。
 *
 * @Author: chenqi
 * @Date: 2019.10.23 10:27
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        Leader instructor = new Instructor("张三", new Dean("李四", null));
        LeaveNode leaveNode1 = new LeaveNode(1, "chenqi");
        LeaveNode leaveNode2 = new LeaveNode(4, "chenqi");
        LeaveNode leaveNode3 = new LeaveNode(6, "chenqi");
        instructor.handRequest(leaveNode1);
        instructor.handRequest(leaveNode2);
        instructor.handRequest(leaveNode3);

    }
}
