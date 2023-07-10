package top.oneyi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 *
 * @author oneyi
 */
@Configuration
public class CorsConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 设置所有的请求  可以进行跨域
                        .allowCredentials(false)
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 这里也可以使用 * 代替
                        .allowedOrigins("*"); // 可以设置允许跨域的ip 或者使用 * 代替
            }
        };
    }

}

