package com.spring.springboot.appListener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author Frankie Yang on 2018/1/4.
 */
@Deprecated
public class MyApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("[ApplicationListener]Frankie Yang 的 Application Started ！！！");
    }
}
