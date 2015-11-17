package com.mad.migration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import com.directv.apg.mad.asset.builder.AssetBuilder;
import com.directv.apg.mad.asset.builder.PropertyPlaceHolderMapping;
import com.directv.apg.mad.asset.service.AssetBuilderService;
import com.directv.apg.mad.asset.service.impl.AssetBuilderServiceImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class AssetManagerConfig {
	
	@Bean
	public PropertyPlaceHolderMapping mappingProperties() {
		
		PropertyPlaceHolderMapping mappingProperties = new PropertyPlaceHolderMapping();
		mappingProperties.setLocation(new ClassPathResource("application.properties"));
		return mappingProperties;
		
	}
	
	@Bean
	public AssetBuilder assetBuilder() {
		return new AssetBuilder();
	}
	
	@Bean
	public AssetBuilderService assetManager() {
		return new AssetBuilderServiceImpl();

	}
	
}
