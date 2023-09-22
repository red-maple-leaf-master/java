package top.oneyi.generator.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import top.oneyi.generator.common.GenConstants;
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
     *
     * @return
     */
    @Override
    public Map<String, Object> preview() {

        // 创建输出对象
        Map<String,Object> map = new HashMap<>();

        // 输出为字符串
        StringWriter resultStr = new StringWriter();
        try{
            // 模块名
            String module = "generator";
            String tableName = "blade_role";

            List<String> templates = new ArrayList<>();
            templates.add(GenConstants.CONTROLLER);
            templates.add(GenConstants.DOMAIN);
            templates.add(GenConstants.DTO);
            templates.add(GenConstants.MAPPER);
            templates.add(GenConstants.SERVICE);
            templates.add(GenConstants.SERVICE_IMPL);
            // 设置数据
            setModelData(map,resultStr,module,tableName,templates);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭流
            try {
                resultStr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 设置返回对象的值
     * @param map 返回的对象
     * @param resultStr 保存模板转换之后的对象
     * @param module 模块名字
     * @param tableName 表名字
     * @param templateNames 需要生成的文件名集合
     */
    private void setModelData(
                              Map<String, Object> map,
                              StringWriter resultStr,
                              String module,
                              String tableName,
                              List<String> templateNames) throws Exception {
        // 配置对象
        Configuration configuration = freeMarkerConfigurer.getConfiguration();

        for (String templateName : templateNames) {
            Template template = configuration.getTemplate(templateName+GenConstants.FTL_SUFFIX);
            // 数据;
            Map<String, Object> dataModel = DbUtil.getData(module,tableName);

            // 渲染模板
            template.process(dataModel, resultStr);
            map.put(templateName,resultStr.toString());
            resultStr.flush();
        }


    }
}
