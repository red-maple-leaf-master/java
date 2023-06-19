package com.example.demo;


import com.example.demo.pdf.util.PdfUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

// https://blog.csdn.net/qq_39181568/article/details/80003903

@SpringBootTest
public class itextCreateDemo {

    private final static String PATH = "E:\\Desktop\\YCT.pdf";

    @Test
    public void test01() throws IOException, DocumentException {
        // 1.新建document对象
        Document document = new Document(PageSize.A4, 90, 90, 72, 72);

        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter.getInstance(document, new FileOutputStream(PATH));

        // 3.打开文档
        document.open();

        // Create a PdfFont
//        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        /* 使用中文字体 */
        BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/STFANGSO.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

//        Font font = new Font(baseFont);
        // 标题 加粗
        Font fangFont = new Font(bf, 14, Font.BOLD);
        Font fangFont01 = new Font(bf, 12, Font.BOLD);
        Font fangFont02 = new Font(bf, 12);

        Phrase phrase02 = new Phrase();
        setUnderLine(phrase02, "融资期限为45465", false, fangFont02);
        setUnderLine(phrase02, "timeLimit", true, fangFont02);
        setUnderLine(phrase02, "termType", true, fangFont02);
        setFontStyle(document, phrase02);

        // 5.关闭文档
        document.close();
    }

    @Test
    public void test() throws IOException, DocumentException {

        Map<String, String> wordToPDFData = PdfUtils.getWordToPDFData();


        // 1.新建document对象
        Document document = new Document(PageSize.A4, 90, 90, 72, 72);

        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter.getInstance(document, new FileOutputStream(PATH));

        // 3.打开文档
        document.open();

        // Create a PdfFont
//        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        /* 使用中文字体 */
        BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/STFANGSO.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

//        Font font = new Font(baseFont);
        // 标题 加粗
        Font fangFont = new Font(bf, 14, Font.BOLD);
        Font fangFont01 = new Font(bf, 12, Font.BOLD);
        Font fangFont02 = new Font(bf, 12);

        // 4.添加一个内容段落
        setHeaderStyle("壹仓通供应链平台融资合同", document, fangFont);


        setFontStyleAndStyle("甲方（借款方）：" + wordToPDFData.get("enterpriseBaseInfo.enterpriseName"), document, fangFont01);
        setFontStyleAndStyle("法定代表人：" + wordToPDFData.get("enterpriseBaseInfo.legalPersonName"), document, fangFont01);
        setFontStyleAndStyle("住所地：" + wordToPDFData.get("enterpriseBaseInfo.legalPersonAddress"), document, fangFont01);
        setFontStyleAndStyle("联系方式：" + wordToPDFData.get("enterpriseBaseInfo.legalPersonPhone"), document, fangFont01);
        setFontStyleAndStyle("乙方（融资方）：" + wordToPDFData.get("enterpriseInfo.enterpriseName"), document, fangFont01);
        setFontStyleAndStyle("法定代表人：" + wordToPDFData.get("enterpriseInfo.enterpriseLegalPerson"), document, fangFont01);
        setFontStyleAndStyle("住所地：" + wordToPDFData.get("enterpriseInfo.domicile"), document, fangFont01);
        setFontStyleAndStyle("联系方式：" + wordToPDFData.get("enterpriseInfo.contact"), document, fangFont01);

        setFontStyle("鉴于：甲方为壹仓通供应链服务平台（以下简称壹仓通平台）的注册用户，因业务的开" +
                "展，存在融资需求；乙方为满足壹仓通平台中的中小企业供应链融资的需求，解决中小企业融资难问题，根据有关法律法规，" +
                "双方经协商一致，签订本合同。", document, fangFont02);

        setFontStyle("一、融资前提", document, fangFont01);
        setFontStyle("本合同项下融资业务，需满足以下条件：", document, fangFont02);
        setFontStyle("1、本合同已经生效；", document, fangFont02);
        setFontStyle("本合同项下融资业务，需满足以下条件：", document, fangFont02);
        setFontStyle("2、甲方向乙方预留与签署本合同有关的公司文件、单据、印章、相关人员名单和签字样本，并填妥有关凭证和审批文件；", document, fangFont02);
        setFontStyle("3、甲方开立履行本合同所必须的账户；", document, fangFont02);
        setFontStyle("4、本合同约定的担保已有效设立；", document, fangFont02);
        setFontStyle("5、乙方认为甲方应予满足的其他条件。", document, fangFont02);

        setFontStyle("二、融资金额", document, fangFont01);
        setFontStyle("甲方可依据双方约定的资料向乙方申请提供融资，乙方根据相关条件判断是否批准甲方申请。。", document, fangFont02);
        // 带下划线的用短语对象
        // 设置带下划线的数值
        Phrase phrase01 = new Phrase();
        setUnderLine(phrase01, "本合同项下融资金额为（小写）", false, fangFont02);
        setUnderLine(phrase01, wordToPDFData.get("loanAmount"), true, fangFont02);
        setUnderLine(phrase01, ",（大写）人民币", false, fangFont02);
        setUnderLine(phrase01, wordToPDFData.get("capitalLoanAmount"), true, fangFont02);
        setUnderLine(phrase01, "。", false, fangFont02);
        setFontStyle(document, phrase01);


        setFontStyle("三、融资期限", document, fangFont01);
        setFontStyle("本合同项下融资业务，需满足以下条件：", document, fangFont02);

        Phrase phrase02 = new Phrase();
        setUnderLine(phrase02, "融资期限为", false, fangFont02);
        setUnderLine(phrase02, wordToPDFData.get("timeLimit"), true, fangFont02);
        setUnderLine(phrase02, wordToPDFData.get("termType"), false, fangFont02);
        setFontStyle(document, phrase02);

        setFontStyle("甲方提前还款的，按实际融资期限计算。", document, fangFont02);


        setFontStyle("四、资金用途", document, fangFont01);
        setFontStyle("甲方支付与合作方《买卖合同》项下货款，合同号__________，货物品名：__________。", document, fangFont02);


        setFontStyle("五、融资费用及收取", document, fangFont01);

        Phrase phrase03 = new Phrase();
        setUnderLine(phrase03, "甲方应向乙方支付本合同项下产生的相关费（包含但不限于手续费，费率：发票金额的", false, fangFont02);
        setUnderLine(phrase03, wordToPDFData.get("procedureInterestRate"), true, fangFont02);
        setUnderLine(phrase03, "，该费用计收、标准和方式等按乙方有关规定执行。", false, fangFont02);

        setFontStyle(document, phrase03);

        Phrase phrase04 = new Phrase();
        setFontStyle("甲方通过以下第___种方式支付上述费用：", document, fangFont02);
        setFontStyle("1、在乙方通知后___个银行工作日内通过___________支付；", document, fangFont02);
        setUnderLine(phrase04, "2、授权乙方直接从甲方账户（账号：", false, fangFont02);
        setUnderLine(phrase04, wordToPDFData.get("partyBankAccount"), true, fangFont02);
        setUnderLine(phrase04, "）扣除；", false, fangFont02);
        setFontStyle(document, phrase04);
        setFontStyle("3、其他：________________。", document, fangFont02);


        setFontStyle("六、利率和计结利息", document, fangFont01);
        setFontStyle("1、利率（年利率）", document, fangFont02);
        setFontStyle("参照中国人民银行____期人民币贷款利率_____ %上/下浮___%；", document, fangFont02);
        setFontStyle("2、利息计算", document, fangFont02);
        setFontStyle("利息自乙方付款之日起算，按实际支付款项金额和实际使用天数计算；", document, fangFont02);
        setFontStyle("计算公式：利息=本金×实际天数×日利率（日利率计算方式为年利率/360）", document, fangFont02);
        setFontStyle("3、结息方式", document, fangFont02);
        setFontStyle("结息方式为下列第___种：", document, fangFont02);
        setFontStyle("（1）按季结息，每季度末月的___日为结息日，___日为付息日：", document, fangFont02);
        setFontStyle("（2）按月结息，每月的 ___日为结息日，___日为付息日：", document, fangFont02);
        setFontStyle("（3）到期利随本清：", document, fangFont02);
        setFontStyle("（4）预收利息，到期结息。", document, fangFont02);
        setFontStyle("4、罚息", document, fangFont02);
        setFontStyle("（1）若甲方未按约定期限偿还融资款项，就逾期部分，从逾期之日起按照逾期罚息利率计收利息，直至清偿本息为止。逾期罚息利率为本条第 1款约定的利率水平上加收____%:", document, fangFont02);
        setFontStyle("(2）对甲方不能按期支付的利息，以本条第 3款约定的结息方式，融资期内按照本条第1款约定的利率计收复利，進期后改按本款约定的罚,息利率计收复利。", document, fangFont02);
        setFontStyle("（3）罚息计算公式：利息=本金×实际天数×日利率。本金是指逾期本金和应付未付之利息。", document, fangFont02);


        setFontStyle("七、融资款的发放", document, fangFont01);
        setFontStyle("融资银行在同意甲方融资申请后，按以下第____种方式发放融资款项：", document, fangFont02);

        Phrase phrase05 = new Phrase();
        setUnderLine(phrase05, "1、将全部融资款直接划转至如下账户：户名：", false, fangFont02);
        setUnderLine(phrase05, wordToPDFData.get("enterpriseInfo.accountName"), true, fangFont02);
        setUnderLine(phrase05, "，账号：", false, fangFont02);
        setUnderLine(phrase05, wordToPDFData.get("enterpriseInfo.bankAccount"), true, fangFont02);
        setUnderLine(phrase05, ",开户行：", false, fangFont02);
        setUnderLine(phrase05, wordToPDFData.get("enterpriseInfo.depositBank"), true, fangFont02);
        setUnderLine(phrase05, "。未经乙方书面同意，甲方或款项实际收取方不得改变融资款用途。", false, fangFont02);
        setFontStyle(document, phrase05);

        Phrase phrase06 = new Phrase();
        setUnderLine(phrase06, "2、根据甲方的申请，在融资额度内分次向如下账户：户名：", false, fangFont02);
        setUnderLine(phrase06, wordToPDFData.get("enterpriseInfo.accountName"), true, fangFont02);
        setUnderLine(phrase06, "，账号：", false, fangFont02);
        setUnderLine(phrase06, wordToPDFData.get("enterpriseInfo.bankAccount"), true, fangFont02);
        setUnderLine(phrase06, ",开户行：", false, fangFont02);
        setUnderLine(phrase06, wordToPDFData.get("enterpriseInfo.depositBank"), true, fangFont02);
        setUnderLine(phrase06, "。甲方超过本合同约定时间未支用货款金额的一部或全部，应视为自动取消，未支用金额不得再继续支用。", false, fangFont02);
        setFontStyle(document, phrase06);


        setFontStyle("八、融资款的偿还", document, fangFont01);
        setFontStyle("甲方应在融资银行处开立结算账户，户名：_____，账号：__________,作为本合同项下融资款的指定扣款账户。同时授权乙方于还款日从该账户中直接扣收应还融资款本息。若甲方指定扣款账户发生变更，应在最近一个约定还款日之前7日内书面通知乙方。由于甲方未及时告知融资银行造成的后果，由甲方承担。", document, fangFont02);


        setFontStyle("九、融资担保", document, fangFont01);
        setFontStyle("1、动产抵押/质押", document, fangFont02);
        setFontStyle("本合同项下的融资款由甲方提交乙方认可的动产（电子仓单）进行质押担保；", document, fangFont02);
        setFontStyle("2、本合同项下的融资款由___承担连带保证担保。", document, fangFont02);
        setFontStyle("____出具的无条件不可撤销担保书作为偿还贷款的保证。", document, fangFont02);


        setFontStyle("十、违约及违约处理", document, fangFont01);
        setFontStyle("（一）甲方发生下列情况一项即构成违约", document, fangFont02);
        setFontStyle("（1）甲方不能按本合同约定的还款计划偿还融资款。", document, fangFont02);
        setFontStyle("（2）甲方未按规定的用途使用融资款。", document, fangFont02);
        setFontStyle("（3） 甲方在融资期内发生有损其清偿能力的情况，如失信、被起诉等。", document, fangFont02);
        setFontStyle("（4）甲方违反其与供应链平台或合作方《买卖合同》内容的。", document, fangFont02);
        setFontStyle("（5）其他违反合同的行为。", document, fangFont02);
        setFontStyle("（二）违约处理", document, fangFont02);
        setFontStyle("甲方构成前款违约行为，乙方有权按下列条款一项或数项规定处理。", document, fangFont02);
        setFontStyle("(1）以书面通知甲方，告知其违约问题，并责成限期采取有效措施，违约行为。", document, fangFont02);
        setFontStyle("(2）对甲方未按规定用途使用的和挪用融资款，在本合同规定利率基础上加收利息100%。", document, fangFont02);
        setFontStyle("(3）对甲方未按规定期限偿还贷款，乙方对逾期部分的贷款在本合同规定的利率基础上加收利息。", document, fangFont02);
        setFontStyle("（4）宣布本合同项下尚未偿还的贷款/融资款本息加速到期。停止发放本合同项下的全部金额或尚未支用的贷款余额。", document, fangFont02);
        setFontStyle("（5）提前收回本本合同项下的部分或全部贷款本息，由贷款人从监管账户中扣取，或采用贷款人认为必要的其他方式追索。", document, fangFont02);

        setFontStyle("十一、合同的生效、变更及解除", document, fangFont01);
        setFontStyle("1、本合同经甲方、乙方双方签字或盖章后生效，在贷款本息全部清偿后自动失效。", document, fangFont02);
        setFontStyle("2、除由于一方违约原因外，任何一方要求交更或解除合同，应征得另一方同意，双方未办商一致前，本合同仍然有效。", document, fangFont02);
        setFontStyle("3、本合同所依据的国家有关规定发生了变化，合同双方应对本合同作相应的修改、变更或解除。", document, fangFont02);

        setFontStyle("十二、其他", document, fangFont01);
        setFontStyle("1、本合同项下货款项目的货款申请书是本合同不可分割的附件，其所列各条款与本合项下的有关条款享有同等法律效力。", document, fangFont02);
        setFontStyle("2、甲方保证向贷款人按月提供有关计划、统计、财务会计报表及其人有关资料。", document, fangFont02);
        setFontStyle("3、双方的住所地即为文书送达地址。", document, fangFont02);
        setFontStyle("4、合同争议的解决方式：本合同在履行过程中发生的争议，由双方当事人协商解决，协商不成的，任何一份可采取下列______种方式解决：", document, fangFont02);
        setFontStyle("（1）提交___南京______仲裁委仲裁；：", document, fangFont02);
        setFontStyle("（2）乙方所在地法院起诉。", document, fangFont02);
        setFontStyle("（以下无正文，为签字页）", document, fangFont02);
        setFontStyle("", document, fangFont02);
        setFontStyle("甲方（借款方）：                                              乙方（融资方/贷款方）：", document, fangFont02);
        setFontStyle("", document, fangFont02);
        setFontStyle("日期 ：                                                                 日期：", document, fangFont02);

        // 5.关闭文档
        document.close();
    }

    /**
     * 设置段前段后  0.5行  首行缩进2字符 行间距 25榜
     *
     * @param text
     */
    public void setStyle(Paragraph text) {
        text.setSpacingAfter(10f);
        text.setSpacingAfter(10f);
        text.setFirstLineIndent(20f);//设置首行缩进
        text.setLeading(25f);// 行间距
    }

    /**
     * 大标题 设置段前段后  0.5行 行间距 25榜
     *
     * @param text
     */
    public void setHeaderStyle(String text, Document document, Font font) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingAfter(10f);
        paragraph.setSpacingAfter(10f);
        paragraph.setLeading(25f);// 行间距
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);//设置居中
        document.add(paragraph);
    }

    /**
     * 开头设置加粗 不缩进
     *
     * @param text
     * @param document
     * @param font
     * @throws DocumentException
     */
    public void setFontStyleAndStyle(String text, Document document, Font font) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingAfter(10f);
        paragraph.setSpacingAfter(10f);
        document.add(paragraph);
    }

    /**
     * 正文 没有下划线样式
     *
     * @param text
     * @param document
     * @param font
     * @throws DocumentException
     */
    public void setFontStyle(String text, Document document, Font font) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        setStyle(paragraph);
        document.add(paragraph);
    }

    /**
     * 正文 有下划线样式
     *
     * @param document
     * @throws DocumentException
     */
    public void setFontStyle(Document document, Phrase phrase) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);
        setStyle(paragraph);
        document.add(paragraph);
    }

    /**
     * 设置下划线
     */
    public void setUnderLine(Phrase phrase, String text, boolean flag, Font font) {

        if (flag) {
            Chunk chunk = new Chunk("  " + text + "  ", font);
            chunk.setUnderline(0.2f, -1);
            phrase.add(chunk);
        } else {
            Chunk chunk = new Chunk(text, font);
            phrase.add(chunk);
        }

    }
}
