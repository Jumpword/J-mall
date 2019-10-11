package com.jmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author jumping-張文平
 * @Date 2019/10/11 20:29
 * @Version 1.0
 */
@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        //1.添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1).允许的域名，不要写*否则cookis就无法使用
        config.addAllowedOrigin("http://www.jmall.com");
        //2).是否发送cookies信息
        config.setAllowCredentials(true);
        //2).允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        //4).允许的头信息
        config.addAllowedHeader("*");
        //5).有效时长
        config.setMaxAge(3600L);

        //添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/***",config);

        //返回新的CorsFilter
        return new CorsFilter(configurationSource);


    }
}
