package com.tstu.airflight.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "neo4j")
@Getter
@Setter
public class Neo4jProperties {
    private String host;
    private String port;
    private String username;
    private String password;
}
