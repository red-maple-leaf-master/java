package top.oneyi.serverApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "one-server1",url = "localhost:1010")
//@FeignClient(name = "one-server")
public interface TestApi {
    @GetMapping("/test/one")
    String list(@RequestParam("str") String str);
}
