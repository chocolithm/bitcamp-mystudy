package bitcamp.listener;

import bitcamp.context.ApplicationContext;

public interface ApplicationListener {
  void onStartup(ApplicationContext context);

  void onShutdown(ApplicationContext context);
}
