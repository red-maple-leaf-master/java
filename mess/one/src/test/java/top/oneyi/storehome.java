package top.oneyi;

import net.minidev.json.JSONUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import top.oneyi.pojo.po.SysUser;
import top.oneyi.util.MyCsvFileUtil;


import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipOutputStream;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

public class storehome {
    private Map<String, String> buildAccessory(String type, int num, String fuJian) {
        Map<String, String> map = new HashMap<>();
        // 循环标志位 因为是按顺序进行存储的,所以可以定制标志位 来进行减少循环
        int w = 0;
        int count = 0;
        String[] split = fuJian.split(",");
        for (int i = 1; i <= num; i++) {
            // 构建第几个文件名字
            String files = type + String.format("%02d", i);
            for (int j = 0; j < split.length; j++) {
                count++;
                // 如果附件存在就设置在map中
                if (split[w].contains(files)) {
                    if (map.get(files) == null) {
                        map.put(files, split[w]);
                    } else {
                        String str = map.get(files) + "," + split[w];
                        map.put(files, str);
                    }
                    w++;
                } else {
                    // 由于数组是顺序存储,不存在说明没有了 后面也不需要循环了 停止for循环
                    break;
                }
                //防止数组越界
                if (w == split.length) {
                    break;
                }
            }
        }
        System.out.println("一共循环了 ==" + count + "次");
        return map;
    }

    @Test
    public void Test01() {
        String fujian = "http://114.67.96.145:20178/customerData/business01/c715cfa4-9b39-445a-a659-f28c860c404a_身份证国徽.jpg," +
                "http://114.67.96.145:20178/customerData/business01/c715cfa4-9b39-445a-a659-f28c860c404a_身份证国徽2.jpg," +
                "http://114.67.96.145:20178/customerData/business02/fd74c642-0dd8-4449-aa19-b82c6b6166fa_一般纳税人证明.jpg," +
                "http://114.67.96.145:20178/customerData/business03/ec2ff970-a401-4902-a10b-7a6890613713_营业执照.jpg," +
                "http://114.67.96.145:20178/customerData/business04/345f51cc-1bae-4905-9e33-793fffb6d8b4_开票信息.jpg";
        Map<String, String> business = this.buildAccessory("business", 4, fujian);
        System.out.println(business);
    }

    @Test
    public void test02() {
        String pallcode = "12,14,13,15,16,18,17";
        String pallcode02 = "14,15";

        ArrayList<String> strings = new ArrayList<>(Arrays.asList(pallcode.split(",")));
        ArrayList<String> strings02 = new ArrayList<>(Arrays.asList(pallcode02.split(",")));
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            for (String s : strings02) {
                if (s.equals(next)) {
                    iterator.remove();
                }
            }
        }
     /*   String join = String.join(",", strings);
        System.out.println("join = " + join);*/

        StringJoiner joiner = new StringJoiner(",");
        for (String string : strings) {
            joiner.add(string);
        }
        String s = joiner.toString();
        System.out.println("s = " + s);
    }

    private static List<String> transferArrayToList(String str) {
        return new ArrayList<>(Arrays.asList(str.split(",")));
    }

    @Test
    public void test03() {
        StringJoiner sj = new StringJoiner(":", "[", "]");
        sj.add("George").add("Sally").add("Fred");
        String desiredString = sj.toString();
        System.out.println("desiredString = " + desiredString);

        StringJoiner sj2 = new StringJoiner(",", "(", ")");
        sj2.add("one");
        StringJoiner merge = sj2.merge(sj);
        System.out.println("merge.toString() = " + merge.toString());
    }


    @Test
    public void test04() {
        int n = 100;
        double pow = Math.pow(2, n);
        for (int i = 0; i < pow; i++) {
            System.out.println("我执行了" + i + "次");
        }
    }

    @Test
    public void test05() {

        String nums = "202302280012";
        int num = (Integer.parseInt(nums) + 1);
        System.out.println("num = " + num);
        nums = String.valueOf(num);
        System.out.println("nums111111111111 = " + nums);


    }

    @Test
    public void test06() throws IOException {
        String path = "E:\\Desktop\\身份证人像.jpg";
        String downPath = "E:\\Desktop\\one\\java\\one\\1.jpg";
        FileOutputStream os = new FileOutputStream(new File(downPath));
        FileInputStream is = new FileInputStream(new File(path));
        byte[] len = new byte[1024 * 8];
        while ((is.read(len) != -1)) {
            os.write(len);
            os.flush();
        }
    }

    String fileName = "E:\\Desktop\\wan001.csv";

    @Test
    public void test07() {
        List<SysUser> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            SysUser user = new SysUser();
            user.setId((long) i);
            user.setIsShow(1);
            user.setRealName("中湖");
            user.setUserPassword("132465");
            user.setUpdateTime(new Date());
            user.setCreateTime(new Date());
            list.add(user);
        }
        // 创建表格行标题
        String tableNames = MyCsvFileUtil.buildCsvFileTableNames(list);
        // 创建文件
        MyCsvFileUtil.writeFile(fileName, tableNames);
        // 写入数据
//        String contentBody = MyCsvFileUtil.buildCsvFileBodyMap(list);
        List<String> bodyList = MyCsvFileUtil.buildCsvFileBodyMapToList(list, tableNames);
        // 调用方法生成
//        MyCsvFileUtil.writeFile(fileName,contentBody);
        MyCsvFileUtil.createXlsx(bodyList);
    }


}
