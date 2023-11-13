package top.oneyi.TestNG.test;


import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;



public class DemoTest {


    @Test
    public void test() {
        System.out.println("我是业务方法");
    }

    @Test
    public void test01() throws IOException {
        // get方式访问接口
        OkHttpClient clientWith30sTimeout = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        String url="https://api.apiopen.top/api/sentences";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = clientWith30sTimeout.newCall(request).execute();
        System.out.println(response.body().string());
    }

    // POST 方式访问接口
    @Test
    public void test02() throws IOException {

      MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String url="https://api.qqsuu.cn/api/dm-jiejiari";
        String json ="{\"date\":\"2023-05-07\"}";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, String.valueOf(JSONObject.parseObject(json)));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    // post方式提交键值对
    @Test
    public void test03() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url="https://api.qqsuu.cn/api/dm-jiejiari";


        RequestBody formBody = new FormBody.Builder()
                .add("date", "2023-05-07")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            System.out.println("Unexpected code " + response);
        }
    }
    // Post方式提交流
    @Test
    public void test04() throws IOException {
        MediaType MEDIA_TYPE_MARKDOWN
                = MediaType.parse("text/x-markdown; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new RequestBody() {
            @Override
            public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8("Numbers\n");
                bufferedSink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    bufferedSink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }

            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }
        };

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }
    //Post方式提交分块请求，可以上传文件
    @Test
    public void test05() throws IOException {
        String IMGUR_CLIENT_ID = "...";
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("image", "logo-square.png",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }

}
