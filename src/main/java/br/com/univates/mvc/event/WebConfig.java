package br.com.univates.mvc.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.univates.mvc.event.interceptor.AccessInterceptor;

/**
 * @author Arthur
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public AccessInterceptor accessInterceptor() {
		return new AccessInterceptor();
	}

}
