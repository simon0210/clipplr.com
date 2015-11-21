package com.clipplr.platform.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by simon on 4/19/15.
 */
@ImportResource("classpath:net/bull/javamelody/monitoring-spring.xml")
@EnableTransactionManagement
@Configuration
@MapperScan("com.clipplr.platform.persistence.mybatis.mapper")                                            /* location of mapper (java) */
@ComponentScan(basePackages = {"com.clipplr.platform.persistence.service"}, useDefaultFilters = false,    /* location of serviceImpl (java) */
                includeFilters = { @ComponentScan.Filter(Service.class)})
public class ClipplrDatabaseConfig implements TransactionManagementConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ClipplrDatabaseConfig.class);

    @Bean
    public DataSource dataSource() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("java:comp/env/jdbc/db_clipplr");

        try {
            jndiObjectFactoryBean.afterPropertiesSet();
        } catch (NamingException e) {
           logger.error("JNDI NamingException {}", e.toString());
        }

        return (DataSource) jndiObjectFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:configuration/mybatis-config.xml"));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/**/*.xml"));
        return sessionFactory.getObject();
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}
