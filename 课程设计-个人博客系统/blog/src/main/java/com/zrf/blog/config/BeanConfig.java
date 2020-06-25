package com.zrf.blog.config;

import com.zrf.blog.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用于将一些实体类放入Spring容器
 *
 * @author 张润发
 * @date
 */
@Configuration
public class BeanConfig {

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

}
