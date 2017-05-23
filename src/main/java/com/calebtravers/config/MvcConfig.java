package com.calebtravers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by adminbackup on 5/22/17.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/recipes").setViewName("recipe-home");
        registry.addViewController("/recipes/view/*").setViewName("recipe-detail");
        registry.addViewController("/lists").setViewName("list-home");
        registry.addViewController("lists/view/*").setViewName("list-detail");
        registry.addViewController("/recipes/add").setViewName("recipe-add");
        registry.addViewController("'/lists/add").setViewName("list-add");
        registry.addViewController("/login").setViewName("login");
    }

}
