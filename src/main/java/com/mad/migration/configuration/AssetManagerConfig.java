package com.mad.migration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.directv.apg.mad.asset.service.AssetBuilderService;
import com.directv.apg.mad.asset.service.impl.AssetBuilderServiceImpl;

@Configuration
public class AssetManagerConfig {
	
	@Bean
	public AssetBuilderService assetManager() {
		return new AssetBuilderServiceImpl();

	}
	
}
