package org.zhire.design.observer.observerNew;

public class RegNotificationObserver implements RegObserver {

    //private NotificationService notificationService;

    @Override
    public void handleRegSuccess(long userId) {
      //  notificationService.sendInboxMessage(userId, "Welcome...");
    }
}