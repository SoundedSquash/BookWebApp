package edu.wctc.wtb.bookwebapp.listeners;

import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author jlombardo
 */
public class MyServletContextListener implements ServletContextListener {

    /**
     * Automatically called by server when application starts up. Currently
     * used to date stamp the event.
     * 
     * @param event - automatically triggered when application starts
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        Date date = new Date();
        sc.setAttribute("appStarted", date);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to do here
    }

}
