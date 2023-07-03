package top.oneyi;


import io.github.admin4j.http.core.Pair;
import io.github.admin4j.http.util.HttpUtil;
import okhttp3.Response;
import org.junit.Test;


public class HttpTest {


    @Test
    public void test01() {
        Response response = HttpUtil.get("https://baidu.com", Pair.of("q", "okhttp"));
        System.out.println("response = " + response);
    }

    @Test
    public void test02() {
        String url = "https://gw.api.taobao.com/router/rest";
        String body = "url:https://s.click.taobao.com/t?e=m%3D2%26s%3DczETaBNerYUcQipKwQzePOeEDrYVVa64Dne87AjQPk9yINtkUhsv0IVFef3PjDfGOEKr7KrEFSe4AaQXFbYQsn%2FE2biKqiliYrWpJ0oQH%2BZu19uY3Wew%2FQ%2FPCNv2lOROgvUNyyJS1KL3%2BWMkG3VUs5V1r6G5FwrwwS2j3qu4zkdSWUn%2Btt4ohTbkvtSt0EFgcSpj5qSCmbA%3D&scm=null&pvid=null&app_pvid=59590_33.4.32.24_687_1656832372727&ptl=floorId%3A17741&originalFloorId%3A17741&app_pvid%3A59590_33.4.32.24_687_1656832372727&union_lens=lensId%3APUB%401656832370%40ded45bcf-5b47-46fe-98ea-b672ce2a28ae_664777139012%40024kNK2clP3axlxlzC2yO8eN";
        Response response = HttpUtil.post(url, body);
        System.out.println("response = " + response);

    }
}
