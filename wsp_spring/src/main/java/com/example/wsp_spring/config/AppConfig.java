package com.example.wsp_spring.config;

import com.example.wsp_spring.model.BeanLifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * アプリケーションのConfig定義クラスです.
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

  /**
   * {@link BeanLifeCycle}をアプリケーション起動時に生成します.
   */
  @Bean
  public BeanLifeCycle BeanLifeCycle() {
    return new BeanLifeCycle();
  }

}
