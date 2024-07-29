package bitcamp.listener;

import bitcamp.context.ApplicationContext;

public interface ApplicationListener {
  default void onStartup(ApplicationContext context) {
  }

  ;

  default void onShutdown(ApplicationContext context) {
  }

  ;
}
