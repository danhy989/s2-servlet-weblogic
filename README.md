# s2-servlet-weblogic
Deploy a servlet application server (WAR) into Oracle WebLogic (Tutorial)

**Step:**
1. Installing Oracle Weblogic (Tested on version 12.2.1.3.0)
2. Copy app_config folder to $Oracle_Home\user_projects\domains\base_domain\
3. Edit config in app_config\weblogic.txt
4. Edit your WEB-INF/web.xml. Add the below code:
    <env-entry>
        <env-entry-name>warConfigName</env-entry-name>
        <env-entry-type>java.lang.String</env-entry-type>
        <env-entry-value>app_config</env-entry-value>
    </env-entry>

    <listener>
        <listener-class>com.danhy989.middleware.ApplicationServerConfig</listener-class>
    </listener>
5. Create a WebLogic config file under WEB-INF/weblogic.xml:
    <?xml version="1.0" encoding="UTF-8"?>
    <weblogic-web-app xmlns="http://xmlns.oracle.com/weblogic/weblogic-web-app"
                    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xsi:schemaLocation="http://xmlns.oracle.com/weblogic/weblogic-web-app
                                        http://xmlns.oracle.com/weblogic/weblogic-web-app/1.4/weblogic-web-app.xsd">
        <context-root>example-app</context-root>
    </weblogic-web-app>
6. Edit log4j path file: ${log4jPath}/app.log
7. Using config from Server Application: 
    Example: String path = AbstractApplicationServer.getFileTempDir();
8. Set Memory in setDomainEnv.cmd
    @REM IF USER_MEM_ARGS the environment variable is set, use it to override ALL MEM_ARGS values
    set USER_MEM_ARGS=-Xms4096m -Xmx9096m %MEM_DEV_ARGS% %MEM_MAX_PERM_SIZE%
9. Copy the war app folder (exploded) to $Oracle_Home\user_projects\domains\base_domain\autodeploy
10. Start a WebLogic server: startWebLogic.cmd

Enjoy!!!
