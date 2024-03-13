package com.danhy989.middleware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ResourceBundle;

public class TomcatApplicationServer extends AbstractApplicationServer {
    private static final Log log = LogFactory.getLog(TomcatApplicationServer.class);
    @Override
    public void initialize() {
        log.info("Tomcat initialize");
        try {
            if (!getValueFromContext() && !getValueFromProperty()) {
                throw new IllegalStateException("Database description is not found.");
            }
            showApplicationServerInfo();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    private boolean getValueFromContext() throws NamingException {
        InitialContext context = new InitialContext();
        driverClassName = getEnvString(context, "driverClassName");
        url = getEnvString(context, "url");
        userName = getEnvString(context, "userName");
        password = getEnvString(context, "password");
        maxPool = getEnvInteger(context, "maxPool");
        fileTempDir = getEnvString(context, "fileTempDir");
        batchAlertDir = getEnvString(context, "batchAlertDir");
        return driverClassName != null;
    }
    private boolean getValueFromProperty() {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        driverClassName = getPropertyString(bundle, "driverClassName");
        url = getPropertyString(bundle, "url");
        userName = getPropertyString(bundle, "userName");
        password = getPropertyString(bundle, "password");
        maxPool = getPropertyInteger(bundle, "maxPool");
        fileTempDir = getPropertyString(bundle, "fileTempDir");
        batchAlertDir = getPropertyString(bundle, "batchAlertDir");
        return driverClassName != null;
    }
    private String getEnvString(InitialContext context, String name) {
        try {
            return (String)context.lookup("java:comp/env/" + name);
        } catch (NamingException e) {
            log.error("no value defined at InitialContext : " + name);
            return null;
        }
    }
    private Integer getEnvInteger(InitialContext context, String name) {
        try {
            return (Integer)context.lookup("java:comp/env/" + name);
        } catch (NamingException e) {
            log.error("no value defined at InitialContext : " + name);
            return null;
        }
    }
    private String getPropertyString(ResourceBundle bundle, String name) {
        return bundle.getString(name);
    }
    private Integer getPropertyInteger(ResourceBundle bundle, String name) {
        return Integer.parseInt(bundle.getString(name));
    }
}
