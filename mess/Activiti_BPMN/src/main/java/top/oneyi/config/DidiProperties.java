package top.oneyi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 添加元数据
 *
 * @author oneyi
 * @date 2023/5/17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "com.didispace")
public class DidiProperties {

    /**
     * 这是一个测试配置
     */
    private String from;

}
