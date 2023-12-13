package top.oneyi.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DataImport {
    //   参数一：数据库连接对象、
    //   参数二：流文件读取出的集合、
    //   参数三：从第几条数据开始读取，目的是排除表头、
    //   参数四：是否包含主键、
    //   参数五：每次批量执行添加数据的数量、
    //   参数六：sql语句
    public static void dispose(Connection conn, List<String> list, Integer startRows, boolean includePrimaryKey, Integer size, String sql) {
        try {
            conn.setAutoCommit(false);  //  设置事物手动提交
            PreparedStatement ps = conn.prepareStatement(sql);
            String[] split = null;
            if (includePrimaryKey) {    //  包含主键,只需判断一次
                for (int i = startRows; i < list.size(); i++) {
                    //  按逗号切割字符串，-1代表忽略数组长度，避免数组长度越界异常
                    split = list.get(i).split(",", -1);
                    /*下方代码产生警告提示的原因：同一项目中，有重复的代码块（idea很好的提示。但是这里无法将判断放在循环内，不然会多出百万次判断使程序缓慢）*/
                    for (int j = 0; j < split.length; j++) {   //  遍历刚刚获取的数组
                        //   对集合中的每条数据进行处理，将字符串中多出的引号去掉，避免录入数据库时因字段类型不匹配而导致的格式转换异常
                        ps.setObject(j + 1, split[j].replace("\"", ""));    //  循环赋值
                    }
                    ps.addBatch();   //  将所有数据转为一条sql
                    if (i % size == 0 && i != 0) {   //  如果i能整除size，即执行循环体
                        ps.executeBatch();           //  批量执行sql
                        conn.commit();               //  事物手动提交
                        conn.setAutoCommit(false);   //  重新设置事物为手动提交
                        ps = conn.prepareStatement(sql);   //  再次为ps对象赋值
                    }
                }
            } else {    //  不包含主键
                for (int i = startRows; i < list.size(); i++) {
                    String s = list.get(i);
                    //  将集合中的对象从第一个逗号切割，substring包头不包尾，因此此处需加1
                    split = s.substring(s.indexOf(",") + 1).split(",", -1);
                    for (int j = 0; j < split.length; j++) {
                        ps.setObject(j + 1, split[j].replace("\"", ""));
                    }
                    ps.addBatch();
                    if (i % size == 0 && i != 0) {
                        ps.executeBatch();
                        conn.commit();
                        conn.setAutoCommit(false);
                        ps = conn.prepareStatement(sql);
                    }
                }
            }
            ps.executeBatch();  //  循环外提交是因为可能会出现循环内条件不成立而未提交过的情况
            conn.commit();      //  提交事物，避免脏数据（事物太长也有弊端）
            ps.close();         //  关闭资源
            conn.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
