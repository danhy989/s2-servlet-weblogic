package com.danhy989.middleware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractApplicationServer {
    private static final Log log = LogFactory.getLog(AbstractApplicationServer.class);
    protected static String driverClassName;
    protected static String userName;
    protected static String password;
    protected static String url;
    protected static Integer maxPool;
    protected static String batchAlertDir;
    protected static String fileTempDir;
    protected static String companyCode;
    protected static String imageDir;
    public static String getDriverClassName() {
        return driverClassName;
    }
    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUrl() {
        return url;
    }
    public static Integer getMaxPool() {
        return maxPool;
    }
    public static String getBatchAlertDir() {
        return batchAlertDir;
    }
    public static String getFileTempDir() {
        return fileTempDir;
    }

    public static String getCompanyCode() {
        return companyCode;
    }
    public static String getImageDir() {
        return imageDir;
    }
    public abstract void initialize();
    protected void showApplicationServerInfo(){
        log.info("driverClassName: " + driverClassName);
        log.info("url: " + url);
        log.info("userName: " + userName);
        log.info("password: " + password);
        log.info("maxPool: " + maxPool);
        log.info("companyCode: " + companyCode);
        log.info("fileTempDir: " + fileTempDir);
        log.info("batchAlertDir: " + batchAlertDir);
        log.info("imageDir: " + imageDir);
    }
}
