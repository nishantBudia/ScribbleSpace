package com.nishant.ScribbleSpace.config;

import com.nishant.ScribbleSpace.util.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder();
    }
}
