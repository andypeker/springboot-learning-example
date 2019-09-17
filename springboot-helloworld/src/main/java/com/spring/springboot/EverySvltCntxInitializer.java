package com.spring.springboot;

import ch.qos.logback.classic.servlet.LogbackServletContainerInitializer;
import com.spring.springboot.initializer.CustomServletContainerInitializer;
import com.spring.springboot.initializer.CustomServletContainerInitializer2;
import com.spring.springboot.initializer1.MyWebApplicationInitializer;
import com.spring.springboot.initializer1.MyWebApplicationInitializer2;
import org.apache.catalina.startup.WebappServiceLoader;
import org.apache.tomcat.websocket.server.WsSci;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.web.servlet.*;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.ServletContainerInitializer;

/**
 * @author Frankie Yang on 2019-06-20.
 */
public class EverySvltCntxInitializer {


    //  ############################ ServiceLoader -2
    /**
     * responsible for loading SpringServletContainerInitializer
     * (implementation of ServletContainerInitializer) from that file
     *
     * Tomcat Startup Procedure:
     * 1, Tomcat initialize the ServletContext
     * 2, ContextConfig is notified with this context startup event
     * 3, service-loading is delegated to WebappServiceLoader<ServletContainerInitializer>
     * 4, WebappServiceLoader scans in WEB-INF/lib jars for the file
     *      META-INF/services/javax.servlet.ServletContainerInitializer in order to load the implementation
     * 5, Once loaded return to step 3 and, ContextConfig will call implementation's
     *      (here SpringServletContainerInitializer) onStartup method which will do rest of the things.
     *
     * 接口 ServletContainerInitializer 的 实现类 设置在 某个文本文件（javax.servlet.ServletContainerInitializer）中，由 某个 Listener 获取并且实例化。
     * 这个 Listener 是什么？--- ContextConfig（上文已经提到）！
     *
     * Tomcat has LifecycleListeners those will listen to lifecycle events like start, stop etc.
     * org.apache.catalina.startup.ContextConfig is such a startup event listener for a
     * ServletContext that configures the properties of that ServletContext, and the associated defined servlets.
     * 翻译一下，ContextConfig 是一个 监听器，用于 配置 ServletContext 的 properties 并关联 servlet。
     *
     * 一句话， Tomcat 识别到 ServletContext 启动了；
     * 由 ContextConfig，指示 WebappServiceLoader 去加载 各个 ServletContainerInitializer；
     * 加载完成后，再由 ContextConfig 调用启动这些 ServletContainerInitializer 去配置 ServletContext。
     * */

    //  ############################ Initializer 0a
    org.apache.catalina.startup.ContextConfig gg245gweqreg34gg;
    org.apache.catalina.startup.EngineConfig g34go0930g94;
    org.apache.catalina.startup.HostConfig g3g3049gj0340g9;
    org.apache.catalina.startup.UserConfig gg0394jg0934g09;
    //  ############################ Initializer 0b
    WebappServiceLoader eovenoribnoierb;
    //  ############################ Initializer 0c
    ServletContainerInitializer rthr34oi;
        WsSci swioeowie;
        SpringServletContainerInitializer noin340h89034;
        LogbackServletContainerInitializer aaa34g09340g9j09;
        CustomServletContainerInitializer g3ig039g093k4g0k; //   No Recognized
        CustomServletContainerInitializer2 ff4g0j340g9j3049g; //   No Recognized


    /**
     * 类 ServletContainerInitializer 的 子类的 onStartup 方法是web应用中，我们的代码可以控制到的最早时间点。
     * 其 子类 SpringServletContainerInitializer 的 方法onStartup 专门处理各种 WebApplicationInitializer；（也就是说 WebApplicationInitializer 是代码可以控制的起点）
     * 循环地 调起 各个 WebApplicationInitializer 的子类。
     *
     * 简单地说，SpringServletContainerInitializer 负责将 ServletContext 实例化 并委托给 用户定义的 各个WebApplicationInitializer 实现。
     * 每个 WebApplicationInitializer 负责参与初始化 ServletContext 的实际工作。
     * */

    /**
     * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
     * 总而言之，可以总结一下：
     * 0， 始祖级的 Tomcat 的方法 org.apache.catalina.core.StandardContext#startInternal() 调用各个 ServletContainerInitializer 的 onStartup；
     * 1， 其中一个 Initializer --- SpringServletContainerInitializer，启动 各个 WebApplicationInitializer（似乎 ContextLoaderListener（应该是 ServletContextInitializer）有同样的功效）；
     * 2， 接口 WebApplicationInitializer 的 方法onStartup 的参数是 ServletContext；
     * 2b, 其中 子类 SpringBootServletInitializer 有点特殊功能：拉起 ContextLoaderListener；
     *
     * 5， 接口 ApplicationContextInitializer 相关执行（在 SpringApplication 的 方法run，或者 @EnableAutoConfiguration），
     *     其 方法onStartup 的 参数是 ? extends ConfigurableApplicationContext。
     *
     * 说明一下：
     *    通常情况下，SpringServletContainerInitializer 不会被 load & call，所以 各个 WebApplicationInitializer 也就不能被call，
     *    ContextLoaderListener 也不能被 SpringBootServletInitializer call；所以，有其他方式，比如 ServletContextInitializer etc。
     *
     * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
     *
     * TODO TODO TODO TODO TODO Important !
     * Spring Boot Reference Guide 里明确说了，嵌入 的 Servlet 容器，默认不执行 ServletContainerInitializer
     *（也就没机会执行 WebApplicationInitializer 那一套）；取而代之的，就是 ServletContextInitializer 接口；
     * Have another word, 如果 执行了 ServletContainerInitializer， 则 WebApplicationInitializer 会被识别 并 执行。
     *
     * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
     *
     * 补充，上面的说法 关于 WebApplicationInitializer 和 ContextLoaderListener 完全正确。
     * programmatic declaration and registration of servlet, filter and listener components，
     * these methods from within ServletContextListener#contextInitialized method
     * or ServletContainerInitializer#onStartup method.
     *
     * 各个 WebApplicationInitializer/ServletContextInitializer 是为了【通过 JavaConfig】 "丰富" ServletContext，
     * 而 ServletContextListener（ContextLoaderListener）的功效也是 "找机会"【通过xml】 "丰富" ServletContext。
     *
     * TODO 这里说的“丰富”，是否就是 Root ApplicationContext 的初始化呢？
     *
     * 再补充：上面的说法 关于 WebApplicationInitializer 和 ContextLoaderListener 完全错误！
     * WebApplicationInitializer 与 ContextLoaderListener 没有任何对等关系！
     * 其实 ContextLoaderListener 比较简单，监听容器的某个事件，然后触发执行 initWebApplicationContext，没有其他逻辑；
     * 而 WebApplicationInitializer 没这么简单。首先，WebApplicationInitializer 是一个接口，它可以有多个子类，这些子类
     * 都会被 SpringServletContainerInitializer 唤起；而且 WebApplicationInitializer 承担的职责范围很大，包括不限于
     * [ContextLoaderListener 和] DispatcherServlet。TODO 这里还需要进一步研究理解。
     *
     * 由此可见，ContextLoaderListener 的作用时间很靠前，而 WebApplicationInitializer 作用时间比较靠后。
     *
     * 上面的“完全错误”说法，严重错误！
     * WebApplicationInitializer 是一个配置“接口”，它可以配置很多东西，包括 ContextLoaderListener，因为 ContextLoaderListener 也需要配置。
     * 其实，WebApplicationInitializer 与 ContextLoaderListener，并不存在先后顺序关系，它们不在同一个维度；
     * ContextLoaderListener 执行一些动作，效果是给 ServletContext 设置一个属性，比如 ROOT_APPLICATION_CONTEXT啥啥，
     * 而 WebApplicationInitializer 就是给 ServletContext 设置任何/全部属性，只是记下来并没有马上执行。
     * 上面之所以说，WebApplicationInitializer 不简单，就是因为 WebApplicationInitializer 并没有什么具体行为，因为它就是一个 Hook。
     *
     * 【上面对对错错的说法，全都错误】WebApplicationInitializer 和 ContextLoaderListener 毫无关系，不能相提并论！
     * 可以说 WebApplicationInitializer 能做各种配置，Root上下文、子上下文、Filter 和 Listener 以及其他，还可以搞 ContextLoaderListener；
     * 而 ContextLoaderListener 只搞 Root上下文（Root上下文 也是起点啊，似乎并不能说只能搞 Root上下文）。
     *
     * 有时候，使用了 WebApplicationInitializer，就不再需要 ContextLoaderListener。
     *
     * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
     * 可以这么说，SpringServletContainerInitializer/WebApplicationInitializer 和 ContextLoaderListener 是 同一个“目标”的 不同“起点”。
     * 当然，“起点”也不止这 2个，还有其他“起点”！
     * “起点” SpringServletContainerInitializer 需要 WebApplicationInitializer，其他“起点”需要 ServletContextInitializer ！
     * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
     *
     * 接口/Hook WebApplicationInitializer 有多种子类，比如 AbstractContextLoaderInitializer 可以用来设置 ROOT上下文(Root-Application-Context)，
     * 而那些 Abstract***DispatcherServletInitializer，用来设置 子上下文（servlet-Application-Context），也可以设置 ROOT上下文。
     * */


    ApplicationContextInitializer aci;
    ContextLoader gregerg;


    /**
     *
     * TODO Important Definition:
     *
     * "web application": There is one context (ServletContext) per "web application" per Java Virtual Machine.
     *
     * */


    /**
     * //1
     * interface ServletContainerInitializer :
     * has one implement class SpringServletContainerInitializer.
     *
     * //2
     * interface WebApplicationInitializer :
     *
     * Interface to be implemented in Servlet 3.0+ environments in order to configure the ServletContext
     * programmatically -- as opposed to (or possibly in conjunction with) the traditional web.xml-based
     * approach.
     *
     * Implementations of this SPI will be detected automatically by SpringServletContainerInitializer,
     * which itself is bootstrapped automatically by any Servlet 3.0 container. See its Javadoc for details
     * on this bootstrapping mechanism.
     *
     * //3
     * interface ServletContextInitializer :
     *
     * Interface used to configure a Servlet 3.0+ context programmatically. Unlike WebApplicationInitializer,
     * classes that implement this interface (and do not implement WebApplicationInitializer) will not be
     * detected by SpringServletContainerInitializer and hence will not be automatically bootstrapped by
     * the Servlet container.
     *
     * This interface is primarily designed to allow ServletContextInitializers to be managed by Spring
     * and not the Servlet container.
     *
     * //4
     * interface ApplicationContextInitializer<C extends ConfigurableApplicationContext>
     *
     * Callback interface for initializing a Spring ConfigurableApplicationContext prior to being refreshed. [line 482~485]
     *
     * Typically used within web applications that require some programmatic initialization of the application
     * context. For example, registering property sources or activating profiles against the context's environment.
     * See ContextLoader and FrameworkServlet support for declaring a "contextInitializerClasses" context-param
     * and init-param, respectively.
     *
     * ApplicationContextInitializer processors are encouraged to detect whether Spring's Ordered interface has
     * been implemented or if the @Order annotation is present and to sort instances accordingly if so prior to
     * invocation.
     * */


    /**
     * interface ServletContainerInitializer :
     *
     * The ServletContainerInitializer implementation is intented to be bundled in a JAR file which is in turn been
     * dropped in /WEB-INF/lib of the webapp. The JAR file itself should have
     * a /META-INF/services/javax.servlet.ServletContainerInitializer file containing the FQN of the
     * ServletContainerInitializer implementation in the JAR. Please note that this file should thus not be
     * placed in the webapp itself!
     *
     * This allows webapp module developers to let their JAR file hook on webapp's startup and shutdown cycle.
     * It's true that they could also have used a ServletContextListener with @WebListener for this, but this
     * won't work if the webapp's own web.xml file has a metadata-complete="true" attribute set in <web-app>
     *     which means that the webapp shouldn't scan JARs for annotations (which saves startup time).
     *
     * That the ServletContainerInitializer doesn't work in your particular case can only mean that you're
     * actually not developing a module JAR file, but just a part integral to your own web application. In
     * that case, the ServletContainerInitializer is useless for you and you should be using ServletContextListener instead.
     *
     * */



    /**
     * Spring WebApplicationInitializer provides a programatic way to configure the
     * Spring DispatcherServlet and ContextLoaderListener in Servlet 3.0+ compliant
     * servlet containers, rather than adding this configuration through a web.xml file.
     * */
    WebApplicationInitializer niog340;
        SpringBootServletInitializer noi3480384g;
        AbstractContextLoaderInitializer n03n4g083490n;
            AbstractDispatcherServletInitializer n034ng03409g;
                AbstractAnnotationConfigDispatcherServletInitializer aaa0394g0934g;
        JerseyAutoConfiguration.JerseyWebApplicationInitializer gbgbg098hg34g34;
        MyWebApplicationInitializer f3gi3ng3o4igno3in4ogi3o4gio;    //  No Recognized
        MyWebApplicationInitializer2 f3gg3498j9g834984ogi3o4gio;    //  No Recognized



    /**
     * WebApplicationInitializer 依赖 ServletContainerInitializer，到 SvltCntx！
     * ServletContextInitializer，到 SvltCntx！
     * */



    /**
     * ServletContextInitializer 与 WebApplicationInitializer 有什么关系？
     *
     * 应该拿 ServletContextInitializer 与 WebApplicationInitializer 对比，二者的成员方法的参数都是 ServletContext；
     * 类 SpringServletContainerInitializer 会 自动识别 到 WebApplicationInitializer，但 不会 自动识别 ServletContextInitializer。
     *
     * ServletContextInitializer 和 WebApplicationInitializer 功效是相同的，估计仅仅是用法不同；
     * 主要区别是，WebApplicationInitializer 给 Servlet Container 使用，而 ServletContextInitializer 给 Spring 使用。
     * */

    EmbeddedServletContainerAutoConfiguration g43g34;

    ServletContextInitializer g30g90394g09;
        RegistrationBean g3gergp;
            ServletRegistrationBean g340j3094g;
            ServletListenerRegistrationBean g34gj03j4g09;
        //  AbstractFilterRegistrationBean rr34g03j4g09j394g;
                FilterRegistrationBean g34gj0394g034gk09;
                DelegatingFilterProxyRegistrationBean g0349gj3094g09;



    /**
     * WebApplicationInitializer is used by a Servlet Container at startup of the web application
     * and provides a way for programmatic creating a web application(replacement for a web.xml file),
     * （可以说在 MVC 之前）wrong !
     * ApplicationContextInitializer provides a hook to configure the Spring application context
     * before it gets fully created
     * 【ApplicationContextInitializer 主要作用就是在 ConfigurableApplicationContext 类型(或者子类型)的
     * ApplicationContext 做 refresh 之前，允许我们对 ConfiurableApplicationContext 的实例做进一步的设置和处理】
     * （可以说在 AC 过程中）wrong !
     * */



    //  ############################ AutoConfiguration 2 EmbeddedServletContainerAutoConfiguration

    /**
     * 还可以对比 ServletContainerInitializer 和 WebApplicationInitializer ？
     * 根和茎茎的关系
     *
     * 还可以对比 ServletContainerInitializer 和 ServletContextInitializer ？
     * 根和茎茎的关系
     *
     * 还可以对比 ServletContextInitializer 和 WebApplicationInitializer ？
     * 功效相同，用法不同
     *
     * 还可以对比 ApplicationContextInitializer 和 ServletContextInitializer ？
     * 有没有关系？
     * */


    /*
        WebApplicationInitializer 与 ContextLoaderListener 的关系：
        WebApplicationInitializer 通过 某种途径 搞 ContextLoaderListener !
    */

}
