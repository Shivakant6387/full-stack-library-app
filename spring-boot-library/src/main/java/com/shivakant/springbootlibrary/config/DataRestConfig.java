package com.shivakant.springbootlibrary.config;

import com.shivakant.springbootlibrary.model.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "http://localhost:3000";

    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions
                = {HttpMethod.PATCH,
                HttpMethod.POST,
                HttpMethod.PUT,
                HttpMethod.DELETE,
        };
        config.exposeIdsFor(Book.class);
        disableHttpMethod(Book.class, config, theUnsupportedActions);
        /*Configure Cors Mapping*/
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);
    }

    public void disableHttpMethod(Class theClass,
                                  RepositoryRestConfiguration config,
                                  HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
}
