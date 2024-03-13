# s2-servlet-weblogic
Deploy a servlet application server (WAR) into Oracle WebLogic (Tutorial)

**Step:**
1. Copy folder app_config to $Oracle_Home\user_projects\domains\base_domain\
2. Edit config in app_config\weblogic.txt
3. Set Memory in setDomainEnv.cmd
    @REM IF USER_MEM_ARGS the environment variable is set, use it to override ALL MEM_ARGS values
    set USER_MEM_ARGS=-Xms10000m -Xmx20000m %MEM_DEV_ARGS% %MEM_MAX_PERM_SIZE%
4. Copy the war app folder (exploded) to $Oracle_Home\user_projects\domains\base_domain\autodeploy
5. Start a WebLogic server



   
