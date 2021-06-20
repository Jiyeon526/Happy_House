package com.ssafy.house;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.house.interceptor.JwtInterceptor;

@SpringBootApplication
public class MyWorkFinalPjtApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MyWorkFinalPjtApplication.class, args);
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/house/**")
				.excludePathPatterns(Arrays.asList("/house/member/**"))
				.excludePathPatterns(Arrays.asList("/house/houselist/**"))
				.excludePathPatterns(Arrays.asList("/house/housesearch/**"))
				.excludePathPatterns(Arrays.asList("/house/houseread/**"))
				.excludePathPatterns(Arrays.asList("/house/community/**"))
				.excludePathPatterns(Arrays.asList("/**"))
				.excludePathPatterns(Arrays.asList("/index.html"))
				.excludePathPatterns(Arrays.asList("/css/**"))
				.excludePathPatterns(Arrays.asList("/img/**"))
				.excludePathPatterns(Arrays.asList("/js/**"))
				.excludePathPatterns(Arrays.asList("/fonts/**"));
	}
}
