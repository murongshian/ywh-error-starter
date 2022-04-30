package ywh.error.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ywh.error.starter.controller.ErrorController;

@Configuration
@ConditionalOnClass(ErrorController.class)
public class ErrorControllerAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    ErrorController userController (){
        return new ErrorController();
    }
}