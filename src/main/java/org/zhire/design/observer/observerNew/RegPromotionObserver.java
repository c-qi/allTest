package org.zhire.design.observer.observerNew;

public class RegPromotionObserver implements RegObserver {
    // private PromotionService promotionService; // 依赖注⼊
    @Override
    public void handleRegSuccess(long userId) {
        //   promotionService.issueNewUserExperienceCash(userId);
    }
}
