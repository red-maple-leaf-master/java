package top.oneyi;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * velocity 测试
 * @author oneyi
 * @date 2023/4/23
 */
public class VelocityTest {
    @Test
    public void test1() throws IOException {
        // 1,设置velocity的资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 2,初始化velocity引擎
        Velocity.init(prop);
        // 3,创建velocity容器
        VelocityContext context = new VelocityContext();
        // 设置数据
        context.put("name", "zhangsan");
        // 4,加载velocity模板文件
        Template template = Velocity.getTemplate("vms/01-quickstart.vm", "utf-8");
        // 5,合并数据到模板
        FileWriter fw = new FileWriter("E:\\Desktop\\one\\java\\one\\src\\main\\resources\\HTML\\01-quickstart.html");
        // 合并+写入
        template.merge(context, fw);
        // 6,释放资源
        fw.close();
    }
    private static String macAddressStr = null;
    private static String computerName = System.getenv().get("COMPUTERNAME");
    private static final String[] windowsCommand = { "ipconfig", "/all" };
    private static final String[] linuxCommand = { "/sbin/ifconfig", "-a" };
    private static final Pattern macPattern = Pattern.compile(".*((:?[0-9a-f]{2}[-:]){5}[0-9a-f]{2}).*", Pattern.CASE_INSENSITIVE);

    public static List<String> getMacAddressLIst() throws IOException {
        final ArrayList<String> macAddressList = new ArrayList<String>();
        final String os = System.getProperty("os.name");
        final String command[];

        if (os.startsWith("Windows")) {
            command = windowsCommand;
        } else if (os.startsWith("Linux")) {
            command = linuxCommand;
        } else {
            throw new IOException("Unknow operating system:" + os);
        }
        // 执行命令
        final Process process = Runtime.getRuntime().exec(command);

        BufferedReader bufReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        for (String line = null; (line = bufReader.readLine()) != null;) {
            Matcher matcher = macPattern.matcher(line);
            if (matcher.matches()) {
                macAddressList.add(matcher.group(1));
                // macAddressList.add(matcher.group(1).replaceAll("[-:]",
                // ""));//去掉MAC中的“-”
            }
        }

        process.destroy();
        bufReader.close();
        return macAddressList;
    }

    /**
     * 获取一个网卡地址（多个网卡时从中获取一个）
     *
     * @return
     */
    public static String getMacAddress() {
        if (macAddressStr == null || macAddressStr.equals("")) {
            StringBuffer sb = new StringBuffer(); // 存放多个网卡地址用，目前只取一个非0000000000E0隧道的值
            try {
                List<String> macList = getMacAddressLIst();
                for (Iterator<String> iter = macList.iterator(); iter.hasNext();) {
                    String amac = iter.next();
                    if (!amac.equals("0000000000E0")) {
                        sb.append(amac);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            macAddressStr = sb.toString();

        }

        return macAddressStr;
    }
    /**
     * 获取电脑唯一标识
     *
     * @return
     */
    public static String getComputerID() {
        String id = getMacAddress();
        if (id == null || id.equals("")) {
            try {
                id = getIpAddrAndName();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return computerName;
    }
    /**
     * 获取客户端IP地址
     *
     * @return
     */
    public static String getIpAddrAndName() throws IOException {
        return InetAddress.getLocalHost().toString();
    }

    @Test
    public void test01() throws IOException {
        List<String> macAddressLIst = this.getMacAddressLIst();
        System.out.println("macAddressLIst = " + macAddressLIst);
        String macAddress = getMacAddress();
        System.out.println("macAddress = " + macAddress);
        System.out.println("computerName = " + computerName);

        System.out.println("InetAddress.getLocalHost().toString() = " + InetAddress.getLocalHost().toString());
        System.out.println("InetAddress.getLocalHost().getHostAddress().toString() = " + InetAddress.getLocalHost().getHostAddress().toString());
    }
} 
