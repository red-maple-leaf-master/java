package top.oneyi.api.config;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableSwaggerBootstrapUI
public class SwaggerAutoConfiguration {

    // 接口不分组
    // http://localhost:1010/doc.html
    // Docket表示接口文档，用于封装接口文档相关信息（如记录扫描哪些包、文档名字、文档信息等）
    @Bean
    public Docket createRestApi1() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                // groupName : 接口文档组名字
                .apiInfo(apiInfo())
                .select()
                // basePackage 表示扫描那个包
                .apis(RequestHandlerSelectors.basePackage("top.oneyi.api.controller"))
                .build();
        return docket;
    }


    // 用户接口组
//    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                // groupName : 接口文档组名字
                .apiInfo(apiInfo()).groupName("用户接口组")
                .select()
                // basePackage 表示扫描那个包
                .apis(RequestHandlerSelectors.basePackage("top.oneyi.api.controller.user"))
                .build();
        return docket;
    }

    // 菜单接口组
//    @Bean
    public Docket createRestApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).groupName("菜单接口组")
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("top.oneyi.api.controller.menu"))
                .build();
        return docket;
    }


    //构建 api文档的详细信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("API接口文档")
                //创建人
                .contact(new Contact("红枫叶主", "http://www.oneyi.top", "1362187574@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}
