package top.oneyi.api.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.oneyi.api.common.entity.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {


    /**
     *   // @ApiOperation用在请求的方法上，说明方法的用途、作用
     * @return
     */
    @GetMapping("/getUsers")
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户信息")
    public List<User> getAllUsers(){
        User user = new User();
        user.setId(100);
        user.setName("itcast");
        user.setAge(20);
        user.setAddress("bj");
        List<User> list = new ArrayList<>();
        list.add(user);
        return list;
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增用户",notes = "新增用户信息")
    public String save(@RequestBody User user){
        return "ok";
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新用户", notes = "更新用户信息")
    public String update(@RequestBody User  user){
        return "update";
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户", notes = "删除用户信息")
    public String delete(int id){
        return "OK";
    }

    /**
     *    // @ApiImplicitParams用在请求的方法上，表示一组参数说明,此处表示对pageNum和pageSize这两个参数进行说明
     *    // @ApiImplicitParam用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",required = true,type = "Integer"),
            @ApiImplicitParam(name="pageSize",value = "每页条数",required = true,type = "Integer")
    })
    @ApiOperation(value = "分页查询用户信息")
    @GetMapping(value = "page/{pageNum}/{pageSize}")
    public String findByPage(@PathVariable Integer pageNum,
                             @PathVariable Integer pageSize) {
        return "OK";
    }
}
