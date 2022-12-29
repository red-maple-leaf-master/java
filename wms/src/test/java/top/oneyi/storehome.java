package top.oneyi;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
                    if(w == split.length){
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
}
