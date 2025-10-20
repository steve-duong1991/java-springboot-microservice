package steveduong.v2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestApiConfig implements WebMvcConfigurer {

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.defaultContentType(MediaType.APPLICATION_JSON);
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    // learn later
    WebMvcConfigurer.super.addFormatters(registry);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // learn later
    WebMvcConfigurer.super.addInterceptors(registry);
  }
}
