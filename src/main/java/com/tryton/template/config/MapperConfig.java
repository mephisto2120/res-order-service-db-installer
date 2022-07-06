package com.tryton.template.config;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.tryton.template.mapper")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@RequiredArgsConstructor
class MapperConfig {

    private final DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setInitialSize(dataSourceProperties.getInitialSize());
        dataSource.setMaxWait(dataSourceProperties.getMaxWait());
        dataSource.setMaxActive(dataSourceProperties.getMaxActive());
        dataSource.setMaxIdle(dataSourceProperties.getMaxIdle());
        dataSource.setMinIdle(dataSourceProperties.getMinIdle());
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IllegalStateException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        try {
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create sqlSessionFactory ", e);
        }
    }

    @Bean
    public HealthIndicator dbHealthIndicator() {
        return new DataSourceHealthIndicator(dataSource(), "SELECT 1");
    }
}
