package com.danhy989.middleware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileReader;

public class WeblogicApplicationServer extends AbstractApplicationServer {
    private static final Log log = LogFactory.getLog(WeblogicApplicationServer.class);
    private final String WEBLOGIC_TXT_FILE = System.getProperty("weblogicConfigPath");
    @Override
    public void initialize() {
        log.info("Weblogic initialize");
        this.getValueFromWeblogic();
        showApplicationServerInfo();
    }
    private void getValueFromWeblogic() {
        try(BufferedReader reader = new BufferedReader(new FileReader(WEBLOGIC_TXT_FILE))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] keyValue = line.split("=");
                if(keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    switch (key) {
                        case "driverClassName":
                            driverClassName = value;
                            break;
                        case "url":
                            url = value;
                            break;
                        case "userName":
                            userName = value;
                            break;
                        case "password":
                            password = value;
                            break;
                        case "maxPool":
                            maxPool = Integer.parseInt(value);
                            break;
                        case "companyCode":
                            companyCode = value;
                            break;
                        case "imageDir":
                            imageDir = value;
                            break;
                        case "batchAlertDir":
                            batchAlertDir = value;
                            break;
                        case "fileTempDir":
                            fileTempDir = value;
                            break;
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("No value defined at Weblogic : " + e.getMessage());
        }
    }
}
