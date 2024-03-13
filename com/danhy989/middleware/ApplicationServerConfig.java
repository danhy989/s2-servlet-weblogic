package com.danhy989.middleware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class ApplicationServerConfig implements ServletContextListener {
    private static final Log log = LogFactory.getLog(ApplicationServerConfig.class);
    private final static String USER_DIR = System.getProperty("user.dir");
    private final static String LOG4J_PATH = "log4jPath";
    private final static String LOG = "logs";
    private final static String WEBLOGIC_JNDI_WAR_CONFIG_NAME = "java:comp/env/warConfigName";
    private final static String WEBLOGIC_FILE_CONFIG = "weblogic.txt";
    private final static String WWEBLOGIC_CONFIG_PATH = "weblogicConfigPath";
    private final static String TOMCAT_CATALINA_HOME = "catalina.home";
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Context context = new InitialContext();
            AbstractApplicationServer applicationServer = null;
            if (System.getProperty(ApplicationServerType.WEBLOGIC.getName()) != null) {
                this.setEnvForWebLogic(context);
                applicationServer = new WeblogicApplicationServer();
            } else if (System.getProperty(ApplicationServerType.TOMCAT.getName()) != null) {
                this.setEnvForTomcat();
                applicationServer = new TomcatApplicationServer();
            }
            if(null != applicationServer){
                applicationServer.initialize();
            }
        } catch (NamingException e) {
            log.error("NamingException: " + e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Clean up resources if needed
    }

    private void setEnvForWebLogic(Context context) throws NamingException {
        String warConfigName = (String) context.lookup(WEBLOGIC_JNDI_WAR_CONFIG_NAME);
        final String log4jPath = USER_DIR + File.separator +warConfigName + File.separator + LOG;
        final String weblogicConfigPath = USER_DIR + File.separator +warConfigName+ File.separator + WEBLOGIC_FILE_CONFIG;
        System.setProperty(LOG4J_PATH, log4jPath);
        log.info("log4jPath: " + log4jPath);
        System.setProperty(WWEBLOGIC_CONFIG_PATH, weblogicConfigPath);
        log.info("weblogicConfigPath: " + weblogicConfigPath);
    }

    private void setEnvForTomcat(){
        final String catalinaHome = System.getProperty(TOMCAT_CATALINA_HOME);
        final String log4jPath = catalinaHome + File.separator + LOG;
        System.setProperty(LOG4J_PATH, log4jPath);
        log.info("log4jPath: " + log4jPath);
    }

}
