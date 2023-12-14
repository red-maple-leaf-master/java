package top.oneyi.generator;


import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created with IntelliJ IDEA.
 * 经过AI优化过的代码
 *
 * @Author: wanyi
 * @Date: 2023/12/14/9:14
 */
@Log
@SpringBootTest
public class optimizeCode {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * 原生JDBC插入 1000 万条数据
     * @throws Exception 异常抛出
     */
    @Test
    public void test01() throws Exception {

        StopWatch stopWatch = new StopWatch();

        List<User> userList = generateUser(1000000);
        log.info("生成用户记录总数: "+ userList.size());
        // 将list分成十份
        int chunkSize = userList.size() / 10;
        stopWatch.start();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // jdk自带的异步类
        CompletableFuture<?>[] futures = new CompletableFuture<?>[10];
        for (int i = 0; i < 10; i++) {
            List<User> chunk = userList.subList(i * chunkSize, (i +1 ) * chunkSize);
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    extracted(chunk);
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }, executorService);
            futures[i] = future;
        }

        CompletableFuture.allOf(futures).join();

        stopWatch.stop();
        System.out.println("使用原生jdbc插入100万条数据总耗时:  "+stopWatch.getTotalTimeMillis() + " ms");
    }

    @Transactional
    public boolean extracted(List<User> userList) throws SQLException {
        String sql = "insert into user(nick_name,age) values (?,?)";
        Connection conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (User user1 : userList) {
            pstmt.setString(1,user1.getNickName());
            pstmt.setInt(2,user1.getAge());
            pstmt.addBatch();
        }

        pstmt.executeBatch();
        conn.commit();
        conn.close();
        return true;
    }

    private List<User> generateUser(int count){
        List<User> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setNickName("萌新程序员小哥_" + i);
            user.setAge(i);
            list.add(user);
        }

        return list;
    }
}
