package top.one.easyjdbc.core.config;

import org.springframework.context.annotation.Configuration;
import top.one.easyjdbc.core.util.DBUtil;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/28/14:10
 */
@Configuration
public class JdbcTemplate {

    @Resource
    private JDBCConfig jdbcConfig;

    @Resource
    private DBUtil DbUtil;
    /**
     * 查到的数据集转换成指定实体类
     * @param rs 数据集
     * @param obj 返回的转换好的数据集
     * @param <T> 类型
     * @return
     */
    public <T> ArrayList<T> selectEntityList(ResultSet rs, Class<T> obj) {
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
                    name = DbUtil.lineToHump(name);
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
                    } else if (type.isAssignableFrom(java.sql.Date.class)) {
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
