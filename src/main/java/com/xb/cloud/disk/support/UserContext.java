package com.xb.cloud.disk.support;

import com.xb.cloud.disk.entity.User;

/**
 * @author jack
 */
public class UserContext {

    private static final ThreadLocal<User> userHolder = new InheritableThreadLocal<>();

    public static User getUser() {
        return userHolder.get();
    }

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static void remove() {
        userHolder.remove();
    }
}
