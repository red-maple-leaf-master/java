package top.oneyi.generator;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;


import javax.annotation.Resource;
import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/19/17:56
 */

@Log
@SpringBootTest
public class test {


    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * 原生JDBC插入 1000 万条数据
     *
     * @throws Exception 异常抛出
     */
    @Test
    public void test01() throws Exception {

        StopWatch stopWatch = new StopWatch();

        List<User> userList = generateUser(1000000);
        log.info("生成用户记录总数: " + userList.size());
        // 将list分成十份
        int chunkSize = userList.size() / 10;
        Thread[] threads = new Thread[10];
        // 创建线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        stopWatch.start();

        for (int i = 0; i < 10; i++) {

            List<User> chunk = userList.subList(i * chunkSize, (i + 1) * chunkSize);
            threads[i] = new Thread(() -> {
                try {
                    extracted(chunk);
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            });
            log.info("开启线程: " + threads[i].getName());
            threads[i].start();

     /*       Runnable worker = () -> {
                try {
                    boolean success = extracted(chunk);
                    if (!success) {
                        throw new RuntimeException("数据插入失败");
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            };*/
            // 将线程放到线程池
//            executorService.execute(worker);

        }
        // 等待线程执行完毕
        for (Thread thread : threads) {
            thread.join();
        }
        // extracted(stopWatch, userList);
        // 关闭线程池
//        executorService.shutdown();
//        while (!executorService.isTerminated()) {}
        stopWatch.stop();
        System.out.println("使用原生jdbc插入100万条数据总耗时:  " + stopWatch.getTotalTimeMillis() + " ms");
    }

    /**
     * 向数据库插入数据
     *
     * @param userList 需要插入的用户集合
     * @throws SQLException 抛出的异常
     */
    public void extracted(List<User> userList) throws SQLException {
        String sql = "insert into user(nick_name,age,create_time,update_time,remark,version) values (?,?,?,?,?,?)";
        Connection conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 将list集合插入数据库
        // 批量插入
        for (User user : userList) {
            pstmt.setString(1, user.getNickName());
            pstmt.setInt(2, user.getAge());
            pstmt.setString(3, user.getCreateTime());
            pstmt.setString(4, user.getUpdateTime());
            pstmt.setString(5, user.getRemark());
            pstmt.setInt(6, user.getVersion());
            pstmt.addBatch();
        }


        pstmt.executeBatch();
        conn.commit();
        conn.close();
    }


    private List<User> generateUser(int count) {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setNickName("萌新程序员小哥_" + i);
            user.setAge(i);
            user.setCreateTime(new Date().toString());
            user.setUpdateTime(new Date().toString());
            user.setVersion(1);
            user.setRemark("备注");
            list.add(user);
        }

        return list;
    }

    @Test
    public void BigData1000wTest() throws Exception {
        //   通过JDBCUtil工具类获取数据库连接对象
        //Connection conn = BaseDao.getConn("million-test", "root", "root");
        //   StreamUtil是已经封装好的使用流读取文件的工具类
        //List<String> list = StreamUtil.readingLineFormTextFile(new File("D://milliondatatest//test(500W).csv"));
        String sql = "insert into mysqltest values(?,?,?,?)";   //  定义要导入数据的sql,无需主键将第一个?设置为null
        long start = System.currentTimeMillis();   //   获取方法开始执行前的时间（单位：毫秒）
        //  调用刚刚封装好的工具类
        //DataImport.dispose(conn, list, 0, true, 1000000, sql);
        long end = System.currentTimeMillis();     //   获取方法执行结束后的时间
        //   相减即可得到插入所有数据的耗时   秒=毫秒/1000;
        // System.out.println("成功导入" + list.size() + "条数据！！时长：" + (end - start) / 1000 + "秒");
    }

    /**
     * 查询数据
     */
    @Test
    public void selectTest() throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String sql = "select * from user";
        Connection conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 获取结果集
        ResultSet rs = pstmt.executeQuery();
        // 将数据封装成use对象,添加到list集合中
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setNickName(rs.getString("nick_name"));
            user.setAge(rs.getInt("age"));
            user.setCreateTime(rs.getString("create_time"));
            user.setUpdateTime(rs.getString("update_time"));
            user.setRemark(rs.getString("remark"));
            user.setVersion(rs.getInt("version"));
            userList.add(user);
        }
        System.out.println(userList.size());

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }


    /**
     * 写一个往数据库插入一百万数据的测试类
     */
    @Test
    public void test02() throws InterruptedException, SQLException {
        StopWatch stopWatch = new StopWatch();

        List<User> userList = generateUser(1000000);
        log.info("生成用户记录总数: " + userList.size());
        stopWatch.start();
        extracted(userList);
        stopWatch.stop();
        System.out.println("使用原生jdbc插入100万条数据总耗时:  " + stopWatch.getTotalTimeMillis() + " ms");
    }


    @Test
    public void test03() throws InterruptedException, SQLException {
        String s = User.generateInsertSql();
        System.out.println("s = " + s);
    }



    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test04() {
        String url = "https://weather.cma.cn/api/weather/view?stationid=58238";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println("forEntity = " + forEntity.getBody());
    }
}
