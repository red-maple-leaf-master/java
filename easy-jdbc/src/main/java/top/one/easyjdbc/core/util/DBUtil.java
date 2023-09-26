package top.one.easyjdbc.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import top.one.easyjdbc.core.config.JDBCConfig;
import top.one.easyjdbc.core.entity.Field;
import top.one.easyjdbc.core.entity.Generator;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/26/16:22
 */
@Configuration
public class DBUtil {

    @Resource
    private JDBCConfig jdbcConfig;

    /**
     * 正则表达式规则: 匹配: _字母
     */
    private static final Pattern PATTERN = Pattern.compile("_(\\w)");

    /**
     * 获取表的中文名称
     *
     * @param tableName 表名
     * @return 返回表的注释
     */
    public  String getTableComment(String tableName) throws SQLException {
        Connection conn = jdbcConfig.getConnection();
        Statement state = conn.createStatement();
        ResultSet res = state
                .executeQuery("select table_comment from information_schema.TABLES where TABLE_NAME = '"
                        + tableName + "';");
        String tableNameCh = "";
        while (res.next()) {
            tableNameCh = res.getString("table_comment");
        }
        res.close();
        state.close();
        conn.close();
        System.out.println("表名 = " + tableNameCh);
        return tableNameCh;
    }

    /**
     * 获取所有列信息
     *
     * @param tableName 表名
     * @return 返回属性的Field集合
     * @throws Exception 抛出异常
     */
    public List<Field> getColumnByTableName(String tableName) throws Exception {
        List<Field> fieldList = new ArrayList<>();
        // 获取sql连接
        Connection conn = jdbcConfig.getConnection();
        Statement state = conn.createStatement();
        // 获取所有数据库字段
        ResultSet res = state.executeQuery("show full columns from " + tableName);
        while (res.next()) {
            String name = res.getString("Field");
            String type = res.getString("Type");
            String comment = res.getString("Comment");
            String nullAble = res.getString("Null");

            Field field = new Field();
            field.setName(name); // 数据库字段
            field.setNameHump(lineToHump(name)); // 小驼峰
            field.setNameBigHump(lineToBigHump(name)); // 大驼峰
            field.setComment(comment); // 字段注释
            field.setJavaType(sqlTypeToJavaType(type)); // 类型
            field.setType(type); // 数据库类型
            if (comment.contains("|")) {
                field.setNameCn(comment.substring(0, comment.indexOf("|")));
            } else {
                field.setNameCn(comment);
            }
            field.setNullAble("YES".equals(nullAble)); // 是否非空
            if (type.toUpperCase().contains("VARCHAR")) {
                String lengthStr = type.substring(type.indexOf("(") + 1, type.length() - 1);
                field.setLength(Integer.valueOf(lengthStr)); // 字段长度
            } else {
                field.setLength(0);
            }
            fieldList.add(field);
        }
        res.close();
        state.close();
        conn.close();
        System.out.println("列信息 = " + fieldList);
        return fieldList;
    }

    /**
     * 下划线转小驼峰
     *
     * @param str 字符串
     * @return 小驼峰
     */
    public  String lineToHump(String str) {
        Matcher matcher = PATTERN.matcher(str.toLowerCase());
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转大驼峰
     *
     * @param str 字符串
     * @return 大驼峰
     */
    public  String lineToBigHump(String str) {
        String s = lineToHump(str);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 数据库类型转为 Java 类型
     *
     * @param sqlType 数据库类型
     * @return 返回 Java 类型
     */
    public  String sqlTypeToJavaType(String sqlType) {
        final String varchar = "VARCHAR";
        final String chr = "CHAR";
        final String text = "TEXT";
        final String datetime = "DATETIME";
        final String integer = "INT";
        final String aLong = "LONG";
        final String decimal = "DECIMAL";
        if (sqlType.toUpperCase().contains(varchar)
                || sqlType.toUpperCase().contains(chr)
                || sqlType.toUpperCase().contains(text)) {
            return "String";
        } else if (sqlType.toUpperCase().contains(datetime)) {
            return "Date";
        } else if (sqlType.toUpperCase().contains(integer)) {
            return "Integer";
        } else if (sqlType.toUpperCase().contains(aLong)) {
            return "Long";
        } else if (sqlType.toUpperCase().contains(decimal)) {
            return "BigDecimal";
        } else {
            return "String";
        }
    }
    /**
     *  获取字段数据
     * @param generator 生成代码所需作者包名等数据
     * @return 字段数据
     * @throws Exception
     */
    public Map<String,Object> getData(Generator generator) throws Exception {

        // 获取大驼峰类名
        String bigDoMain = lineToBigHump(generator.getTableName());
        //获取小驼峰类名
        String domain = lineToHump(generator.getTableName());
        //表的中文注释
        String tableNameCn = getTableComment(generator.getTableName());

        //获取数据库表的属性列表
        List<Field> fieldList = getColumnByTableName(generator.getTableName());
        //获取要导入的Java类型
        Set<String> typeSet = getJavaTypes(fieldList);
        // 格式化时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        //放到 Map 集合中供 freemarker 使用
        Map<String, Object> map = new HashMap<>(10);
        map.put("Domain", bigDoMain);
        map.put("package", generator.getPackageName());
        map.put("author", generator.getFunctionAuthor());
        map.put("date", simpleDateFormat.format(new Date()));
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("module", generator.getModuleName());
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);
        return map;
    }


    /**
     * 将Java数据类型保存到 set 中
     * @param fieldList 字段集合
     * @return
     */
    public  Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (Field field : fieldList) {
            set.add(field.getJavaType());
        }
        return set;
    }

    /**
     * 查到的数据集转换成指定实体类
     * @param rs 数据集
     * @param obj 返回的转换好的数据集
     * @param <T> 类型
     * @return
     */
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
                    name = lineToHump(name);
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
