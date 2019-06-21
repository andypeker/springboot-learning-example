package com.spring.springboot;

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
     * */

}
