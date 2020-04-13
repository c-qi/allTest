package org.zhire.design.chainofResponsibility;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 请假条
 *
 * @Author: chenqi
 * @Date: 2019.10.23 10:16
 */
@Data
@AllArgsConstructor
public class LeaveNode {
    private int num;
    private String person;
}
