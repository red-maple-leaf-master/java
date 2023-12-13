package top.oneyi.generator;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


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
     * @throws Exception 异常抛出
     */
    @Test
    public void test01() throws Exception {

        StopWatch stopWatch = new StopWatch();

        List<User> userList = generateUser(1000000);
        log.info("生成用户记录总数: "+ userList.size());

        String sql = "insert into user(nick_name,age) values (?,?)";
        Connection conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (User user1 : userList) {
            pstmt.setString(1,user1.getNickName());
            pstmt.setInt(2,user1.getAge());
            pstmt.addBatch();
        }
        stopWatch.start();
        pstmt.executeBatch();
        conn.commit();
        stopWatch.stop();
        conn.close();

        System.out.println("使用原生jdbc插入100万条数据总耗时:  "+stopWatch.getTotalTimeMillis() + " ms");
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
}
