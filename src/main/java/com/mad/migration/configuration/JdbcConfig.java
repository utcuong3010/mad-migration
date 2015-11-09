package com.mad.migration.configuration;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class JdbcConfig{

	@Autowired	
	private DataSource dataSource;
	
//	@Autowired
//	private TransactionManager transactionManager;
//	
	
	@Bean
	public DataSourceInitializer databasePopulor() {
		
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
//		databasePopulator.addScript(new ClassPathResource("org/springframework/batch/core/schema-drop-mysql.sql"));		
		databasePopulator.setContinueOnError(true);
		databasePopulator.setIgnoreFailedDrops(true);
		
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator);
		
		return initializer;
	}
	
}
