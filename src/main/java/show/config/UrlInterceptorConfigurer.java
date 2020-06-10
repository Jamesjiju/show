package show.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//新增加一个类用来添加虚拟路径映射
@Configuration
public class UrlInterceptorConfigurer implements WebMvcConfigurer {    
	
	@Override    
	public void addResourceHandlers(ResourceHandlerRegistry registry) {        
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:/tts9/workspace/show/src/main/resources/static/images/");    
	}
}

