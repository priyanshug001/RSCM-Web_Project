package com.RSCM.config;

import java.io.File;
import org.apache.catalina.Context;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addContextCustomizers((Context context) -> context.setAllowCasualMultipartParsing(true)); // File upload support
        factory.setDocumentRoot(new File("src/main/webapp"));
        return factory;
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
        return factory -> {
            factory.setPort(8080); // Default 8080 hota hai, isko 8081 kar diya
            factory.setContextPath(""); // Default `/` hota hai, isko `/myapp` kiya
        };
    }
}
