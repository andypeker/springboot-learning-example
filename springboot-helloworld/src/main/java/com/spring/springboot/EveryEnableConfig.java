package com.spring.springboot;

import com.spring.springboot.autocfg.HelloAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableMBeanExport;
//import org.springframework.data.web.config.EnableSpringDataWebSupport;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Frankie Yang on 2019-06-21.
 */
public class EveryEnableConfig {


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
//    EnableSpringDataWebSupport gergh0934jg0394jg09;


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
     * 2， 创建一个 注解 @EnableXXX；其定义本身 具有 注解 Import，把 @Configuration 的类 作为参数。 [ 有例子吗？]
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


    //  start 自动配置注解 -- 自动配置类 -- properties自动配置 -- properties配置 -- 一气呵成
    EnableAutoConfiguration aaa2323232112;
    HelloAutoConfiguration aaaa23223;

    ConfigurationProperties tohijioqehr;
    EnableConfigurationProperties eroignoinerng;
    //  end

}
