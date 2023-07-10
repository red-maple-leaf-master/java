package top.oneyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;
import top.oneyi.config.ApplicationFilter;

@MapperScan("top.oneyi.mapper")
@SpringBootApplication
public class OneApplication {
    public static void main(String[] args) {
        SpringApplication.run(OneApplication.class, args);
    }

    /**
     * 注册过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<ApplicationFilter> registrationBean() {
        FilterRegistrationBean<ApplicationFilter> filterRegistrationBean = new FilterRegistrationBean<>(new ApplicationFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
