package com.spring.springboot;

import com.spring.springboot.appListener.*;
import com.spring.springboot.saRunListener.MySprAppRunLsnr;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.BackgroundPreinitializer;
import org.springframework.boot.builder.ParentContextCloserApplicationListener;
import org.springframework.boot.context.FileEncodingApplicationListener;
import org.springframework.boot.context.config.AnsiOutputApplicationListener;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.config.DelegatingApplicationListener;
import org.springframework.boot.context.event.*;
import org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener;
import org.springframework.boot.logging.ClasspathLoggingApplicationListener;
import org.springframework.boot.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.*;

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


    //  2
//    SpringApplicationRunListeners springAppRunLsnrs;
    SpringApplicationRunListener springAppRunLsnr;
        EventPublishingRunListener ergergerg;
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
        ParentContextCloserApplicationListener ewf;
        FileEncodingApplicationListener fwefwe;
        AnsiOutputApplicationListener fwef;
        DelegatingApplicationListener ddddergerd;
        LiquibaseServiceLocatorApplicationListener gwgwegweg;
        ClasspathLoggingApplicationListener fwefwewefwef;
        LoggingApplicationListener fwefweflwefl;
        BackgroundPreinitializer gwegwegweg;
        //  读配置文件 application.properties 和 application.yaml
        ConfigFileApplicationListener fwefewf;


    /*
    * ApplicationContextInitializer
    * 和 ApplicationListener
    * 的批量加载处理？
    * */


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


    ApplicationEventMulticaster gergerg;
        AbstractApplicationEventMulticaster ewwefwef;
            SimpleApplicationEventMulticaster sdgweg;



}
