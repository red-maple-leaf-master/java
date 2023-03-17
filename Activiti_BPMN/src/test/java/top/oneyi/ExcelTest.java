package top.oneyi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.ibatis.ognl.Ognl.getClassResolver;
import static org.apache.ibatis.ognl.Ognl.getValue;

public class ExcelTest {


    @Test
    public void test(){
        String path = "C:\\Users\\ASUS\\Documents\\WeChat Files\\wxid_0w3o2p8ahwug22\\FileStorage\\File\\2023-03\\20级UI学员总表.xlsx";
        String sheel = "UI1班";
        String sheel2 = "UI2班";
        String sheel3 = "UI3班";

        try {
            String str = "''";
            List<String> list1 = this.readXls(path,sheel);
            List<String> list2 = this.readXls(path,sheel2);
            List<String> list3 = this.readXls(path,sheel3);
            list1.addAll(list2);
            list1.addAll(list3);
            System.out.println("list2.size() = " + list2.size());
            Map<String,String> map = new HashMap<>();
            int count=0;
            System.out.println("一共 "+list1.size()+" 条学员数据");
            for (String s : list1) {
                System.out.print(s+" ");
                map.put(s,count+"");
                count++;
                 if(count % 15 == 0 ){
                    System.out.println();
                }

            }
/*            int count1=0;
            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                System.out.print(stringStringEntry.getKey()+" ");
                if(stringStringEntry.getKey().equals("类型为空")){
                    System.out.println("stringStringEntry.getValue() = " + stringStringEntry.getValue());

                }
                if(count1 % 15 == 0 ){
                    System.out.println();
                }
                count1++;
            }
            System.out.println("count = " + count);
            System.out.println("map.size() = " + map.size());
            System.out.println("map.containsKey(\"类型为空\") = " + map.containsKey("类型为空"));*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文件名、sheel名读取excel中信息
     * @param path 文件地址
     * @param sheel
     * @return
     * @throws Exception
     */
    public static List<String> readXls(String path, String sheel) throws Exception {
        String  fileName=path.substring(path.lastIndexOf(".") + 1);
        Workbook workbook = null;
        InputStream input = Files.newInputStream(Paths.get(path));
        List<String> list = new ArrayList<>();
        boolean is2003 = true;
        //判断是否为2003版Excel
        if (!fileName.equals("xls")) {
            is2003 = false;
        }
        if (is2003 == true) {
            workbook = new HSSFWorkbook(input);
        } else {
            workbook = new XSSFWorkbook(input);
        }
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            Sheet sheet = workbook.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            String sheetName = sheet.getSheetName();
            if (sheetName.trim().equals(sheel)) {
                // 循环行Row
                for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row != null) {
                        // 每一行 的每一格的数据
                        for (int i = 0; i < row.getLastCellNum(); i++) {
                            if(i == 2){
                                Cell cell = row.getCell(i);
                                if(cell != null){
                                    // 暂时只要姓名这一列
                                    if(!"姓名".equals(getValue(cell))){
                                        list.add(getValue(cell));
                                    }
                                }

                            }
                        }
                    }
                }
            }

        }
        return list;
    }
    /**
     * 转换格式
     * @return
     */
    private static String getValue(Cell cell) {
        if (null != cell) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    return String.valueOf(cell.getNumericCellValue()) ;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    return String.valueOf(cell.getStringCellValue()) ;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    return String.valueOf(cell.getBooleanCellValue()) ;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    return String.valueOf(cell.getCellFormula()) ;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    return "" ;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    return "类型错误" ;
                default:
                    return "未知类型" ;
            }
        } else {
            return "类型为空";
        }
    }


}
