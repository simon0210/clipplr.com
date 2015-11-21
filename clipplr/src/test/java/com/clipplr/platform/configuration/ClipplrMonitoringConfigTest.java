package com.clipplr.platform.configuration;

import net.bull.javamelody.*;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by simon on 6/21/15.
 */
@Configuration
public class ClipplrMonitoringConfigTest implements WebApplicationInitializer {

    @Bean
    public MonitoringSpringAdvisor annotationMonitoringAdvisor() {
        MonitoringSpringAdvisor annotationMonitoringAdvisor = new MonitoringSpringAdvisor();
        annotationMonitoringAdvisor.setPointcut(new MonitoredWithAnnotationPointcut()); // Add pointcut that matches javamelody's @MonitoredWithSpring annotated methods
        return annotationMonitoringAdvisor;
    }

    @Bean
    public static MonitoringSpringAdvisor requestMonitoringAdvisor() {
        MonitoringSpringAdvisor requestMonitoringAdvisor = new MonitoringSpringAdvisor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.clipplr.platform..*.*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)");
        requestMonitoringAdvisor.setPointcut(pointcut);
        return requestMonitoringAdvisor;
    }

    @Bean
    public static MonitoringSpringAdvisor serviceMonitoringAdvisor() {
        MonitoringSpringAdvisor serviceMonitoringAdvisor = new MonitoringSpringAdvisor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public * com.clipplr.platform..*.*(..)) and within(@org.springframework.stereotype.Service *)");
        serviceMonitoringAdvisor.setPointcut(pointcut);
        return serviceMonitoringAdvisor;
    }

    @Bean
    public static SpringDataSourceBeanPostProcessor springDataSourceBeanPostProcessor() {
        SpringDataSourceBeanPostProcessor springDataSourceBeanPostProcessor = new SpringDataSourceBeanPostProcessor();
        return springDataSourceBeanPostProcessor;
    }

    @Override
    public void onStartup(ServletContext servlet) throws ServletException {
        servlet.addListener(SessionListener.class);
        FilterRegistration.Dynamic filter = servlet.addFilter("monitoring", MonitoringFilter.class);
        filter.addMappingForUrlPatterns(null, true, "/*");
    }
}
