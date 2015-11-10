package com.mad.migration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.mad.migration.job.JobLauncher;

@SpringBootApplication
@ComponentScan(value={"com.mad.migration"})
public class Main  {
	
    public static void main(String[] args) throws Exception{
       ApplicationContext ctx =  SpringApplication.run(Main.class, args);
       if(ctx  != null) {
    	   JobLauncher launcher = ctx.getBean(JobLauncher.class);
    	   launcher.launcher();
       }       
    }
}