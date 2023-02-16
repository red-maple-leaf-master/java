package top.one.controller;
 
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
@Api(tags = "用户测试登录")
public class EsbController {
    
    @ApiOperation(value = "用户登录",notes = "用户登录测试")
    @RequestMapping(value = "/esb",method= RequestMethod.GET)
    public String esb(){
        return "123";
    }
}