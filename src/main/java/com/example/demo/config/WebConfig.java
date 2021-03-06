package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class WebConfig {
    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix( "templates/" );
        templateResolver.setCacheable( false );
        templateResolver.setSuffix( ".html" );
        templateResolver.setTemplateMode( "HTML" );
        templateResolver.setCharacterEncoding( "UTF-8" );
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler( true );
        templateEngine.setTemplateResolver( templateResolver() );
        templateEngine.addDialect( new SpringSecurityDialect() );
        templateEngine.addDialect( new Java8TimeDialect() );
        return templateEngine;
    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine( templateEngine() );
        viewResolver.setCharacterEncoding( "UTF-8" );
        return viewResolver;
    }
}
