package com.spring.springboot;

import com.spring.springboot.initializer2.MyApplicationContextInitializer1;
import com.spring.springboot.initializer2.MyApplicationContextInitializer2;
import org.springframework.boot.autoconfigure.logging.AutoConfigurationReportLoggingInitializer;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer;
import org.springframework.boot.context.ContextIdApplicationContextInitializer;
import org.springframework.boot.context.config.DelegatingApplicationContextInitializer;
import org.springframework.boot.context.embedded.ServerPortInfoApplicationContextInitializer;
import org.springframework.boot.web.support.ServletContextApplicationContextInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.ConfigurableWebEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;

/**
 * @author Frankie Yang on 2019-06-20.
 */
public class EverySpringBootProcedure {



    //  ############################ AutoConfiguration 3

    /*
     * spring.factories 文件有很多个，而且其中不止有 Auto-Configure 一种，
     * 还有 其他十多种，是否 所有的这些，都是通过 EnableAutoConfiguration 拉入？
     * 具体拉入逻辑需要研究研究，搞搞清楚。
     *
     *
     * spring.factories 文件的类型：
     *
     * （默认情况下，springboot 启动时，SpringFactoriesLoader 拉入的类型的顺序如下，某几个多次出现）
     * ApplicationContextInitializer
     * ApplicationListener
     * SpringApplicationRunListener
     * EnvironmentPostProcessor
     * PropertySourceLoader
     * BeanInfoFactory
     * FailureAnalyzer
     * EnableAutoConfiguration
     * AutoConfigurationImportFilter
     * AutoConfigurationImportListener
     * TemplateAvailabilityProvider
     * ManagementContextConfiguration
     *
     * FailureAnalysisReporter
     * SpringDataJacksonModules
     * RepositoryFactorySupport
     * TestExecutionListener
     * ContextCustomizerFactory
     *
     * 应该是这样，spring-boot 启动，有一个繁复的过程；
     * 在这个过程中，有很多个阶段，不同的阶段，会需要不同的"配置"。
     *
     * 比如 ApplicationContextInitializer 相关阶段，需要 从 spring.factories 获取 "类型"ApplicationContextInitializer 的配置；
     * 而 ApplicationListener 相关阶段，需要 从 spring.factories 获取 "类型"ApplicationListener 的配置。
     * 而 WebMvc 相关阶段，需要 从 spring.factories 获取 “类型”WebMvcAutoConfiguration 的配置（可以使用 @EnableWebMvc 修改定制）。
     * 然后 xxxAutoConfiguration 相关阶段，需要 从 spring.factories 获取 "类型"xxxAutoConfiguration 的配置。
     *
     * 大概情况应该就是这样的逻辑。
     *
     * 最重要的东西 就是 类 SpringFactoriesLoader，接受 类型 作为 参数key，获取对应类型的 配置。
     * */


    /**
     *
     * Spring Boot Initialization Steps:
     1, SpringApplication.run() creates EmbeddedWebApplicationContext application context;
     2, Calls its refresh() method;
     3, Refresh process reads annotations of the starting class TestSpring. It looks for import annotations.
     EnableAutoConfiguration is one of them. For an import annotation the refresh process gets the corresponding
     class from the annotation value and invokes its selectImports() method;
     4, In case of @EnableAutoConfiguration the corresponding class is EnableAutoConfigurationImportSelector
     whose selectImports() loads tons of other import selectors from the META-INF/spring.factories;
     5, This process continues recursively. Also, all bean definitions, that are inside these import selectors,
     are read. I.e. it includes beans defined by a method with the @Bean annotation, i.e. beans that require
     the Spring context to call the corresponding method automatically to instantiate them;
     6, The refresh() continues and reaches onRefresh(), the createEmbeddedServletContainer() method is called inside;
     7, Among read bean defitions at the previous step, beans implementing ServletContextInitializer are
     searched for and instantiated. One of them is the bean, defined by the
     DispatcherServletAutoConfiguration.DispatcherServletRegistrationConfiguration#dispatcherServletRegistration()
     method of ServletRegistrationBean type that extends ServletContextInitializer. As you can guess from the
     name of the class, such initializers add a given servlet (in this case DispatcherServlet) to a given
     ServletContext, when their onStartup() method is invoked;
     8, A tomcat embedded server is created (not started completely yet). All found ServletContextInitializers
     at the previous step are passed to this tomcat initialization - this is where the onStartup() methods of
     those ServletContextInitializers are called and DispatcherServlet gets created and registered as servlet;
     9, End of onRefresh() of application context;
     10,The finishRefresh() is called where tomcat is finally started by TomcatEmbeddedServletContainer.start();
     11,End of refresh() of application context and other final initialization steps;
     12,The app is running.

     * import annotations 是很重要的概念，是哪些呢？
     * 使用了 @Import(<? extends ImportSelector>.class) 的注解
     * */


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



    //  ############################  Configure Environment

    ConfigurableEnvironment cfgenv;
        AbstractEnvironment afefeee;
        ConfigurableWebEnvironment rgrgrgrg;
            StandardServletEnvironment erf9023jf0923;




}
