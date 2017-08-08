package com.shellcore.android.myphotolibrary.libs.base;

/**
 * Created by Cesar on 07/08/2017.
 */

public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
