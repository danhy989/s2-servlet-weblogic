package com.danhy989.middleware;

public enum ApplicationServerType {
    TOMCAT("catalina.home"), WEBLOGIC("weblogic.Name");
    ApplicationServerType(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }

}
