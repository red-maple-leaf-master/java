package top.oneyi.generator.service;

import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/20/10:41
 */
public interface GeneratorService {
    /**
     * 预览代码
     * @return
     */
    Map<String, Object> preview() throws IOException;
}
