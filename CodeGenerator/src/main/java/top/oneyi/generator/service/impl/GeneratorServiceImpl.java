package top.oneyi.generator.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import top.oneyi.generator.common.GenConstants;
import top.oneyi.generator.domain.Generator;
import top.oneyi.generator.service.GeneratorService;
import top.oneyi.generator.utils.DbUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/20/10:41
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {


    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 预览代码
     * @param generator        生成代码所需作者包名等数据
     * @return
     */
    @Override
    public Map<String, Object> preview(Generator generator) {

        // 创建输出对象
        Map<String, Object> map = new HashMap<>();
        // 输出为字符串
        StringWriter resultStr = new StringWriter();
        try {
            // 设置数据
            setModelData(map, resultStr, generator);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            try {
                resultStr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //返回生成好的代码
        return map;
    }

    /**
     * 设置返回对象的值
     *
     * @param map           返回的对象
     * @param resultStr     保存模板转换之后的对象
     * @param generator        生成代码所需作者包名等数据
     */
    private void setModelData(Map<String, Object> map, StringWriter resultStr, Generator generator) throws Exception {
        // 配置对象
        Configuration configuration = freeMarkerConfigurer.getConfiguration();

        for (String templateName : GenConstants.ALL_PREFIX) {
            Template template = configuration.getTemplate(templateName + GenConstants.FTL_SUFFIX);
            // 数据;
            Map<String, Object> dataModel = DbUtil.getData(generator);

            // 渲染模板
            template.process(dataModel, resultStr);
            map.put(templateName, resultStr.toString());
            resultStr.flush();
        }


    }
}
