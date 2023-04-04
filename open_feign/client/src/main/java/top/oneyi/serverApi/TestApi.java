package top.oneyi.serverApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "feignTest", url = "localhost:1011/test")
public interface TestApi {
    @GetMapping("/one")
    String list(@RequestParam("str") String str);

}
