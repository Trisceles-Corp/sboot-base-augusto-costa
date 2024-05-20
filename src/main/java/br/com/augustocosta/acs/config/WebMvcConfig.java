package br.com.augustocosta.acs.config;

import br.com.augustocosta.acs.presentation.interceptor.AuthInterceptor;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login2/**", "/css/**", "/js/**", "/bootstrap/**", "/fonts/**");
    }

//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
//        return factory -> {
//            RedirectWebServerFactoryCustomizer customizer = new RedirectWebServerFactoryCustomizer();
//            customizer.customize(factory);
//        };
//    }

    static class RedirectWebServerFactoryCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
        @Override
        public void customize(TomcatServletWebServerFactory factory) {
            factory.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
        }

        private Connector httpToHttpsRedirectConnector() {
            Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
            connector.setScheme("http");
            connector.setPort(8080);
            connector.setSecure(false);
            connector.setRedirectPort(8443);
            return connector;
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/");
    }
}