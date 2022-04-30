package ywh.error.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ywh.error.starter.advice.ActionAdvice;

@Configuration
@ConditionalOnClass(ActionAdvice.class)
public class AdviceAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    ActionAdvice actionAdvice (){
        return new ActionAdvice();
    }
}
