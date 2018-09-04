package com.spring.springboot;

import ch.qos.logback.classic.servlet.LogbackServletContainerInitializer;
import com.spring.springboot.initializer.*;
import com.spring.springboot.appListener.*;
import com.spring.springboot.saRunListener.MySprAppRunLsnr;
import com.spring.springboot.scListener.MyListener;
import com.spring.springboot.scListener.MyServletContextListener;
import com.spring.springboot.service.HelloAutoConfiguration;
import org.apache.tomcat.websocket.server.WsContextListener;
import org.apache.tomcat.websocket.server.WsSci;
import org.springframework.aop.framework.AbstractAdvisingBeanPostProcessor;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.EntityManagerFactoryDependsOnPostProcessor;
import org.springframework.boot.autoconfigure.data.mongo.MongoClientDependsOnBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerTemplateAvailabilityProvider;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.logging.AutoConfigurationReportLoggingInitializer;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.builder.ParentContextCloserApplicationListener;
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
import org.springframework.boot.context.properties.ConfigurationBeanFactoryMetaData;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener;
import org.springframework.boot.logging.ClasspathLoggingApplicationListener;
import org.springframework.boot.logging.LoggingApplicationListener;
import org.springframework.boot.context.event.*;
import org.springframework.boot.web.servlet.*;
import org.springframework.boot.web.support.ServletContextApplicationContextInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.*;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.*;
import org.springframework.context.event.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.BeanValidationPostProcessor;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.ContextCleanupListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.context.support.ServletContextAwareProcessor;
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
import java.util.ServiceLoader;

/**
 * Spring Boot 应用启动类
 * <p>
 * Created by bysocket on 16/4/26.
 */
// Spring Boot 应用的标识
@SpringBootApplication
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

        ServletContextInitializer ewfwef;
        WebApplicationInitializer wefwefwef;
        ApplicationContextInitializer wefwefwefwef;

        SpringBootConfiguration wer;
        EnableAutoConfiguration wrwer23;

        EnableScheduling we3r;
        EnableCaching werwer;

        EnableWebMvc fwefwefwef;

        SpringFactoriesLoader wer2323r23r;

        SpringApplicationRunListener wewerl;
        ApplicationListener fwefwef;

        ParentContextCloserApplicationListener ewf;
        FileEncodingApplicationListener fwefwe;
        AnsiOutputApplicationListener fwef;
        ConfigFileApplicationListener fwefewf;
        DelegatingApplicationListener ddddergerd;
        LiquibaseServiceLocatorApplicationListener gwgwegweg;
        ClasspathLoggingApplicationListener fwefwewefwef;
        LoggingApplicationListener fwefweflwefl;


        //  1

        //  org.springframework.context
        ApplicationContextInitializer wefwefwwwef;
        AutoConfigurationReportLoggingInitializer vnbvg;
        ServerPortInfoApplicationContextInitializer fefwefewf;
        //  javax.servlet
        ServletContainerInitializer svltContIniter2;
        //  org.springframework.web
        WebApplicationInitializer webAppIniter;
        //  org.springframework.boot.web.servlet
        ServletContextInitializer svltCntxIniter;


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
        ApplicationListener lsnr;
//            rergerg ergerg;
//                rergerg ergerg;


        ConditionalOnBean gwgweg;

        ConfigurationPropertiesAutoConfiguration gfergerg;


        Profile wefwef;

        Condition regerg;
        Conditional fwefwewef;

    }
        //  3

        Conditional fwefwdedwe;
        ConditionalOnWebApplication fwhjfgjfgjefwef;

        DispatcherServlet dispSvlt;
        SpringFactoriesLoader spFacldr;

        //  657567

        JmxAutoConfiguration gegerg;

        ServiceLoader svcLdr;
        ClassLoader clsLdr;
        SpringFactoriesLoader spFacLdr;


        //  4

//        AutoConfigureWebMvc sfsdff;
        WebMvcAutoConfiguration wefeeewefwef;

        WebApplicationInitializer fwefw2123123e;
        ApplicationContextInitializer vervwr324234;

        MessageSourceAutoConfiguration fef92309020934234;

        ApplicationListener gergergerg;
        ParentContextCloserApplicationListener grgergergerg;
        FileEncodingApplicationListener wgwegweg;
        DelegatingApplicationListener wefwe23423424fwef;

        MessageSourceAutoConfiguration ge23454325rgergerg;

        WebMvcAutoConfiguration grgweg;

        /*

            # AutoConfigureWebMvc auto-configuration imports
            org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc=\
            org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
            org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration,\
            org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration,\
            org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\
            org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration,\
            org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\
            org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration,\
            org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration,\
            org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration

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

            # AutoConfigureWebClient auto-configuration imports
            org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient=\
            org.springframework.boot.test.autoconfigure.web.client.WebClientRestTemplateAutoConfiguration,\
            org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\
            org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration

        */

        ApplicationListener fw21111e;
        AutoConfigurationImportFilter gwrgwegweg;

        BackgroundPreinitializer gwegwegweg;

        SpringBootConfiguration fwefwe5367537357;

        ServletComponentScan fwefwef2443634613;
        ComponentScan fwefwefwe3214134f;
        ComponentScans fwefwefwefw451345351e;

        EnableAutoConfiguration feef242342343ef;
        EnableMBeanExport egwegwegweg;
        EnableScheduling gewrgwegweg;
        EnableWebMvc fwefwefwef133123123123;

        SpringApplicationAdminJmxAutoConfiguration fwe2323f;


//        AutoConfigureWebMvc fwef575466wef;
        MessageSourceAutoConfiguration gergerge456245645rg;

        WebMvcAutoConfiguration fewfwefwef;
        GroovyTemplateAutoConfiguration ggwegweg;

        ConfigurationWarningsApplicationContextInitializer wevwevwev;
        ContextIdApplicationContextInitializer gwegweg;
        DelegatingApplicationContextInitializer grgrgrg;
        ServerPortInfoApplicationContextInitializer fwefepfpe;

        CacheAutoConfiguration gerger56u356u356u;
        ApplicationContextInitializer gergerg34g34g34;

        /*

            # Application Context Initializers
            org.springframework.context.ApplicationContextInitializer=\
            org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer,\
            org.springframework.boot.context.ContextIdApplicationContextInitializer,\
            org.springframework.boot.context.config.DelegatingApplicationContextInitializer,\
            org.springframework.boot.context.embedded.ServerPortInfoApplicationContextInitializer

            # AutoConfigureCache auto-configuration imports
            org.springframework.boot.test.autoconfigure.core.AutoConfigureCache=\
            org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration

            # AutoConfigureDataJpa auto-configuration imports
            org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa=\
            org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\
            org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\
            org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\
            org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration

            # AutoConfigureDataMongo auto-configuration imports
            org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo=\
            org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
            org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration

            # AutoConfigureJdbc auto-configuration imports
            org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc=\
            org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\
            org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\
            org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration

            # Auto Configure
            org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
            org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
            org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
            org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
            org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
            org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
            org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
            org.springframework.boot.autoconfigure.cloud.CloudAutoConfiguration,\
            org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
            org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
            org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
            org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
            org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.ldap.LdapDataAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration,\
            org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration,\
            org.springframework.boot.autoconfigure.elasticsearch.jest.JestAutoConfiguration,\
            org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration,\
            org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\
            org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration,\
            org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration,\
            org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration,\
            org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration,\
            org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,\
            org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration,\
            org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration,\
            org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\
            org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration,\
            org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration,\
            org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration,\
            org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration,\
            org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration,\
            org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\
            org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration,\
            org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration,\
            org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration,\
            org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration,\
            org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration,\
            org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration,\
            org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\
            org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration,\
            org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration,\
            org.springframework.boot.autoconfigure.mobile.DeviceResolverAutoConfiguration,\
            org.springframework.boot.autoconfigure.mobile.DeviceDelegatingViewResolverAutoConfiguration,\
            org.springframework.boot.autoconfigure.mobile.SitePreferenceAutoConfiguration,\
            org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration,\
            org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
            org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration,\
            org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\
            org.springframework.boot.autoconfigure.reactor.ReactorAutoConfiguration,\
            org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration,\
            org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration,\
            org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration,\
            org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration,\
            org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration,\
            org.springframework.boot.autoconfigure.session.SessionAutoConfiguration,\
            org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration,\
            org.springframework.boot.autoconfigure.social.FacebookAutoConfiguration,\
            org.springframework.boot.autoconfigure.social.LinkedInAutoConfiguration,\
            org.springframework.boot.autoconfigure.social.TwitterAutoConfiguration,\
            org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration,\
            org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration,\
            org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration,\
            org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration,\
            org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration,\
            org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration,\
            org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration,\
            org.springframework.boot.autoconfigure.websocket.WebSocketMessagingAutoConfiguration,\
            org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration

        */

        FlywayAutoConfiguration gergergergerg;

        MongoDataAutoConfiguration vczvzxvzxcv;
        AutoConfigurationImportFilter gwefwef;
        AutoConfigurationImportListener fwefwe3513451435;
        ApplicationContextInitializer fwefwefwef234234;

        SpringApplicationAdminJmxAutoConfiguration ergqhqrhqrh;


        //  6

        EmbeddedServletContainerAutoConfiguration fwefoiwef989we89f;
        DispatcherServletAutoConfiguration fwefwef9090209f23f;

        ConfigurableEmbeddedServletContainer gerg345354345;
        AbstractConfigurableEmbeddedServletContainer reer4545;
        EmbeddedServletContainerFactory r23r23r2333452345r;
        AbstractEmbeddedServletContainerFactory fwefwef0203f;
        TomcatEmbeddedServletContainerFactory gwg4343t134twegweg;
        UndertowEmbeddedServletContainerFactory ggergeqrge314t143tqrg;
        JettyEmbeddedServletContainerFactory fwefqg34g341g;

        EmbeddedServletContainer fwef123123wef;
        JettyEmbeddedServletContainer r23r23r23r;
        TomcatEmbeddedServletContainer gqgerg43g34g;
        UndertowEmbeddedServletContainer gergergo090qergqrg;

        DispatcherServlet ds;

        AbstractApplicationContext fwe090we0fw0ef;
        GenericApplicationContext fwfwefwef;
        GenericWebApplicationContext fwef092f023f;
        EmbeddedWebApplicationContext fwefwef234234234;
        AnnotationConfigEmbeddedWebApplicationContext wef0090wefwef;
        XmlEmbeddedWebApplicationContext ergergpeorpogperg0909erg;

        EnableWebMvc wefwefwef430143901490;
        WebMvcConfigurer vfdvf122323;
        WebMvcConfigurerAdapter erobeqribnoiqerbqerb;
        WebMvcConfigurationSupport vv2390923confi;
        DelegatingWebMvcConfiguration greogioeirg;
        WebMvcAutoConfiguration.EnableWebMvcConfiguration rogioerigerg;
        WebServlet df;
        WebListener egerg;
        WebFilter fwef23;
        WebInitParam eropowe;


        ServletContextInitializer fwefwefwef23452435345;

        SpringBootCondition erververv;

//        EndpointWebMvcManagementContextConfiguration erf2309230f923f;

        SpringApplicationAdminJmxAutoConfiguration grgergwg0909g23g;

        FreeMarkerTemplateAvailabilityProvider gergergerg93148951485915;
        ConditionalOnClass ergerg0314901943014;
        ConditionalOnBean gregergerg2039023;


        EnableScheduling fwef01092;
        ApplicationContextInitializer fwefwefwef1203910293;

        BeanPostProcessor gregeg013409014;

        ConfigurationProperties tohijioqehr;
        EnableConfigurationProperties eroignoinerng;

        ApplicationContext ac;
        RequestMapping reqMap;

        EnableAutoConfiguration foweio23;
        DataSourceAutoConfiguration ewoi2o3io2i3of;

        WebMvcConfigurerAdapter weadpt;

        HttpMessageConverter wewwe;
        ServletRegistrationBean wqqwq232323;

        //  start
        EmbeddedServletContainerCustomizer fwefwe2323;

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

        GenericApplicationContext gac;
        EmbeddedWebApplicationContext eac;
        AnnotationConfigEmbeddedWebApplicationContext aaa23223;
        XmlEmbeddedWebApplicationContext xmleac;

        //  end

        AnnotationConfigApplicationContext ac121;
        AnnotationConfigEmbeddedWebApplicationContext eac11231;

        EnvironmentAware wefwef;
        Environment wefwef23f23;

        JdbcTemplateAutoConfiguration auorooro;
        ServerProperties rweoiwnoeb;

        ApplicationListener al;
        ApplicationContextInitializer acInit;
        ParentContextCloserApplicationListener a23223;
        ServletContextListener scl;
        EmbeddedServletContainerInitializedEvent aa232323;

        ServerEndpoint se;
        WebMvcProperties www;
        WebMvcConfigurer aa22323;
        WebMvcAutoConfiguration abwbe232323;
        WebMvcRegistrations eger4334;

        WebApplicationInitializer wainit;
        SpringBootServletInitializer sbsinit;
        AbstractContextLoaderInitializer weoiow23;
        AbstractDispatcherServletInitializer absdispsvlinit;
        AbstractAnnotationConfigDispatcherServletInitializer a2oinoi23;

        ServletContext sc;

        ImportBeanDefinitionRegistrar g34g98j9834g98h98h9;
        EnableAutoConfiguration aaa2323232112;
        CassandraAutoConfiguration ebqerbqerb2323;
        MongoAutoConfiguration d4gn3oi43o4ngoi34g;
        MongoDataAutoConfiguration qevqerbqerb23;
        MongoRepositoriesAutoConfiguration qoerinqeorbnoqerbno23902903;

        HelloAutoConfiguration aaaa23223;

        TransactionManagerCustomizers aaa232323;

        DataSourceAutoConfiguration erbeqb2223;


        //  ############################ WebMVC 相关 AutoConfiguration

        DispatcherServletAutoConfiguration erbqerbqeb323;
        HttpMessageConvertersAutoConfiguration g3g34g43;
        ServerPropertiesAutoConfiguration gr34go3ini3o4g;
        WebClientAutoConfiguration vrebe43g34g;
        EmbeddedServletContainerAutoConfiguration lnoienrboerb34g;


        //  ############################ Initializer

        /**
         * 类 ServletContainerInitializer 的 子类的 onStartup 方法是一个web应用中，我们的代码可以控制到的最早时间点。
         * 其 子类 SpringServletContainerInitializer 专门处理各种 WebApplicationInitializer；
         * 循环地 调起 各个 WebApplicationInitializer 的子类 的 方法 onStartup。
         *
         * 简单地说，SpringServletContainerInitializer 负责将 ServletContext 实例化并委托
         * 给用户定义的 WebApplicationInitializer 实现。
         * 然后每个 WebApplicationInitializer 负责完成初始化 ServletContext 的实际工作。
         * */
        ServletContainerInitializer rthr34oi;
            WsSci swioeowie;
            SpringServletContainerInitializer noin340h89034;
            LogbackServletContainerInitializer aaa34g09340g9j09;
            MyServletContainerInitializer g3ig039g093k4g0k; //   No Recognized

        WebApplicationInitializer niog340;
            SpringBootServletInitializer noi3480384g;
            AbstractContextLoaderInitializer n03n4g083490n;
                AbstractDispatcherServletInitializer n034ng03409g;
                    AbstractAnnotationConfigDispatcherServletInitializer aaa0394g0934g;
            JerseyAutoConfiguration.JerseyWebApplicationInitializer gbgbg098hg34g34;
            MyWebApplicationInitializer f3gi3ng3o4igno3in4ogi3o4gio;    //  No Recognized
            MyWebApplicationInitializer2 f3gg3498j9g834984ogi3o4gio;    //  No Recognized


        /**
         * 注册Servlet、Filter、Listener的方法，两种：
         *
         * 1， （war）注册到 WebApplicationInitializer 的实现类中：
         *      servletContext.addListener()
         *      servletContext.addFilter()
         *      servletContext.addServlet()
         * 2， （jar）注册到任意一个 @Configuration 配置类中：
         *      ServletRegistrationBean
         *      FilterRegistrationBean
         *      ServletListenerRegistrationBean
         * */

        /**
         * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
         * 总而言之，可以总结一下：
         * 0， 始祖级的 Tomcat 的方法 org.apache.catalina.core.StandardContext#startInternal() 调用各个 ServletContainerInitializer 的 onStartup；
         * 1， 其中一个 Initializer --- SpringServletContainerInitializer，启动 各个WebApplicationInitializer（似乎 ContextLoaderListener 有同样的功效）；
         * 2， 接口WebApplicationInitializer 的 方法onStartup 的参数是 ServletContext；
         * 2b, 其中 子类SpringBootServletInitializer 有点特殊功能：拉起 ContextLoaderListener；
         * 3， 接口ApplicationContextInitializer 的 方法onStartup 的 参数是 ? extends ConfigurableApplicationContext。
         * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
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
        /*
            ## springboot
            # Application Context Initializers
            org.springframework.context.ApplicationContextInitializer=\
            org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer,\
            org.springframework.boot.context.ContextIdApplicationContextInitializer,\
            org.springframework.boot.context.config.DelegatingApplicationContextInitializer,\
            org.springframework.boot.context.embedded.ServerPortInfoApplicationContextInitializer

            ## springboot-autoconfigure
            # Initializers
            org.springframework.context.ApplicationContextInitializer=\
            org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer,\
            org.springframework.boot.autoconfigure.logging.AutoConfigurationReportLoggingInitializer
        */

    //  ############################ Servlet Listener

        ServletContextListener oieoivoir34g34g;
            ContextLoaderListener vr3robinoi3bnio;
            WebAppRootListener j0923jf02093;
            ContextCleanupListener fff34ogin3oigo43i;
            WsContextListener aaa3gpompopo;
            IntrospectorCleanupListener fgogoi34goi;
            MyServletContextListener gg34g09j093jg904;  // Recognized
            MyListener jf293f982j3f9823;

        ServletContextEvent g34oinoi3n4ogi3no4ig;
            ServletContextAttributeEvent g3goim3oin4go3in4goin6;

        /**
         * 接口 ServletContextListener 可以实现，做一些 定制性 的事情；
         * 类 ContextLoaderListener 已经实现了 接口 ServletContextListener，
         * 借助 "工具类" ContextLoader 完成了一些事情了，算是一个输出性的东西，不容置喙。
         * 
         * 接口 ContextLoaderListener 绝对不是“输出性的东西” ，而且非常重要，是 “初始化” 的起点！
         * 在此之前 一直在收集配置；再此之后，开始使用配置（初始化），形成具有完整层级和正确功能的 AC！
         * 这个过程由 接口 ServletContextListener 的实现类 ContextLoaderListener 发起！
         */

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

        ContextLoader gregerg34gj093j409g;
        ServiceLoader oinepirg0394jg03j94;
        ClassLoader grg0934g0j3094g09;

        //  ############################ AutoConfiguration 1 WebMvc

        EnableWebMvc afef2232323;
        WebMvcAutoConfiguration fwefweg34ginoi3n4g;
        WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter g34g4hnoi42nho24ohi;
        WebMvcConfigurerAdapter ewgtwrth3223;
        WebMvcConfigurer rbqebrqe43g;

        /**
         * + @Configuration
         * implements WebMvcConfigurer                  ：不会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
         * implements WebMvcConfigurer + @EnableWebMvc  ：会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
         * extends WebMvcConfigurationSupport           ：会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
         * extends DelegatingWebMvcConfiguration        ：会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
         * implements WebMvcConfigurer + @EnableAutoConfiguration   ：OK
         * */

        /*
        * https://www.youtube.com/watch?v=uDl1qlJWE7A
        * https://www.youtube.com/watch?v=viP3VCx1X6w
        * */

        ServletListenerRegistrationBean onionoi34343;
        FilterRegistrationBean eroinoi23224;
        ServletRegistrationBean oeqrinboi45981450801;

        ImportBeanDefinitionRegistrar lknlknlk3434;
        ImportSelector g34g34;
        AutoConfigurationImportSelector greon3o4i;


        //  ############################ AutoConfiguration 2 EmbeddedServletContainerAutoConfiguration

        /**
         * 这里有个问题：
         * ServletContextInitializer 与 ContextLoaderListener 有什么关系？
         * */

        EmbeddedServletContainerAutoConfiguration g43g34;

        EmbeddedServletContainerFactory g30g93094g0934;
            AbstractEmbeddedServletContainerFactory g34g03409gk3049gk09;
                JettyEmbeddedServletContainerFactory g34j0394g0349gk09;
                TomcatEmbeddedServletContainerFactory g30g304gk09;
                UndertowEmbeddedServletContainerFactory g3049gk0394kg09;

        ServletContextInitializer g30g90394g09;
            RegistrationBean g3gergp;
            ServletRegistrationBean g340j3094g;
            ServletListenerRegistrationBean g34gj03j4g09;
                FilterRegistrationBean g34gj0394g034gk09;
                DelegatingFilterProxyRegistrationBean g0349gj3094g09;

        //  ############################ AutoConfiguration 3


        //  ############################ AutoConfiguration 4


        //  ############################ AutoConfiguration 5


        //  ############################ AutoConfiguration 6


        /*
        * TODO 必须搞搞清楚
        * spring.factories 文件有很多个，而且其中不止有 AutoConfigure 一种，
        * 还有 其他十多种，是否 所有的这些，都是通过 EnableAutoConfiguration 拉入？
        * 具体拉入逻辑需要研究研究，搞搞清楚。
        *
        *
        * spring.factories 文件的类型：
        *
        * （默认情况下，springboot 启动时，SpringFactoriesLoader 拉入的类型的顺讯如下，某几个多次出现）
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
        * RepositoryFactorySupport
        * RepositoryFactorySupport
        * TestExecutionListener
        * ContextCustomizerFactory
        *
        * 应该是这样，spring-boot 启动，有一个繁复的过程；
        * 在这个过程中，有很多个阶段，不同的阶段，会需要不同的"配置"。
        *
        * 比如 ApplicationContextInitializer 相关阶段，需要 从 spring.factories 获取 "类型"ApplicationContextInitializer 的配置；
        * 而 ApplicationListener 相关阶段，需要 从 spring.factories 获取 "类型"ApplicationListener 的配置。
        * 然后 AutoConfiguration 相关阶段，需要 从 spring.factories 获取 "类型"AutoConfiguration 的配置。
        *
        * 大概情况应该就是这样的逻辑。
        *
        * 最重要的东西 就是 类 SpringFactoriesLoader，接受 类型 作为 参数key，获取对应类型的 配置。
        * */

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


        //  ############################ EnableXXX Anno Customize

        /**
         * 使用 AutoConfiguration 有两种做法
         * 1， 把 @Configuration 的类，加入 *.factories，作为 key EnableAutoConfiguration 的 value；
         *      这样的话，EnableAutoConfiguration 的 Importer 会 以 EnableAutoConfiguration 为类型 load 这个 类。
         * 2， 创建一个 注解 @EnableXXX；其定义本身 具有 注解 Import，把 @Configuration 的类 作为参数。
         *
         *
         * 接口 ImportSelector 声明了 一个方法 selectImports；
         *
         * 注解 Import 可以接受 三种类型的参数（可以被加载的类型）：
         *  ImportSelector 实现类 （ @EnableAutoConfiguration）
         *  Configuration 注解 （其他 @EnableXXX）
         *  ImportBeanDefinitionRegistrar 实现类 （其他。。。）
         *
         * */


}

