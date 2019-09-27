package com.spring.springboot;

import org.apache.catalina.core.*;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.websocket.JettyWebSocketContainerCustomizer;
import org.springframework.boot.autoconfigure.websocket.TomcatWebSocketContainerCustomizer;
import org.springframework.boot.autoconfigure.websocket.UndertowWebSocketContainerCustomizer;
import org.springframework.boot.autoconfigure.websocket.WebSocketContainerCustomizer;
import org.springframework.boot.context.embedded.*;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author Frankie Yang on 2019-06-26.
 */
public class EveryContainer {


    // TODO 以及 各种 其他 Customizer


    EmbeddedServletContainerCustomizer wef029j3f02j30f9;
        ServerProperties g34g03j0934g;
        WebSocketContainerCustomizer gj0349gj034jg0934;
            TomcatWebSocketContainerCustomizer gg3409gj093;
            UndertowWebSocketContainerCustomizer gj0394jg0394jg;
            JettyWebSocketContainerCustomizer fg0943jg0394g;


    ConfigurableEmbeddedServletContainer conownow;
        AbstractConfigurableEmbeddedServletContainer fwf23f23;
        EmbeddedServletContainerFactory wewewe23232;
            AbstractEmbeddedServletContainerFactory wewe2322323;
                TomcatEmbeddedServletContainerFactory tomcatfs;
                UndertowEmbeddedServletContainerFactory undertowfs;
                JettyEmbeddedServletContainerFactory jettyfs;


    EmbeddedServletContainer cont;
        TomcatEmbeddedServletContainer weiuifu23;
        UndertowEmbeddedServletContainer wewe23f23f;
        JettyEmbeddedServletContainer gooi43gnoi2323;



    StandardServer ssss;
    StandardService ssv;
    StandardEngine ergerg;
    StandardHost sfsdfh;
    StandardContext efjowefic;
    StandardWrapper erfkergklw;


}

@Component
class AppContainerCustomizer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(38080);
        container.setContextPath("/xxpath");

    }
}