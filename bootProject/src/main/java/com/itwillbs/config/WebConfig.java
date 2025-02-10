package com.itwillbs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	//  application.properties  경로 지정
	//	uploadPath = D:/Shared/JSP/workspace_sts4/bootProject/src/main/resources/static/upload
	
	@Value("${uploadPath}")
	String uploadPath;
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**") //  웹경로 static/upload/
				.addResourceLocations(uploadPath); // 물리적인 다른 경로
	}
	
}
