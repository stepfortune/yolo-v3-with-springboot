package com.embed.detection.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class PathConfig {
    @Bean
    @Qualifier("rootPath")
    public String rootPath() {
        File file = new File("");
        return file.getAbsolutePath();
    }

    @Bean
    @Qualifier("resourcePath")
    public String resourcePath() {
        File file = new File("");
        return file.getAbsolutePath() + "/resources";
    }


    @Bean
    @Qualifier("cfgPath")
    public String cfgPath() {
        return null;
    }
}
