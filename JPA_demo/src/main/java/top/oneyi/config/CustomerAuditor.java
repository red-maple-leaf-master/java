package top.oneyi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class CustomerAuditor implements AuditorAware<String> {
    /**
     *  获取当前创建或修改的用户
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.of("我是用户名");
    }
}
