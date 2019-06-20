package com.spring.springboot;

import ch.qos.logback.classic.servlet.LogbackServletContainerInitializer;
import com.spring.springboot.appListener.*;
import com.spring.springboot.autocfg.HelloAutoConfiguration;
import com.spring.springboot.initializer.CustomServletContainerInitializer;
import com.spring.springboot.initializer.CustomServletContainerInitializer2;
import com.spring.springboot.initializer1.MyWebApplicationInitializer;
import com.spring.springboot.initializer1.MyWebApplicationInitializer2;
import com.spring.springboot.initializer2.MyApplicationContextInitializer1;
import com.spring.springboot.initializer2.MyApplicationContextInitializer2;
import com.spring.springboot.mvcConfigure.WebConfig;
import com.spring.springboot.mvcConfigure.WebConfig2;
import com.spring.springboot.saRunListener.MySprAppRunLsnr;
import com.spring.springboot.scListener.MyListener;
import com.spring.springboot.scListener.MyServletContextListener;
import org.apache.catalina.startup.WebappServiceLoader;
import org.apache.tomcat.websocket.server.WsContextListener;
import org.apache.tomcat.websocket.server.WsSci;
import org.springframework.aop.framework.AbstractAdvisingBeanPostProcessor;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.*;
import org.springframework.boot.actuate.autoconfigure.EndpointWebMvcManagementContextConfiguration;
import org.springframework.boot.actuate.endpoint.mvc.JolokiaMvcEndpoint;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.EntityManagerFactoryDependsOnPostProcessor;
import org.springframework.boot.autoconfigure.data.mongo.MongoClientDependsOnBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerTemplateAvailabilityProvider;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.logging.AutoConfigurationReportLoggingInitializer;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.autoconfigure.websocket.JettyWebSocketContainerCustomizer;
import org.springframework.boot.autoconfigure.websocket.TomcatWebSocketContainerCustomizer;
import org.springframework.boot.autoconfigure.websocket.UndertowWebSocketContainerCustomizer;
import org.springframework.boot.autoconfigure.websocket.WebSocketContainerCustomizer;
import org.springframework.boot.builder.ParentContextCloserApplicationListener;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer;
import org.springframework.boot.context.ContextIdApplicationContextInitializer;
import org.springframework.boot.context.FileEncodingApplicationListener;
import org.springframework.boot.context.config.AnsiOutputApplicationListener;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.config.DelegatingApplicationContextInitializer;
import org.springframework.boot.context.config.DelegatingApplicationListener;
import org.springframework.boot.context.embedded.*;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.context.event.*;
import org.springframework.boot.context.properties.ConfigurationBeanFactoryMetaData;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener;
import org.springframework.boot.logging.ClasspathLoggingApplicationListener;
import org.springframework.boot.logging.LoggingApplicationListener;
import org.springframework.boot.web.servlet.*;
import org.springframework.boot.web.support.ServletContextApplicationContextInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.event.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.BeanValidationPostProcessor;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.*;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.context.support.ServletContextAwareProcessor;
import org.springframework.web.context.support.StandardServletEnvironment;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
import org.springframework.web.util.IntrospectorCleanupListener;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

/**
 * Spring Boot 应用启动类
 * <p>
 * Created by franklin on 17/4/26.
 */
// Spring Boot 应用的标识
//@SpringBootApplication
@Configuration
@ComponentScan
//@EnableAutoConfiguration
@EnableAutoConfiguration(exclude = {/*Tomcat.class, */DataSourceAutoConfiguration.class})
//        {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class}
public class Application {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
//        SpringApplication.run(Application.class, args);
        SpringApplication app = new SpringApplication(Application.class);

        app.addListeners(
                new MyApplicationStartingListener(),
                new MyApplicationFailListener(),
                new MyApplicationReadyListener(),
                new MyApplicationPreparedListener(),
                new MyEnvReadyListener(),
                new MyContextClosedListener(),
                new MyContextRefreshedListener(),
                new MyContextStartedListener(),
                new MyContextStoppedListener());

        app.addInitializers(
                new MyApplicationContextInitializer1(),
                new MyApplicationContextInitializer2());

        app.run(args);
    }




    public void fwefwef() {
        ApplicationRunner apprnnr;

        EnableScheduling we3r;
        EnableCaching werwer;
        EnableAutoConfiguration feef242342343ef;
        EnableMBeanExport egwegwegweg;
        EnableScheduling gewrgwegweg;
        EnableWebMvc fwefwefwef133123123123;

        SpringFactoriesLoader wer2323r23r;

        //  1
        ApplicationListener gergergerg;
            ParentContextCloserApplicationListener ewf;
            FileEncodingApplicationListener fwefwe;
            AnsiOutputApplicationListener fwef;
            ConfigFileApplicationListener fwefewf;
            DelegatingApplicationListener ddddergerd;
            LiquibaseServiceLocatorApplicationListener gwgwegweg;
            ClasspathLoggingApplicationListener fwefwewefwef;
            LoggingApplicationListener fwefweflwefl;
            ParentContextCloserApplicationListener grgergergerg;
            FileEncodingApplicationListener wgwegweg;
            DelegatingApplicationListener wefwe23423424fwef;
            BackgroundPreinitializer gwegwegweg;

        //  2

//        SpringApplicationRunListeners springAppRunLsnrs;
        SpringApplicationRunListener springAppRunLsnr;
            EventPublishingRunListener ergergerg;
                ApplicationEvent we135rwer;
                SpringApplicationEvent erger34234;
                ApplicationEnvironmentPreparedEvent ewrwer;
                ApplicationPreparedEvent fwefw221ef;
                ApplicationReadyEvent feefw2ef;
                ApplicationFailedEvent efwefeeeeew;
                ApplicationStartingEvent wefwefwef222333;
                ApplicationStartedEvent fewiioweirf;
                ApplicationContextEvent erfer324;
                ContextClosedEvent fwefwef234234;
                ContextRefreshedEvent owefionwef;
                ContextStoppedEvent voiwne;
                ContextStartedEvent ovwieobwefe;
                ApplicationEventMulticaster gergerg;
                AbstractApplicationEventMulticaster ewwefwef;
                SimpleApplicationEventMulticaster sdgweg;

        Profile wefwef;
        ConditionalOnBean gwgweg;
        ConfigurationPropertiesAutoConfiguration gfergerg;
        Condition regerg;
        Conditional fwefwewef;
        ConditionalOnWebApplication fwhjfgjfgjefwef;
    }

        //  3
        DispatcherServlet dispSvlt;

        //  657567
        JmxAutoConfiguration gegerg;

        //  ############################ Loader
        ServiceLoader svcLdr;
        ClassLoader clsLdr;
        //  4
        SpringBootConfiguration fwefwe5367537357;

        //  5
//        AutoConfigureWebMvc sfsdff;

        //  5
        ApplicationContextInitializer gergerg34g34g34;
            ConfigurationWarningsApplicationContextInitializer wevwevwev;
            ContextIdApplicationContextInitializer gwegweg;
            DelegatingApplicationContextInitializer grgrgrg;
            ServerPortInfoApplicationContextInitializer fwefepfpe;

        //  6
        AutoConfigurationImportFilter gwefwef;
        AutoConfigurationImportListener fwefwe3513451435;

        //  6
        ComponentScan fwefwefwe3214134f;
        ComponentScans fwefwefwefw451345351e;

        //  7
        DispatcherServlet ds;

        EndpointWebMvcManagementContextConfiguration erf2309230f923f;

        FreeMarkerTemplateAvailabilityProvider gergergerg93148951485915;

        BeanPostProcessor gregeg013409014;

        ConfigurationProperties tohijioqehr;
        EnableConfigurationProperties eroignoinerng;
        EnableScheduling fwef01092;

        HttpMessageConverter wewwe;
        ServletRegistrationBean wqqwq232323;

        EnvironmentAware wefwef;
        Environment wefwef23f23;

        ServerProperties rweoiwnoeb;

        ServletContextListener scl;
        EmbeddedServletContainerInitializedEvent aa232323;

        ServerEndpoint se;

        ServletContext sc;
        org.apache.catalina.servlet4preview.ServletContext g34g304ijg09340g9;

        ImportBeanDefinitionRegistrar g34g98j9834g98h98h9;

        EnableAutoConfiguration aaa2323232112;
        HelloAutoConfiguration aaaa23223;

        TransactionManagerCustomizers aaa232323;

        /**
         * 极其重要！
         * 注册Servlet、Filter、Listener的方法，两/四种：
         *
         * 1， （war）注册到 WebApplicationInitializer 的实现类中：
         *      servletContext.addListener()
         *      servletContext.addFilter()
         *      servletContext.addServlet()
         * 2， （jar）注册到任意一个 @Configuration 配置类中：
         *      ServletRegistrationBean
         *      FilterRegistrationBean
         *      ServletListenerRegistrationBean
         * 3，  使用注解：
         *      WebServlet
         *      WebListener
         *      WebFilter
         * 4,   xml方式:<listener/>, <servlet/>, <filter/>
         * */

        /**
         * （某个）WebApplicationInitializer 与 web.xml 重叠部分
         *
         <servlet>
           <servlet-name>dispatcher</servlet-name>
           <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
                    </servlet-class>
           <init-param>
             <param-name>contextConfigLocation</param-name>
             <param-value>/WEB-INF/spring/dispatcher-config.xml</param-value>
           </init-param>
           <load-on-startup>1</load-on-startup>
         </servlet>

         <servlet-mapping>
           <servlet-name>dispatcher</servlet-name>
           <url-pattern>/</url-pattern>
         </servlet-mapping>

         public class MyWebAppInitializer implements WebApplicationInitializer {
            @Override
            public void onStartup(ServletContext container) {
                XmlWebApplicationContext appContext = new XmlWebApplicationContext();
                appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");

                ServletRegistration.Dynamic dispatcher =
                    container.addServlet("dispatcher", new DispatcherServlet(appContext));
                dispatcher.setLoadOnStartup(1);
                dispatcher.addMapping("/");
            }
         }
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
         * TODO TODO TODO TODO TODO ???
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
         * TODO 搞搞清楚
         * 有些 Listener 监控 Servlet 容器 的生命(Tomcat内部)；
         * 有些 Listener 监控 [Web]ApplicationContext 的生命 --- ServletContextListener；// TODO ？？？
         * 有些 Listener 监控 Application 的生命 --- ApplicationListener;
         *
         * 有些 Initializer 初始化 ServletContainer --- ServletContainerInitializer(SC + WebApplicationInitializer[])；
         * 有些 Initializer 初始化 ServletContext and WebApplication --- WebApplicationInitializer(SC);
         * 有些 Initializer 初始化 ServletContext[or NOT WebApplication] --- ServletContextInitializer(SC)；
         * 有些 Initializer 初始化 [Web]ApplicationContext --- ApplicationContextInitializer(AC)；
         * 
         * ServletContextInitializer 和 WebApplicationInitializer 效果相同，用法不同；都是以 SC 为参数，通过各种方式，构造 SC 的属性：两个上下文，以及 Filters 和 Listeners;
         * 而 ROOT-AC 是指 根据配置 加载 全部的 与MVC 无关的 Bean，
         * Servlet-AC 是指 根据配置 加载 与MVC 有关的 Bean。
         *
         * WebApplicationInitializer VS ApplicationContextInitializer （完全不能相提并论）
         * So to conclude, except for the Initializer suffix, both WebApplicationInitializer and ApplicationContextInitializer serve fairly
         * different purposes. Whereas the WebApplicationInitializer is used by a Servlet Container at startup of the web application and 
         * provides a way for programmatic creating a web application(replacement for a web.xml file), ApplicationContextInitializer provides
         * a hook to configure the Spring application context before it gets fully created.
         *
         * 在 ROOT容器创建 与 Servlet容器创建 之间，还会创建监听器、过滤器等,
         * 完整的加载/创建顺序是这样：ServletContext - context-param - listener - filter - servlet
         *
         * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
         * 上面的顺序，对应很正确
         *
         * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
         * Important !
         * SpringBoot 中，默认的 WebApplicationInitializer 似乎并不会被执行；
         * 可以使用 ServletContextInitializer 和 SpringBootServletInitializer 代替，
         * 再 配合使用 FilterRegistrationBean, ServletRegistrationBean 和 ServletListenerRegistrationBean。
         *  |
         *  ---> BullShit !
         * */


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

        /**
         * Listener 注册的三/四种方式：
         * web.xml 里的 <listener/>
         * 注解 @WebListener
         * 方法 SC.addListener
         * Bean方式 ServletListenerRegistrationBean
         */

        //  ############################ Servlet Listener
        ServletContextListener oieoivoir34g34g;
            ContextLoaderListener vr3robinoi3bnio;
            WebAppRootListener j0923jf02093;
            ContextCleanupListener fff34ogin3oigo43i;
            WsContextListener aaa3gpompopo;
            IntrospectorCleanupListener fgogoi34goi;
            MyServletContextListener gg34g09j093jg904;  // Recognized
            MyListener jf293f982j3f9823;    // Recognized

        ServletContextEvent g34oinoi3n4ogi3no4ig;
            ServletContextAttributeEvent g3goim3oin4go3in4goin6;

        /**
         * TODO rubbish!
         *
         * 接口 ServletContextListener 可以实现，做一些 定制性 的事情；
         * 类 ContextLoaderListener 已经实现了 接口 ServletContextListener，
         * 借助 "工具类" ContextLoader 完成了一些事情了，算是一个输出性的东西，不容置喙。
         *
         * 接口 ContextLoaderListener 绝对不是“输出性的东西”，而且非常重要！
         * 监控了 [Root] WebApplicationContext 从无到有再到无 过程中的 重要节点！
         * 在此之前 一直在收集配置；再此之后，开始使用配置（初始化 WebApplicationContext），形成具有完整层级和正确功能的 AC！
         * 这个过程由 "某个事件" 触发，再由接口 ServletContextListener 的实现类 ContextLoaderListener 的某个成员方法 完成 ！
         *
         * 不容置喙个屁，ServletContextListener 是 SpringMVC 框架的开端，在 web.xml 配置文件中，由容器触发“开始”事件(ServletContext)，
         * 而 ServletContextListener 响应事件：读取 Root上下文 的配置文件，开始搞 Root上下文。
         */

        /**
         * SpringBoot 启动中，会查找 ApplicationContextInitializer 的子类，
         * 调起 其中的 各个 方法initialize。
         * */
        ApplicationContextInitializer verver34gj03g093j940;
            ServletContextApplicationContextInitializer g3oi3oi4gnoi;
            DelegatingApplicationContextInitializer g3o4g039g409;
            ServerPortInfoApplicationContextInitializer g34og34g09340g9;
            ContextIdApplicationContextInitializer g34ing034g093j4gj9;
            ConfigurationWarningsApplicationContextInitializer g34n0934g09j0;
            AutoConfigurationReportLoggingInitializer g34oin304g09340g9;
            MyApplicationContextInitializer1 init34f23f09j091;  //  Recognized
            MyApplicationContextInitializer2 init34f23f09j092;  //  Recognized


    //  ############################ PostProcessor

        BeanPostProcessor wepnip23p0g092;
            ServletContextAwareProcessor g34onoi3n4ogi34;
                WebApplicationContextServletContextAwareProcessor g3gon3oi4g;
            AbstractAdvisingBeanPostProcessor g34gpm03m094g;
                AbstractBeanFactoryAwareAdvisingPostProcessor g3009304g9;
                    AsyncAnnotationBeanPostProcessor g30j0394jg0934;
            BeanValidationPostProcessor g34ogn0934g;
            InstantiationAwareBeanPostProcessor g340j09g3409g34g;
                CommonAnnotationBeanPostProcessor gerg34g0934g09;
            EmbeddedServletContainerCustomizerBeanPostProcessor g34j093j4g093j4g0934g;
        //  ApplicationContextAwareProcessor
        EmbeddedServletContainerAutoConfiguration.BeanPostProcessorsRegistrar o34ig0m0384g;
        BeanFactoryPostProcessor g3g0934g09304;
        //  ServletComponentRegisteringPostProcessor aaa3og4in3ogino34;
            BeanDefinitionRegistryPostProcessor ff39h0394jg0934;
                ConfigurationClassPostProcessor g340g3n09gn0934jg034;
            ConfigurationBeanFactoryMetaData h0394g0934;
            PropertyResourceConfigurer a0g390g93j04;
                PlaceholderConfigurerSupport g3o4gin3oigo3i4;
                    PropertySourcesPlaceholderConfigurer aa3goi3nogi34;
            AbstractDependsOnBeanFactoryPostProcessor oi34ngo3n4oig3io4;
                EntityManagerFactoryDependsOnPostProcessor jg093j409g34;
                MongoClientDependsOnBeanFactoryPostProcessor g3gj09j039g4;


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
        /*
            ## springboot
            # Application Listeners
            org.springframework.context.ApplicationListener=\
            org.springframework.boot.ClearCachesApplicationListener,\
            org.springframework.boot.builder.ParentContextCloserApplicationListener,\
            org.springframework.boot.context.FileEncodingApplicationListener,\
            org.springframework.boot.context.config.AnsiOutputApplicationListener,\
            org.springframework.boot.context.config.ConfigFileApplicationListener,\
            org.springframework.boot.context.config.DelegatingApplicationListener,\
            org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener,\
            org.springframework.boot.logging.ClasspathLoggingApplicationListener,\
            org.springframework.boot.logging.LoggingApplicationListener

            ## springboot-autoconfigure
            # Application Listeners
            org.springframework.context.ApplicationListener=\
            org.springframework.boot.autoconfigure.BackgroundPreinitializer
        */

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

        //  ############################ Servlet Configure

        ConfigurableEnvironment cfgenv;
            AbstractEnvironment afefeee;
            ConfigurableWebEnvironment rgrgrgrg;
                StandardServletEnvironment erf9023jf0923;

        //  ############################ Servlet Configure

        ContextLoader gregerg34gj093j409g;
        ServiceLoader oinepirg0394jg03j94;
        ClassLoader grg0934g0j3094g09;

        /*
        * https://www.youtube.com/watch?v=uDl1qlJWE7A
        * https://www.youtube.com/watch?v=viP3VCx1X6w
        * */

        ImportBeanDefinitionRegistrar lknlknlk3434;
        ImportSelector g34g34;
        AutoConfigurationImportSelector greon3o4i;


        //  ############################ EnableXXX Anno

        EnableScheduling enSchd;
        EnableAspectJAutoProxy enAspJ;
        EnableAutoConfiguration enAuCfg;
        EnableWebMvc enWmvc;
        EnableCaching enCach;
        EnableLoadTimeWeaving enLdtmwv;
        EnableMBeanExport enMbn;
        EnableOAuth2Sso enOA;
        EnableConfigurationProperties enCfgPrp;

        EnableSpringDataWebSupport gergh0934jg0394jg09;


        /**
         * Enable 就是 "识别"功能 的开关；打开开关，就开始识别，关上开关，就不再识别。
         *
         * 比如， 使用了 注解@EnableScheduling，就开始满世界找 @Scheduled；如果没有使用，就忽略 @Scheduled。
         * 比如， 使用了 注解@EnableWebMvc，就开始满世界找 MVC 相关注解，比如各种 HandlerXXX，Controller，XXXAdapter之类。
         * 
         * 然后呢？@EnableAutoConfiguration 与 其他 @EnableXXX 有什么逻辑区别？
         * 似乎是一样的逻辑，使用了 注解@EnableAutoConfiguration，就开始满世界找 Auto-Configuration 的相关东西 --- 就是那些 Conditional + Configuration。
         * */

        //  ############################ EnableXXX Anno Customize

        /**
         * 使用 AutoConfiguration 有两种做法
         * 1， 把 @Configuration 的类，加入 *.factories，作为 key EnableAutoConfiguration 的 value；
         *     这样的话，EnableAutoConfiguration 的 Importer 会 以 EnableAutoConfiguration 为类型 load 这个 类。
         * 2， 创建一个 注解 @EnableXXX；其定义本身 具有 注解 Import，把 @Configuration 的类 作为参数。
         *
         *
         * 接口 ImportSelector 声明了 一个方法 selectImports；
         *
         * 注解 Import 可以接受 三种类型的参数（可以被加载的类型）：
         *  ImportSelector 实现类 （ @EnableAutoConfiguration）
         *  Configuration 注解 （其他 @EnableXXX）
         *  接口XXXX 实现类
         *  ImportBeanDefinitionRegistrar 实现类 （其他。。。）
         * TODO 这里忘了，再研究研究
         * 
         * */

        // TODO ApplicationListener 和 SpringApplicationRunListener 的处理顺序？

        EmbeddedServletContainerCustomizer wef029j3f02j30f9;
            ServerProperties g34g03j0934g;
            WebSocketContainerCustomizer gj0349gj034jg0934;
                TomcatWebSocketContainerCustomizer gg3409gj093;
                UndertowWebSocketContainerCustomizer gj0394jg0394jg;
                JettyWebSocketContainerCustomizer fg0943jg0394g;


        EmbeddedServletContainerFactory wewewe23232;
            ConfigurableEmbeddedServletContainer conownow;
                AbstractConfigurableEmbeddedServletContainer fwf23f23;
                    AbstractEmbeddedServletContainerFactory wewe2322323;
                        TomcatEmbeddedServletContainerFactory tomcatfs;
                        UndertowEmbeddedServletContainerFactory undertowfs;
                        JettyEmbeddedServletContainerFactory jettyfs;

        EmbeddedServletContainer cont;
            UndertowEmbeddedServletContainer wewe23f23f;
            TomcatEmbeddedServletContainer weiuifu23;
            JettyEmbeddedServletContainer gooi43gnoi2323;


        // TODO 以及 各种 其他 Customizer

        ImportAutoConfiguration gj340gj3049jg0394jg9;

        Registration reg;
        RegistrationBean regBn;
        //  TODO 什么区别？



        DispatcherServletAutoConfiguration grgrrgrg2u39r0293ff;

        /***
         * Spring Boot Uses DispatcherServletAutoConfiguration to initialize a default DispatcherServlet.
         * So you need to customize the Default Dispatcher Servlet in the following way :
         */
        SpringApplicationBuilder grjigo3j40gj3094g;


        CommandLineRunner f4g34g34g9034g93g4g3;
        ApplicationRunner f34g09j04g9j3094jg09;
        ConfigFileApplicationListener fff340gj309jg0934jg;


}


/**
 * Using Spring Mvc WebApplicationInitializer, ApplicationContextInitializer and ContextLoaderListener ?
 * */

class SpringMvcExampleWebApplicationInitializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        registerDispatcherServlet(servletContext);
        registerHiddenHttpMethodFilter(servletContext);
    }

    private void registerDispatcherServlet(final ServletContext servletContext) {
        WebApplicationContext dispatcherContext = createContext(WebMvcContextConfiguration.class, InfrastructureContextConfiguration.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
        dispatcherServlet.setContextInitializers(new SpringMvcExampleProfilesInitializer());
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private WebApplicationContext createContext(final Class<?>... annotatedClasses) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(annotatedClasses);
        return context;
    }

    private void registerHiddenHttpMethodFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic registration = servletContext.addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
        registration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD),
                false, DISPATCHER_SERVLET_NAME);
    }
}

class SpringMvcExampleProfilesInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext ctx) {
        ConfigurableEnvironment environment = ctx.getEnvironment();
        List<String> profiles = new ArrayList<String>(getProfiles());
        if( profiles == null || profiles.isEmpty() )
        {
            throw new IllegalArgumentException("Profiles have not been configured");
        }
        environment.setActiveProfiles(profiles.toArray( new String[0]));
    }

    //TODO add logic
    private Collection<String> getProfiles() {
//        return Lists.newArrayList("file_based", "test_data");
        return Arrays.asList("file_based", "test_data");
    }
}


class WebMvcContextConfiguration{

}

@Configuration
@ComponentScan(basePackages = {"com.savdev.springmvcexample.repository", "com.savdev.springmvcexample.config"})
class InfrastructureContextConfiguration {

}

/*@Configuration
@ComponentScan(basePackages = {"com.savdev.springmvcexample.repository", "com.savdev.springmvcexample.config"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.savdev.springmvcexample.repository"})
public class InfrastructureContextConfiguration2 {

    @Configuration
    @Profile(value = "file_based")
    @PropertySource("classpath:/db/config/file_based.properties")
    public static class FileBasedConfiguration {

        @Inject
        private Environment environment;

        @Bean
        public DataSource dataSource() {
            BasicDataSource dataSource = new org.apache.commons.dbcp.BasicDataSource();
            dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
            dataSource.setUrl(environment.getProperty("jdbc.url"));
            dataSource.setUsername(environment.getProperty("jdbc.username"));
            dataSource.setPassword(environment.getProperty("jdbc.password"));
            return dataSource;
        }
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:/db/liquibase/changelog/db.changelog-master.xml");
        liquibase.setDropFirst(true);
        return liquibase;
    }
}*/

class SpringMvcExampleWebApplicationInitializer2 implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    private static final Class<?>[] configurationClasses = new Class<?>[]{
            WebMvcContextConfiguration.class, InfrastructureContextConfiguration.class};


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        registerListener(servletContext);
        registerDispatcherServlet(servletContext);
        registerHiddenHttpMethodFilter(servletContext);
        registerSpringSecurityFilterChain(servletContext);
    }

    private void registerSpringSecurityFilterChain(ServletContext servletContext) {
        FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter(
//                BeanIds.SPRING_SECURITY_FILTER_CHAIN,
                "frankie yang",
                new DelegatingFilterProxy());
        springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
    }

    private void registerDispatcherServlet(final ServletContext servletContext) {
        WebApplicationContext dispatcherContext = createContext(WebMvcContextConfiguration.class, InfrastructureContextConfiguration.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
        dispatcherServlet.setContextInitializers(new SpringMvcExampleProfilesInitializer());
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private WebApplicationContext createContext(final Class<?>... annotatedClasses) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(annotatedClasses);
//        context.refresh();
        return context;
    }

    private void registerListener(ServletContext servletContext) {
        WebApplicationContext rootContext = createContext(configurationClasses);
        servletContext.addListener(new ContextLoaderListener(rootContext));
//        servletContext.addListener(new RequestContextListener());
    }

    private void registerHiddenHttpMethodFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic registration = servletContext.addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
        registration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD),
                false, DISPATCHER_SERVLET_NAME);
    }
}

