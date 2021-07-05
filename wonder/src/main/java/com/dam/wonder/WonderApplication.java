package com.dam.wonder;

import com.dam.wonder.model.config.thread.ThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties({ThreadPoolConfig.class} )
public class WonderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WonderApplication.class, args);
    }

}
