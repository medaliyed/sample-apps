package com.ali.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.HttpMethods;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import com.ali.ecommerce.entity.Product;
import com.ali.ecommerce.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		// TODO Auto-generated method stub
		//make the apis read only
		
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT,HttpMethod.POST, HttpMethod.DELETE};
		//disabling these methods
		
		config.getExposureConfiguration()
		.forDomainType(Product.class)
		.withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
		config.getExposureConfiguration()
		.forDomainType(ProductCategory.class)
		.withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}

}
