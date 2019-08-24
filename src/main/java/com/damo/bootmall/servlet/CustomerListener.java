package com.damo.bootmall.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomerListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("context initialized ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("context destroyed");
    }
}
