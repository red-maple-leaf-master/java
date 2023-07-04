package top.oneyi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping
    public String test(HttpServletResponse response) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\13621\\Desktop\\test.docx");

        response.addHeader("Content-Disposition",
                "attachment;filename=" + new String("out.docx".getBytes(StandardCharsets.UTF_8),
                        StandardCharsets.ISO_8859_1));
        ServletOutputStream outputStream = response.getOutputStream();
        int len=0;
        while((len=fileInputStream.read())!=-1){
            outputStream.write(len);
        }
        System.out.println("controller被调用");
        return "成功 调用";
    }

}
