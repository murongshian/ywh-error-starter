package ywh.error.starter.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ywh.error.starter.properties.DruidProperties;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DruidProperties.class)
public class DruidAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource(DruidProperties druidProperties) {
    	System.out.println(druidProperties.toString());
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidProperties.getDriver());
        dataSource.setUrl(druidProperties.getUrl());
        dataSource.setUsername(druidProperties.getUsername());
        dataSource.setPassword(druidProperties.getPassword());
        dataSource.setInitialSize(druidProperties.getInitialSize());
        dataSource.setMinIdle(druidProperties.getMinIdle());
        dataSource.setMaxActive(druidProperties.getMaxActive());
        dataSource.setMaxWait(druidProperties.getMaxWait());
        return dataSource;
    }
}