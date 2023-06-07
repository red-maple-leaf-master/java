package top.oneyi.api;

import com.deepoove.poi.XWPFTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.oneyi.api.entity.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest//加载 SpringBoot 启动类
public class wordTest {


    @Test
    public void test() throws Exception {
        Data data = new Data();
        data.setName("张三");
        data.setTitle("入学通知书");
        data.setStartTime(new Date().toString());
        XWPFTemplate template = XWPFTemplate.compile("template.docx").render(
//                new HashMap<String, Object>(){{
//                    put("title", "Hi, poi-tl Word模板引擎");
//                }}
                data
                );
        template.writeAndClose(new FileOutputStream("output.docx"));
    }



}
