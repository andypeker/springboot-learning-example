package com.spring.springboot;

import com.spring.springboot.appListener.*;
import com.spring.springboot.saRunListener.MySprAppRunLsnr;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * @author Frankie Yang on 2019-06-21.
 */
public class EveryListenerEvent {




    //  ############################ Listener

    /**
     * 类 SpringApplicationRunListener 通过 自动配置被调起，
     * 其 子类 EventPublishingRunListener 专门处理 各种 ApplicationListener；
     * 循环地 调起 各个 ApplicationListener 的子类。
     * */

    SpringApplicationRunListener aprlsnr;
        EventPublishingRunListener g303049jg09;
        MySprAppRunLsnr g340g309g039k4g09;  //  Recognized


    //  All Recognized
    ApplicationListener allal;
        MyEnvReadyListener gj09j0934g;
        MyApplicationPreparedListener g30034g;
        MyApplicationStartingListener gj093jg90j3904g;
        MyApplicationFailListener gj039j4g093049g;
        MyApplicationReadyListener gj03j4g093j40g9;
        MyContextRefreshedListener g309j039g40934;
        MyContextClosedListener gj039jg093409g;
        MyContextStartedListener gj03jg903049j;
        MyContextStoppedListener gj039g03940g934g;

    ApplicationEvent g3ig30g0934gk09k;
        SpringApplicationEvent g3g34g34g;
            ApplicationEnvironmentPreparedEvent f3409309g4;
            ApplicationFailedEvent verver340gm09;
            ApplicationReadyEvent g34090934gj0j;
            ApplicationStartingEvent g3409093kg0934;
        ApplicationContextEvent g3m09m34g09;
            ContextRefreshedEvent g30m093jg0934;
            ContextStartedEvent go3ig093094gk;
            ContextStoppedEvent go3i4g093049g0k;



}
