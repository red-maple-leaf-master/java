package top.oneyi.serverApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "one-server", url = "localhost:1011")
//@FeignClient(name = "one-server")
public interface TestApi {
    @GetMapping("/test/one")
    String list(@RequestParam("str") String str);

    @GetMapping("/test01/one")
    String list01(@RequestParam("i") Integer i);
}
