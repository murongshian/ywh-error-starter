package ywh.error.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ywh.error.starter.service.ErrorService;

@Configuration
@ConditionalOnClass(ErrorService.class)
public class ErrorServiceAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    ErrorService userService (){
        return new ErrorService();
    }
}