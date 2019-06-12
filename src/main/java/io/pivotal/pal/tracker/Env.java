package io.pivotal.pal.tracker;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@Component
public class Env {
    private int port;
    private String memoryLimit;
    private String cfInstanceIndex;
    private String cfInstanceAddr;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(String memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getCfInstanceIndex() {
        return cfInstanceIndex;
    }

    public void setCfInstanceIndex(String cfInstanceIndex) {
        this.cfInstanceIndex = cfInstanceIndex;
    }

    public String getCfInstanceAddr() {
        return cfInstanceAddr;
    }

    public void setCfInstanceAddr(String cfInstanceAddr) {
        this.cfInstanceAddr = cfInstanceAddr;
    }
}
