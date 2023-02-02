package top.oneyi;

import com.alibaba.excel.EasyExcel;
import io.lettuce.core.output.KeyStreamingChannel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.mapper.SysUserMapper;
import top.oneyi.mapper.WmOmNoticeIMapper;
import top.oneyi.pojo.po.DemoData;
import top.oneyi.pojo.po.SysUser;
import top.oneyi.pojo.po.WmOmNoticeI;

import javax.swing.text.Style;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = OneApplication.class)
public class testDemo01 {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private WmOmNoticeIMapper wmOmNoticeIMapper;

    @Test
    public void userTest01() {
       /* List<SysUser> sysUsers = sysUserMapper.selectAll();
        SysUser admin = sysUsers.get(0);
        admin.setStorehouseNum("-11");
        sysUserMapper.updateByPrimaryKey(admin);
        System.out.println("admin = " + admin);*/

       List<String> list = new ArrayList<>();
        String s = list.get(11);
        System.out.println("s = " + s);
    }

    // excel 输出路径
    String PATH = "E:\\Desktop";


    @Test
    public void ExcelOut() throws Exception {

        // 创建输入流  03版本使用 xls后缀(65536行)    07版本就是表格行数没有要求,后缀变为xlsx
        // 07版本 XSSFWorkbook  大文件使用这个,但是慢   SXSSFWorkbook 为升级版 需要清理临时文件 ((SXSSFWorkbook)workbook).dispose();
        FileOutputStream fos = new FileOutputStream(PATH +"\\poi03.xls");
       // List<WmOmNoticeI> wmOmNoticeIS = wmOmNoticeIMapper.selectAll();
        // 创建工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建工作表
        Sheet sheet = workbook.createSheet("出货单 ");
        // 创建第一行
        Row row = sheet.createRow(0);
        //创建单元格
        Cell cell11 = row.createCell(0);
        // 第一行第一个
        cell11.setCellValue("姓名");
        //创建单元格
        Cell cell12 = row.createCell(1);
        // 第一行第一个
        cell12.setCellValue("时间 ");
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("华文行楷");
        font.setFontHeightInPoints((short) 45);
        cellStyle.setFont(font);
        cell11.setCellStyle(cellStyle);

        // 第二行
        Row row1 = sheet.createRow(1);
        Cell cell21 = row1.createCell(0);
        Cell cell22 = row1.createCell(1);
        cell21.setCellValue("张三");
        cell22.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        CellStyle cellStyle1 = workbook.createCellStyle();
        Font font1 = workbook.createFont();
        font1.setFontName("仿宋");
        font1.setFontHeight((short) 34);
        cellStyle1.setFont(font1);
        row1.setRowStyle(cellStyle1);
        workbook.write(fos);
        fos.close();
        System.out.println("单元格输出完毕");
    }

/*    @Test
    public void outExel() throws Exception {
        // 文件 输出流
        FileOutputStream fos = new FileOutputStream(PATH +"\\收款单.xls");
        // 创建工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建工作表
        Sheet sheet = workbook.createSheet();
        // 创建行
        Row row01 = sheet.createRow(0);
        Row row02 = sheet.createRow(1);
        Row row03 = sheet.createRow(2);
        Row row04 = sheet.createRow(3);
        Row row05 = sheet.createRow(4);
        Row row06 = sheet.createRow(5);
        Row row07 = sheet.createRow(6);
        Row row08 = sheet.createRow(7);
        Row row09 = sheet.createRow(8);
        Row row10 = sheet.createRow(9);
        Row row11 = sheet.createRow(10);
        row01.setHeightInPoints(25);
        row02.setHeightInPoints(25);
        row03.setHeightInPoints(25);
        row04.setHeightInPoints(25);
        row05.setHeightInPoints(25);
        row06.setHeightInPoints(25);
        row07.setHeightInPoints(25);
        row08.setHeightInPoints(25);
        row09.setHeightInPoints(25);
        row10.setHeightInPoints(25);
        // 处理第一行
        Cell cell = row01.createCell(0);
        cell.setCellValue("收  款  收  据");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
        // 设置字体
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 15);
        font.setUnderline(FontFormatting.U_SINGLE); // 设置下划线
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);
        sheet.addMergedRegion(cellRangeAddress); // 合并第一行
        sheet.setColumnWidth(0,45 * 256); // 设置第一列的宽度
        sheet.setColumnWidth(4,20 * 256); // 设置第五列的宽度
        // 合并地六列的行
        CellStyle cellStyle02 = workbook.createCellStyle();
        Font font5 = workbook.createFont();
        font5.setFontHeightInPoints((short) 15);
        font5.setFontName("宋体");
        cellStyle02.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        cellStyle02.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 左右居中
        cellStyle02.setWrapText(true); // 开启多行显示
        cellStyle02.setFont(font5);
        CreationHelper creationHelper01 = workbook.getCreationHelper();
        Cell cell3 = row01.createCell(5);
        cell3.setCellValue(creationHelper01.createRichTextString("第\r\n一\r\n联\r\n︵\r\n存\r\n根\r\n︶"));
        CellRangeAddress cellRangeAddress3 = new CellRangeAddress(0, 10, 5, 5);
        sheet.addMergedRegion(cellRangeAddress3);
        cell3.setCellStyle(cellStyle02);
        // 处理第二行
        Cell cell1 = row02.createCell(0);
        cell1.setCellValue("单位:江西冠商业运营管理有限责任公司");
        CellStyle cellStyle4 = workbook.createCellStyle();
        cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        Cell cell2 = row02.createCell(3);
        cell2.setCellValue("日期:" + DateTime.now().toString("yyyy年MM月dd日"));
        CellRangeAddress cellRangeAddress1 = new CellRangeAddress(1, 1, 0, 2);
        sheet.addMergedRegion(cellRangeAddress1);
        CellRangeAddress cellRangeAddress2 = new CellRangeAddress(1, 1, 3, 4);
        sheet.addMergedRegion(cellRangeAddress2);
        cell1.setCellStyle(cellStyle4);
        cell2.setCellStyle(cellStyle4);
        //处理第三行
        Cell cell4 = row03.createCell(0);
        cell4.setCellValue("交款单位:");
        sheet.addMergedRegion(new CellRangeAddress(2,2,0,2));
        Cell cell5 = row03.createCell(3);
        cell5.setCellValue("交款单位:");
        sheet.addMergedRegion(new CellRangeAddress(2,2,3,4));
        // 处理第四行
        Cell cell6 = row04.createCell(0);
        Cell cell7 = row04.createCell(1);
        Cell cell8 = row04.createCell(2);
        Cell cell9 = row04.createCell(3);
        Cell cell10 = row04.createCell(4);
        cell6.setCellValue("收款内容");
        cell7.setCellValue("数量");
        cell8.setCellValue("单价");
        cell9.setCellValue("金额");
        cell10.setCellValue("备注");
        CellStyle cellStyle01 = workbook.createCellStyle();
        cellStyle01.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
        cellStyle01.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        Font font4 = workbook.createFont();
        font4.setFontName("宋体");
        font4.setFontHeightInPoints((short) 15);
        cellStyle01.setFont(font4);
        cell6.setCellStyle(cellStyle01);
        cell7.setCellStyle(cellStyle01);
        cell8.setCellStyle(cellStyle01);
        cell9.setCellStyle(cellStyle01);
        cell10.setCellStyle(cellStyle01);
        // 处理第五行
        Cell cell11 = row05.createCell(0);
        Cell cell12 = row05.createCell(1);
        Cell cell13 = row05.createCell(2);
        Cell cell14 = row05.createCell(3);
        Cell cell15 = row05.createCell(4);
        // 处理第六行
        Cell cell16 = row06.createCell(0);
        Cell cell17 = row06.createCell(1);
        Cell cell18 = row06.createCell(2);
        Cell cell19 = row06.createCell(3);
        Cell cell20 = row06.createCell(4);

        // 处理第七行
        Cell cel21 = row07.createCell(0);
        Cell cel22 = row07.createCell(1);
        Cell cel23 = row07.createCell(2);
        Cell cel24 = row07.createCell(3);
        Cell cell25 = row07.createCell(4);
        // 处理第八行
        Cell cell21 = row08.createCell(0);
        cell21.setCellValue("合计");
        CellStyle cellStyle5 = workbook.createCellStyle();
        Font font6 = workbook.createFont();
        font6.setFontName("宋体");
        font6.setFontHeightInPoints((short) 15);
        cellStyle5.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        cellStyle5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 上下居中
        cellStyle5.setFont(font6);
        cell21.setCellStyle(cellStyle5);
        // 处理第九行
        Cell cell22 = row09.createCell(0);
        cell22.setCellValue("人民币( 大写 ):");
        Cell cell23 = row09.createCell(2);
        cell23.setCellValue("( 小写 ):");
        CellStyle cellStyle3 = workbook.createCellStyle();
        Font font3 = workbook.createFont();
        font3.setFontName("宋体");
        font3.setFontHeightInPoints((short) 15);
        cellStyle3.setFont(font3);
        cell4.setCellStyle(cellStyle3);
        cell5.setCellStyle(cellStyle3);
        cell2.setCellStyle(cellStyle3);
        cell1.setCellStyle(cellStyle3);
        cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cell22.setCellStyle(cellStyle3);
        cell23.setCellStyle(cellStyle3);

        sheet.addMergedRegion(new CellRangeAddress(8,8,0,1));
        sheet.addMergedRegion(new CellRangeAddress(8,8,2,4));
        // 处理第十行
        Cell cell24 = row10.createCell(0);
        cell24.setCellValue("备注:");
        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font2 = workbook.createFont();
        font2.setFontHeightInPoints((short) 15);
        font2.setFontName("宋体");
        cellStyle2.setFont(font2);
        cell24.setCellStyle(cellStyle2);
        sheet.addMergedRegion(new CellRangeAddress(9,9,0,4));
        // 处理第十一行
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setVerticalAlignment(CellStyle.ALIGN_LEFT);
        cellStyle1.setWrapText(true);
        Font font1 = workbook.createFont();
        font1.setFontName("宋体"); // 设置字体名称
        font1.setFontHeightInPoints((short) 15);// 设置字号
        cellStyle1.setFont(font1);
        CreationHelper creationHelper = workbook.getCreationHelper();
        Cell cell26 = row11.createCell(0);
        cell26.setCellStyle(cellStyle1);
        cell26.setCellValue(creationHelper.createRichTextString("单\r\n位\r\n盖\r\n章"));
        Cell cell27 = row11.createCell(1);
        cell27.setCellStyle(cellStyle1);
        cell27.setCellValue(creationHelper.createRichTextString("复\r\n核"));
        Cell cell28 = row11.createCell(2);
        cell28.setCellStyle(cellStyle1);
        cell28.setCellValue(creationHelper.createRichTextString("出\r\n纳"));
        Cell cell29 = row11.createCell(4);
        cell29.setCellStyle(cellStyle1);
        cell29.setCellValue(creationHelper.createRichTextString("收\r\n款\r\n人"));
        sheet.addMergedRegion(new CellRangeAddress(8,8,2,3));
        sheet.addMergedRegion(new CellRangeAddress(10,10,2,3));
        workbook.write(fos);
        fos.close();

    }*/

    /**
     * 设置格式
     * @param cell3
     * @param workbook
     */
    private void initFormat(Cell cell3, Workbook workbook) {
        Font font = workbook.createFont();
        CellStyle cellStyle = workbook.createCellStyle();
        font.setFontName("宋体"); // 设置字体名称
        font.setFontHeightInPoints((short) 10);// 设置字号
        cellStyle.setFont(font);
        cell3.setCellStyle(cellStyle);
    }


/*    @Test
    public void EasyExcel(){
        // 模拟从数据库读取到的数据
        java.util.List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        String fileNaem = PATH + "\\EasyTest.xlsx";
        EasyExcel.write(fileNaem,DemoData.class).sheet("模板").doWrite(list);

    }*/

    @Test
    public void test(){
        // 创建工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建工作表
        Sheet sheet = workbook.createSheet();
        // 行数
        int rowNum = 10;
        // 特定的行定制样式

        // 循环创建行数
        for (int i = 0; i < rowNum; i++) {
            Row row = sheet.createRow(i);

        }
    }


    @Test
    public void test02(){
     DemoData demoData = new DemoData();
     Supplier<DemoData> d1 = DemoData::new;
        DemoData demoData1 = d1.get();
        System.out.println("d1.get() = " + d1.get());
        System.out.println("d1.get() = " + d1.get());
        DemoData demoData2 = d1.get();
        System.out.println("demoData1 = " + demoData1);
        System.out.println("demoData2 = " + demoData2);
        Supplier<DemoData> d2 = DemoData::new;
        DemoData demoData11 = d2.get();
        DemoData demoData22 = d2.get();
        System.out.println("demoData11 = " + demoData11);
        System.out.println("demoData22 = " + demoData22);
    }

    /**
     * Consumer实例
     * 该接口有一个accept方法需要传递一个参数,还有一个默认方法
     */
    @Test
    public void test03(){
        Consumer<String> stringConsumer = new Consumer<String>(){

            /**
             * Performs this operation on the given argument.
             *
             * @param s the input argument
             */
            @Override
            public void accept(String s) {
                // 这里可以对传递进来的参数 进行操作
               // s="我是改写的参数";
                System.out.println(s);
            }
        };

        stringConsumer.accept("我是传递的参数");
        System.out.println("==============================");
        // Stream流的forEach使用的就是 Consumer类型的参数  Consumer<? super T> action
        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        // 这就意味着我们可以使用 Consumer接口来对于stream流的数据进行过滤或者操作
       // stream.forEach(stringConsumer);
        // 例子  有个要点,就是stream流只能被消费一次,即每次创建的steam流只能被用一次,所以上面的那个需要注释掉或者再次创建一个steam流
        System.out.println("==============================");
        stream.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                // 对传递的字符串进行操作
                if(s.equals("aaa")){
                    s="我是aaa";

                }
                System.out.println(s);
            }
        });
        System.out.println("==============================");
        Stream<String> stream02= Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        // stream02.forEach(System.out::print);
        // 使用lambda表达式输出,这就是我们常用的那种,本质就是传递一个实现Consumer接口,重写其中的accept方法
       // Consumer<String> consumer1 = (s) -> { System.out.println(s); };
        stream02.forEach(s -> {
            System.out.println(s);
        });

    }

    /**
     * Supplier接口实例,相当于一个容器存储值
     */
    @Test
    public void test04() throws Throwable {
        // Supplier接口只有一个get方法 没有参数,
        Supplier<Double> supplier = Math::random;
        Object o = supplier.get();
        System.out.println(o);
        // 为什么要说他是一个容器呢?
        Supplier<String> str = new Supplier<String>() {
            @Override
            public String get() {
                return "我是一个容器";
            }
        };
        String s = str.get();
        System.out.println(s);
        // 看上去 的确像个容器  可以返回任意类型的对象 和集合和map的确有些类似
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        // 获得一个Optional对象   发现Optianal对象也有一个get方法
        Optional<Integer> first = stream.filter(a -> a > 3).findFirst();
        if(first.isPresent()){ // isPresent方法是判断该对象是否为空
            Integer integer = first.get();
            System.out.println(integer);
        }
        System.out.println("==============================================");
        // 但是 Optional对象并没有实现Supplier接口,他的有一些方法需要 Supplier 类型的参数
        // orElseGet()方法 如果Supplier类型的参数返回的与 Optional对象(值为4)不一致就返回Optional对象中的数值,
        // 如果 Optional对象是null就返回Supplier类型的参数返回的值(值为 1)  直接点开orElseGet()方法也能清晰的看到该方法写了一个三元运算式
      Integer integer =   first.orElseGet(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1;
            }
        });
        // 下面那个方法 是 如果first 为null 就将参数 other 设置进去,否则就返回 first 的值
        Integer integer1 = first.orElse(2);
        System.out.println(integer);
        System.out.println(integer1);
        // 这个方法就是如果 first2 为 null就抛出异常    我们这里就把first2做成null
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);
        Optional<Integer> first2 = stream2.filter(a -> a > 7).findFirst();
        first2.orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new Exception("我是一个异常哦");
            }
        });
    }

    /**
     * Predicate接口 只有一个test方法需要重写 其他都是默认方法
     */
    @Test
    public void test05(){
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 5;
            }
        };
        boolean test = predicate.test(8);
        System.out.println(test);
        System.out.println("=============================");
        // lambda表达式
        predicate =  (t) -> t > 5;
        System.out.println(predicate.test(3));
        System.out.println("=============================");
        // Stream流中的filter参数类型就是 Predicate  大于 5的都会输出
        Stream<Integer> stream = Stream.of(1, 23, 3, 4, 5, 56, 6, 6);
        stream.filter(predicate).forEach(System.out::println);

    }

    /**
     * Function 接口是一个功能型接口，
     * 它的一个作用就是转换作用，将输入
     * 数据转换成另一种形式的输出数据。
     */
    @Test
    public void test06(){
        //泛型的第一个参数是转换前的类型，第二个是转化后的类型
        Function<String,Integer> fn = new Function<String,Integer>(){
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        // 使用Stream流的map方法将字符串转换为字符串的长度
        Stream<String> stream = Stream.of("aaa", "bbbbb", "ccccccv");
        stream.map(fn).forEach(System.out::println);
        System.out.println("=============================");
        Stream<String> stream2= Stream.of("aaa", "bbbbb", "ccccccv");
        List<Integer> list = stream2.map(s -> {
            if(s.length() > 4){
                return s.length();
            }else{
                return 0;
            }
        }).collect(Collectors.toList());
        list.forEach(System.out::println);


    }


}
