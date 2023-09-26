package top.one.easyjdbc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.one.easyjdbc.core.config.JDBCConfig;
import top.one.easyjdbc.core.util.DBUtil;
import top.one.easyjdbc.entity.Goods;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EasyJdbcApplicationTests {

    @Resource
    private JDBCConfig jdbcConfig;

    @Resource
    private DBUtil dbUtil;

    @Test
    void contextLoads() throws SQLException {
        Connection connection = jdbcConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from goods");

        Class<Goods> clazz = Goods.class;

        List<Object> list = new ArrayList<>();
        Object obj = null;
        try {
            while (resultSet.next()) {
                // 创建一个clazz对象实例并将其赋给要返回的那个返回值。
                obj = clazz.newInstance();
                // 获取结果集的数据源
                ResultSetMetaData rsmeta = resultSet.getMetaData();

                // 获取结果集中的字段数
                int count = rsmeta.getColumnCount();

                // 循环取出个字段的名字以及他们的值并将其作为值赋给对应的实体对象的属性
                for (int i = 0; i < count; i++) {
                    // 获取字段名
                    String name = rsmeta.getColumnName(i + 1);
                    // 下划线转小驼峰
                    String entityName = dbUtil.lineToHump(name);
                    // 利用反射将结果集中的字段名与实体对象中的属性名相对应，由于
                    // 对象的属性都是私有的所以要想访问必须加上getDeclaredField(name)和
                    Field f = obj.getClass().getDeclaredField(entityName);
                    f.setAccessible(true);
                    // 将结果集中的值赋给相应的对象实体的属性
                    f.set(obj, resultSet.getObject(name));
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("list = " + list);
    }

    @Test
    void test() throws SQLException {

        Connection connection = jdbcConfig.getConnection();
        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from goods");
//        ArrayList<Goods> goods = putResult(resultSet, Goods.class);

    }


    public <T> ArrayList<T> putResult(ResultSet rs, Class<T> obj) {
        try {
            ArrayList<T> arrayList = new ArrayList<T>();
            ResultSetMetaData metaData = rs.getMetaData();
            //获取总列数,确定为对象赋值遍历次数
            int count = metaData.getColumnCount();
            while (rs.next()) {
                // 创建对象实例
                T newInstance = obj.newInstance();
                // 开始为一个对象赋值
                for (int i = 1; i <= count; i++) {

                    // 给对象的某个属性赋值
                    String name = metaData.getColumnName(i).toLowerCase();

                    // 改变列名格式成java命名格式
                    name = dbUtil.lineToHump(name);
                    // 首字母大写
                    String substring = name.substring(0, 1);
                    String replace = name.replaceFirst(substring, substring.toUpperCase());

                    // 获取字段类型
                    Class<?> type = obj.getDeclaredField(name).getType();
                    Method method = obj.getMethod("set" + replace, type);

                    //判断读取数据的类型
                    if (type.isAssignableFrom(String.class)) {
                        method.invoke(newInstance, rs.getString(i));
                    } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
                        method.invoke(newInstance, rs.getInt(i));
                    } else if (type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class)) {
                        method.invoke(newInstance, rs.getBoolean(i));
                    } else if (type.isAssignableFrom(Date.class)) {
                        method.invoke(newInstance, rs.getDate(i));
                    } else if (type.isAssignableFrom(BigDecimal.class)) {
                        method.invoke(newInstance, rs.getBigDecimal(i));
                    }
                }
                arrayList.add(newInstance);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
