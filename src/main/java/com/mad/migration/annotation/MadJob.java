package com.mad.migration.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface MadJob {
	
	/***
	 * name of job
	 * @return
	 */
	String name() default "";
	
	/***
	 * disable or not
	 * @return
	 */
	boolean enable() default true;
}
