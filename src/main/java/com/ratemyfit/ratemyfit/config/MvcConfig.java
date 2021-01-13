package com.ratemyfit.ratemyfit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 
 * MvcConfig.java
 * Purpose: Picture upload path handler.
  * @author Florian Jäger
 */


@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path pictureUploadDir = Paths.get("./outfits/");
        String pictureUploadPath = pictureUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/outfits/**").addResourceLocations("file:/"+ pictureUploadPath + "/");
    }
}
