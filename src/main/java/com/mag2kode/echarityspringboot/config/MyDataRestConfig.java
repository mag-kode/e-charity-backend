package com.mag2kode.echarityspringboot.config;

import com.mag2kode.echarityspringboot.entity.Donation;
import com.mag2kode.echarityspringboot.entity.DonationCategory;
import jakarta.persistence.*;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ManagedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

        // disable HTTP methods for Product: PUT, POST, DELETE and PATCH
        config.getExposureConfiguration()
                .forDomainType(Donation.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        // disable HTTP methods for ProductCategory: PUT, POST, DELETE and PATCH
        config.getExposureConfiguration()
                .forDomainType(DonationCategory.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config){

        //list of all entity classes
        Set<EntityType<?>> allEntities = entityManager.getMetamodel().getEntities();

        //array of entity types
        List<Class> entityClasses = new ArrayList<>();

        //entity types for entity
        for (EntityType e : allEntities) {
            entityClasses.add(e.getJavaType());
        }

        //expose the entity ids for the array of entity types
        config.exposeIdsFor(entityClasses.toArray(new Class[0]));
    }
}