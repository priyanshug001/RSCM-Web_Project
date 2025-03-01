package com.RSCM.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addContextCustomizers((Context context) -> context.setAllowCasualMultipartParsing(true)); // File upload support
        return factory;
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
        return factory -> {
            factory.setPort(8080); // Default 8080 hota hai, isko 8081 kar diya
            factory.setContextPath("/index"); // Default `/` hota hai, isko `/myapp` kiya
        };
    }
}
