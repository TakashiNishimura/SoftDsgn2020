package com.example.wsp_spring.model;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * アプリケーションのBeanライフサイクルを管理するクラスです.
 */
@Component
public class BeanLifeCycle {

  @PostConstruct
  public void initAfterStartup() {
    System.out.println("start");
  }

  @PreDestroy
  public void cleanupBeforeExit() {
    System.out.println("exit");
  }

}
