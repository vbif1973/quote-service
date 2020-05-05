package com.epam.quoteservice.bootstrap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class QuoteServletContextListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(QuoteServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        //TODO load data from db into caches
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        //TODO save data from caches into db
    }


}
