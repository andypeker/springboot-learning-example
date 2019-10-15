package com.spring.springboot;

import com.spring.springboot.mvcConfigure.WebConfig;
import com.spring.springboot.mvcConfigure.WebConfig2;
import org.springframework.boot.actuate.endpoint.mvc.JolokiaMvcEndpoint;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrations;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.context.ConfigurableWebEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

/**
 * @author Frankie Yang on 2019-06-20.
 */
public class EveryMvcConfigure {


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


    /**
     * Listener 注册的三/四种方式：
     * web.xml 里的 <listener/>
     * 注解 @WebListener
     * 方法 SC.addListener
     * Bean方式 ServletListenerRegistrationBean
     */


    //  ############################ AutoConfiguration 1 WebMvc

    EnableWebMvc afef2232323;
    WebMvcAutoConfiguration fwefweg34ginoi3n4g;
    WebMvcConfigurer rbqebrqe43g;
        WebMvcConfigurerAdapter ewgtwrth3223;
            WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter g34g4hnoi42nho24ohi;
            JolokiaMvcEndpoint gj9034jg0394jg09;
            WebConfig g304gj093j4g;
            WebConfig2 g9384g9834hg98h;
    WebMvcConfigurationSupport vv2390923confi;
        DelegatingWebMvcConfiguration greogioeirg;
//      WebMvcConfigurerComposite fff340gj3904gj9;

    /***
     * 一个应用应该只有一个
     * 继承自 DelegatingWebMVCConfiguration 的配置
     * 或 一个 @EnableWebMvc class，
     * 因为它们会注册相同的底层beans。
     */

    WebMvcAutoConfiguration.EnableWebMvcConfiguration rogioerigerg;
    ServletComponentScan fwefwef2443634613;
    WebServlet df;
    WebListener egerg;
    WebFilter fwef23;
    WebInitParam eropowe;


    //  ############################################# 原理相似
    EnableWebMvc ff32g34g;
    WebMvcAutoConfiguration f304g9j309j4g0934g;
//    EnableSpringDataWebSupport foweio23;
    SpringDataWebAutoConfiguration g30gj3409gj0394g;
    WebMvcProperties www;
    WebMvcConfigurer aa22323;
    WebMvcRegistrations eger4334;

    /**
     * Important !
     * To customize the configuration imported by @EnableWebMvc, we should extend
     * the class WebMvcConfigurerAdapter and override the methods we want to do
     * related customization with. Our extended WebMvcConfigurerAdapter methods are
     * called back from WebMvcConfigurationSupport during configuration stage.
     *
     * [Wrong!]WebMvcConfigurer 原理：通过 WebMvcAutoConfiguration 中的 DelegatingWebMvcConfiguration 的子类 体现作用。
     *
     * 注解 EnableWebMvc 会 导入 DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport；
     * autocfg 会 导入 WebMvcAutoConfiguration，而 这个配置 依赖 WebMvcConfigurer 并且 排斥 WebMvcConfigurationSupport；
     * 所以 如果有 @EnableWebMvc（或者），则使用 WebMvcConfigurationSupport，并且 WebMvcAutoConfiguration 不生效；
     * 如果 需要修改配置，则 必须 实现 WebMvcConfigurer (extends WebMvcConfigurerAdapter) 并且 有 @EnableWebMvc。
     * 如果 没有 WebMvcConfigurer，则 使用 WebMvcConfigurationSupport；
     * 另外，配置 WebMvcAutoConfiguration 内部有逻辑(就是 DelegatingWebMvcConfiguration)，可能会 导入 WebMvcConfigurer（情况4 原理）
     * TODO 情况3 的原理，对情况3的理解需要说明，没有覆盖并不是完全不起作用，而是有所补充；补充部分，就是 WebMvcConfigurer 的实现。
     *
     * extends WebMvcConfigurer，在扩展的类中重写父类的方法即可，
     *      这种方式依旧使用springboot的@EnableAutoConfiguration中的设置
     * @EnableWebMvc + extends WebMvcConfigurer，在扩展的类中重写父类的方法即可，
     *      这种方式会屏蔽springboot的@EnableAutoConfiguration中的设置
     * extends WebMvcConfigurationSupport，在扩展的类中重写父类的方法即可，
     *      这种方式会屏蔽springboot的@EnableAutoConfiguration中的设置
     *
     * Spring Boot Referenc 原文（情况3）:
     * If you want to keep Spring Boot MVC features and you want to add additional MVC configuration
     * (interceptors, formatters, view controllers, and other features), you can add your own @Configuration
     * class of type WebMvcConfigurer but without @EnableWebMvc. If you wish to provide custom instances of
     * RequestMappingHandlerMapping, RequestMappingHandlerAdapter, or ExceptionHandlerExceptionResolver, you
     * can declare a WebMvcRegistrationsAdapter instance to provide such components.
     * TODO WebMvcRegistrationsAdapter 是什么东西？
     * */

    /**
     * 1. 无论是使用 @EnableWebMvc 还是 WebMvcConfigurationSupport，都会禁止 Spring boot 的自动装配，只有使用 WebMvcConfigurer 才不会；
     * 2. 虽然禁止了 Spring boot 的自动装配，但是 WebMvcConfigurationSupport 本身，还是会注册一系列的 MVC 相关的bean 的，从附加的api 可以看到；
     * 3. WebMvcAutoConfiguration 自动装备，其实会创建一个 WebMvcConfigurationSupport 的子类，叫 EnableWebMvcConfiguration
     */

    WebMvcRegistrationsAdapter g340g9j340g9;

    ServletListenerRegistrationBean onionoi34343;
    FilterRegistrationBean eroinoi23224;
    ServletRegistrationBean oeqrinboi45981450801;
    FilterRegistration ffdd34g34g34g;
    ServletRegistration g340gj09j34g0;



}
