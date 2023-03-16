package top.oneyi.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 解决跨域问题
 * @author oneyi
 * @date 2023/3/16
 */

@Component
public class CorsOrigin implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 前端的地址
                .allowedOrigins("http://localhost:1010")
                .allowCredentials(true);
    }
}
