package top.oneyi;

import com.spire.barcode.*;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpireBaercode {
    /**
     * 创建条形码
     * @throws IOException
     */
    @Test
    public void createBarcode() throws IOException {
        BarcodeSettings settings = new BarcodeSettings();//创建BarcodeSettings实例
        settings.setType(BarCodeType.Code_128);//指定条码类型
        settings.setData("123456789");//设置条码数据
        settings.setData2D("123456789");//设置条码显示数据
        settings.setShowTextOnBottom(true);//设置数据文本显示在条码底部
        settings.setX(0.8f);//设置黑白条宽度
        settings.setImageHeight(50);//设置生成的条码图片高度
        settings.setImageWidth(70);//设置生成的条码图片宽度
        settings.hasBorder(true);//设置边框可见
        settings.setBorderColor(new Color(135,206,250));//设置条码边框颜色
        settings.setBorderWidth(1);//设置条码边框宽度
        settings.setBackColor(new Color(240,255,255));//设置条码背景色

        BarCodeGenerator barCodeGenerator = new BarCodeGenerator(settings);//创建BarCodeGenerator实例
        BufferedImage bufferedImage = barCodeGenerator.generateImage();//根据settings生成图像数据，保存至BufferedImage实例
        ImageIO.write(bufferedImage, "png", new File("CODE128.png"));//保存条码为PNG图片
        System.out.println("Complete!");
    }

    /**
     * 创建二维码
     */
    @Test
    public void createQR() throws IOException {
        BarcodeSettings settings = new BarcodeSettings();//创建BarcodeSettings实例
        settings.setType(BarCodeType.QR_Code);//设置条码类型为QR二维码
        settings.setData("尽量克服的记录的会计分录的是解放螺丝扣");//设置二维码数据
        settings.setData2D("Hello 123456789");//设置二维码显示数据
        settings.setQRCodeDataMode(QRCodeDataMode.Alpha_Number);//设置数据类型
        settings.setX(1.0f);//设置二维码模型宽度
        settings.setQRCodeECL(QRCodeECL.H);//设置二维码纠错级别
        settings.setImageWidth(50);//设置生成的二维码图片宽度
        settings.setImageHeight(50);//设置生成的二维码图片高度
        settings.hasBorder(false);//设置二维码边框不可见

        BarCodeGenerator barCodeGenerator = new BarCodeGenerator(settings);//创建BarCodeGenerator实例
        BufferedImage bufferedImage = barCodeGenerator.generateImage();//根据settings生成图像数据，保存至BufferedImage实例
        ImageIO.write(bufferedImage, "png", new File("QRCode.png"));//保存二维码图片为PNG格式
        System.out.println("Complete!");
    }

    /**
     * 识别/读取条码
     */
    @Test
    public void scanBarcode() throws Exception {
        //使用scan方法从图片中识别Code 128条形码
        String[] datas = BarcodeScanner.scan("CODE128.png", BarCodeType.Code_128);
        System.out.print(datas[0]);
    }
}
