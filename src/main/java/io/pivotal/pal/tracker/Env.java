package io.pivotal.pal.tracker;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@ConfigurationProperties
@Component
public class Env {
    private int port;
    private int memoryLimit;
    private String cfInstanceIndex;
    private InetAddress cfInstanceAddr;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getCfInstanceIndex() {
        return cfInstanceIndex;
    }

    public void setCfInstanceIndex(String cfInstanceIndex) {
        this.cfInstanceIndex = cfInstanceIndex;
    }

    public InetAddress getCfInstanceAddr() {
        return cfInstanceAddr;
    }

    public void setCfInstanceAddr(InetAddress cfInstanceAddr) {
        this.cfInstanceAddr = cfInstanceAddr;
    }
}
