package show.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import show.interceptor.LoginInterceptor;

@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//获取拦截器对象
		HandlerInterceptor handler = new LoginInterceptor();
		List<String> patterns = new ArrayList<>();
		patterns.add("/users/reg");
		patterns.add("/users/login");
		patterns.add("/web/reg1.html");
		patterns.add("/web/login1.html");
		patterns.add("/area/findProvince");
		patterns.add("/area/findCity");
		patterns.add("/area/findArea");
		patterns.add("/js/**");
		patterns.add("/images/**");
		patterns.add("/css/**");
		patterns.add("/bootstrap3/**");
		patterns.add("/districts/**");
		patterns.add("/goods/**");
		System.out.println(99999);
//		registry.addInterceptor(handler).addPathPatterns("/**").excludePathPatterns("/web/reg1.html");
		registry.addInterceptor(handler).addPathPatterns("/**").excludePathPatterns(patterns);
	}

	
}
