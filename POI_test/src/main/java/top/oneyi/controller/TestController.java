package top.oneyi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {
    /**
     * 接收文件
     * @param file
     */
    @GetMapping("/addFile")
    public void sendMultipartFile(MultipartFile file) throws IOException {
        System.out.println("file.getName() = " + file.getName()); // 就是参数的名字
        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename()); // 文件的全名
        System.out.println("file.getContentType() = " + file.getContentType()); // 文件类型
        System.out.println("file.getSize() = " + file.getSize()); // 文件字节数
        System.out.println("file.getBytes() = " + file.getBytes()); // 文件转换成 字节数组
        System.out.println("file.getInputStream() = " + file.getInputStream()); // 文件转换成 输入流
        //   transferTo是复制file文件到指定位置(比如D盘下的某个位置),不然程序执行完,文件就会消失,程序运行时,临时存储在temp这个文件夹中
        // 俩个参数 File file ; Path path
    }
    /**
     * 保存图片文件到本地
     * @param file
     */
    @GetMapping("/saveFile")
    public String  save(MultipartFile file) throws IOException {
//        String files = ClassUtils.getDefaultClassLoader().getResource("files").getPath();
        String files = TestController.class.getClassLoader().getResource("static").getPath();
        String filename = file.getOriginalFilename();
        String url_path = files + File.separator + "files"+ File.separator +filename;
        // 访问路径=静态资源路径+文件目录路径
        String visitPath = "static/files/" + filename;
        System.out.println("url_path = " + url_path);
        File saveFile = new File(url_path);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
        }

        return visitPath;
    }
}
