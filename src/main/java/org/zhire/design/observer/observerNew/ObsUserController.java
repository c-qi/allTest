package org.zhire.design.observer.observerNew;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * 用户注册成功后发优惠券，发短信等等许多操作
 * 可以用该模式解耦
 */
public class ObsUserController {

    //  private UserService userService;
    // private PromotionService promotionService;

    public Long register(String telephone, String password) {
        // userService.register();
        // long userId = userService.register(telephone, password);
        // promotionService.issueNewUserExperienceCash(userId);
        return null;
    }

    private List<RegObserver> regObservers = new ArrayList<>(); // ⼀次性设置好，之后也不可能动态的修改

    public void setRegObservers(List<RegObserver> observers) {
        regObservers.addAll(observers);
    }

    public Long register2(String telephone, String password) {
        // long userId = userService.register(telephone, password);
        for (RegObserver observer : regObservers) {
            // observer.handleRegSuccess(userId);
        }
        return null;
    }
}
