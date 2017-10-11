package cn.proem.stamp;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFWriter {

	public static void main(String[] args) throws IOException, DocumentException {
		String[][] fonts = { { "测试01", "测试02", "测试03" }, { "测试11", "测试12", "测试13" }, { "测试21", "测试22", "aaa测试23" } };
		drawTable("G:/test/代码整洁之道.pdf","G:/test/test1.pdf", 10, 20, 100, 20, 3, 3, fonts, 1, 12,BaseColor.BLACK, BaseColor.RED);
	}

	public static void drawTable(String path,
			String outpath,
			float x, float y, // 输出位置 
			float width, float height, // 列宽列高 
			float rownums, float colnums, // 列数行数
			String[][] fonts,  // 输出内容
			float weight, float size, BaseColor linecolor, BaseColor fontcolor // 输出样式
			) throws DocumentException, IOException {
		// 创建一个pdf读入流
		PdfReader reader = new PdfReader(path);
		// 根据一个pdfreader创建一个pdfStamper.用来生成新的pdf.
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outpath));

		// 这个字体是itext-asian.jar中自带的 所以不用考虑操作系统环境问题.
		BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // set
																									// font
		// baseFont不支持字体样式设定.但是font字体要求操作系统支持此字体会带来移植问题.
		Font font = new Font(bf, size);
		font.setStyle(Font.BOLD);
		font.getBaseFont();

		// 获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上.
		PdfContentByte over = stamper.getOverContent(1);
		// 用pdfreader获得当前页字典对象.包含了该页的一些数据.比如该页的坐标轴信息.
		PdfDictionary p = reader.getPageN(1);
		// 拿到mediaBox 里面放着该页pdf的大小信息.
		PdfObject po = p.get(new PdfName("MediaBox"));
		// po是一个数组对象.里面包含了该页pdf的坐标轴范围.
		PdfArray pa = (PdfArray) po;

		// x轴长度
		float pdfwidth = pa.getAsNumber(2).floatValue();
		float pdfheight = pa.getAsNumber(3).floatValue();

		over.setLineWidth(weight);
		over.setColorStroke(linecolor);
		// 绘制横线
		for (int line = 0; line <= rownums; line++) {
			over.moveTo(x, pdfheight - (y + line * height));
			over.lineTo(x + width * colnums, pdfheight - (y + line * height));
		}

		for (int row = 0; row <= colnums; row++) {
			over.moveTo(x + row * width, pdfheight - y);
			over.lineTo(x + row * width, pdfheight - (y + height * rownums));
		}
		over.stroke();
		
		//开始写入文本  
        over.beginText();
        //设置字体和大小  
        over.setFontAndSize(font.getBaseFont(), font.getSize());
        //设置字体颜色
        over.setColorFill(fontcolor); 
          
        
        for(int ldx = 0 ; ldx < rownums; ldx ++){
        	for(int rdx = 0; rdx < colnums; rdx ++){
        		//设置字体的输出位置  
                over.showTextAligned(Element.ALIGN_CENTER, fonts[ldx][rdx], x + (rdx + 0.5f) * width, pdfheight - (y + (ldx + 1) * height) + (height - size + weight) / 2, 0);
        	}
        	
        }
        over.endText();  
		stamper.close();

	}
}