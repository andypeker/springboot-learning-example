package com.spring.springboot;

/**
 * @author Frankie Yang on 2019-06-20.
 */
public class InsightContexts {

    /**
     * TODO 还有一个重要问题没有搞清楚：SpringBoot启动过程中根上下文和子上下文的分解。
     * SpringBoot 似乎并没有严格区分（属于MVC的）这两个阶段，有可能 在 SpringBoot 中只有一个方法，
     * 而这个方法属于MVC，两个阶段都在这个方法内；以至于在 SpringBoot 看来，是同一个事情。
     * Spring Boot 没有 ROOT-Dispatcher 父子结构，默认只有一个 上下文！
     *
     * No-web spring boot应用，context是AnnotationConfigApplicationContext
     * web spring boot应用，context是AnnotationConfigEmbeddedWebApplicationContext
     *
     */

    /***
     * 2019-01-29
     * Why use Application Hierachy ?
     *
     * --- from stackoverflow
     * --- 1
     * The classic use-case for this is when you have multiple Spring DispatcherServlet
     * within a single webapp, with each of these servlets having their own app context,
     * but which need to share beans between them. In this case, you add a 3rd context at
     * the level of the webapp, which is the parent of each of the servlet appcontexts.
     *
     * You can take this pattern further, for example if you have multiple webapps bundled
     * into a single JavaEE EAR. Here, the EAR can have its own context, which is the parent
     * of the individual webapp contexts, which is the parent of the servlet contexts, and so on.
     * You have this hierarchy of responsibility.
     *
     * In other situations, the context structure is dictated by some other factor. For example,
     * Spring Security is independent of Spring MVC, and requires its configuration beans to go
     * in the webapp context. If you want to use Spring MVC with it, then the config for that has
     * to go into the servlet context, which has the root webapp context as its parent.
     *
     * --- 2
     * In reading further, understood the following (skaffman has already indicated
     * parts of this). Each Spring MVC webapp has one root application context and
     * one servlet application context for each DispatcherServlet. The root application context is
     * the parent of each servlet application context.
     *
     * Beans defined in "contextConfigLocation" (context-param in web.xml) are loaded into root
     * application context.
     * Beans in <servlet-name>-servlet.xml are loaded into servlet application context.
     * If an EAR has multiple web apps, an EAR level application context can parent the root context
     * of each webapp in EAR.
     *
     * */


}
