package com.xt.jiguo.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import com.xt.jiguo.interceptor.AdminInterceptor;
import com.xt.jiguo.interceptor.AuthInterceptor;
import lombok.extern.log4j.Log4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.List;

@Configuration
//@Lozg4j
@Log4j
public class jiguoApplicationConfiguration implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.warn("test");
        FastJsonHttpMessageConverter converter=new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }
    @Bean
    public LocaleResolver createLocaleResolver(){
        return new AcceptHeaderLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AuthInterceptor interceptor=new AuthInterceptor();
        registry.addInterceptor(interceptor).addPathPatterns("/thumb/**");
        AdminInterceptor adminInterceptor=new AdminInterceptor();
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true)
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedOrigins("http://127.0.0.1:8080","http://localhost:8080","http://localhost:8888","http://127.0.0.1:8888"
                ,"http://master:8080","http://192.168.231.4:8080","http://slave3:8080","http://192.168.231.7:8080");
    }


}
