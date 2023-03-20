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
import java.util.*;

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

    /**
     * 斐波那契数列
     */
    @Test
    public void test01(){
        int f = f(2);
        System.out.println("f = " + f);
    }

    private int f(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        return f(n - 2) + f(n - 1);
    }

    @Test
    public void test02(){
        int n = 13;
        int[] cache = new int[n + 1];
        // 初始化 数据 数据全部 设置为 -1
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        System.out.println(f(cache, n));
    }
    public  int f(int[] cache, int n) {
        if (cache[n] != -1) {
            return cache[n];
        }
        // 遇到新的值优先放置到数组中 ,遇到调用过的 直接返回结果即可
        cache[n] = f(cache, n - 1) + f(cache, n - 2);
        return cache[n];
    }

    @Test
    public void test03(){
        sum(12000);
    }

    public  long sum(long n) {
        if (n == 1) {
            return 1;
        }
        long m = sum(n - 1);
        return n + m;
    }

    /**
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，
     * 返回 true ；如果数组中每个元素互不相同，返回 false 。
     */
    @Test
    public void test04(){
        int []nums = new int[]{1,2,3,4};
        int []nums2 = new int[]{1,1,1,3,3,4,3,2,4,2};
        System.out.println("containsDuplicate(nums) = " + containsDuplicate(nums2));
    }


    public boolean containsDuplicate(int[] nums) {
        // 先排序
        Arrays.sort(nums);

        // 相邻是否相同
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i +1]){
                return true;
            }
        }
      return false;
    }

    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组
     * （子数组最少包含一个元素），返回其最大和。
     *
     * 子数组 是数组中的一个连续部分。
     */
    @Test
    public void test05(){

        int [] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int dp[] = new int[arr.length];
        dp[0] = arr[0];
        int res=dp[0];
        for (int i = 1; i < arr.length; i++) {
            if(dp[i - 1] > 0){
                dp[i]=dp[i - 1] + arr[i];
                Math.max(res,dp[i]);
            }else{
                dp[i]=arr[i];
            }
        }
        maxSubArray(arr);
    }


    public int maxSubArray(int[] nums) {
        int len = nums.length;
        // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
        int[] dp = new int[len];
        dp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }
        System.out.println("dp = " + Arrays.toString(dp));
        // 也可以在上面遍历的同时求出 res 的最大值，这里我们为了语义清晰分开写，大家可以自行选择
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 俩数之和
     */
    @Test
    public void test06(){
        int[] nums = {2,7,11,15};
        int target = 9;
        int [] res = toSum(target,nums);
        System.out.println(Arrays.toString(res));
    }

    private int[] toSum(int target, int[] nums) {
  /*    // map解题
   Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return new int[]{};*/

        // 双指针解题
        int l=0;
        int r=1;
        int maxNum = nums.length - 1;
        while(l < maxNum){
            if(target == nums[r] + nums[l]){
            return new int[]{r,l};
        }
            if(r == maxNum){
                l=l+1;
                r=l;
            }
            r++;
        }
        return new int[]{};
    }


    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    @Test
    public void test07(){
       String str = "abcabcbb";
       int num =  lengthOfLongestSubstring("abcbb");
        System.out.println("num = " + num);
    }
        public int lengthOfLongestSubstring(String s) {
            if (s.length()==0) return 0;
            HashMap<Character, Integer> map = new HashMap<Character, Integer>();
            int max = 0;
            int left = 0;
            for(int i = 0; i < s.length(); i ++){
                if(map.containsKey(s.charAt(i))){
                    left = Math.max(left,map.get(s.charAt(i)) + 1);
                }
                map.put(s.charAt(i),i);
                max = Math.max(max,i-left+1);
            }
            return max;
        }


}
