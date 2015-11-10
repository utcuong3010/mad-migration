package com.mad.migration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mad.migration.temp.AssetBuilderService;
import com.mad.migration.temp.AssetBuilderServiceImpl;

@Configuration
public class AssetManagerConfig {
	
	@Bean
	public AssetBuilderService assetManager() {
		return new AssetBuilderServiceImpl();

	}
	
}
