package cn.proem.stamp;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class DHZ {

	public static void main(String[] args) {
		try {
			String TemplatePDF = "e://F-3.pdf"; // 魔板路径
			String outFile = "e://test.pdf"; // 生成新的pdf的路径
			File out = new File(outFile);
			PdfReader reader = new PdfReader(TemplatePDF);
			PdfStamper ps = new PdfStamper(reader, new FileOutputStream(out)); // 生成的输出流

			AcroFields s = ps.getAcroFields();
			// 插入文字
			//insertText(ps, s);
			// 插入图片
			insertImage(ps, s);
			ps.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 插入图片
	 * 
	 * @param ps
	 * @param s
	 * @author WangMeng
	 * @date 2016年6月16日
	 */
	public static void insertImage(PdfStamper ps, AcroFields s) {

		try {
			List<AcroFields.FieldPosition> list = s.getFieldPositions("QR_CODE");
			Rectangle signRect = list.get(0).position;

			Image image = Image.getInstance("e:/pdf.jpg");
			PdfContentByte under = ps.getOverContent(2);
			float x = signRect.getLeft();
			float y = signRect.getBottom();
			System.out.println(x);
			System.out.println(y);
			image.setAbsolutePosition(x, y);
			image.scaleToFit(signRect.getWidth(), signRect.getHeight());

			under.addImage(image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 创建Chunk
	 * 
	 * @return
	 * @author WangMeng
	 * @date 2016年6月16日
	 */
	public static Chunk CreateChunk() {
		BaseFont bfChinese;
		Chunk chunk = null;
		try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
			Font fontChinese = new Font(bfChinese, 10 * 4 / 3);
			chunk = new Chunk("张三", fontChinese);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return chunk;
	}

	/**
	 * 插入文本
	 * 
	 * @return
	 * @author WangMeng
	 * @date 2016年6月16日
	 */
	public static void insertText(PdfStamper ps, AcroFields s) {
		List<AcroFields.FieldPosition> list = s.getFieldPositions("CONNECT_NAME");
		Rectangle rect = list.get(0).position;

		PdfContentByte cb = ps.getOverContent(1);
		PdfPTable table = new PdfPTable(1);
		float tatalWidth = rect.getRight() - rect.getLeft() - 1;
		table.setTotalWidth(tatalWidth);

		PdfPCell cell = new PdfPCell(new Phrase(CreateChunk()));
		cell.setFixedHeight(rect.getTop() - rect.getBottom() - 1);
		cell.setBorderWidth(0);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setLeading(0, (float) 1.1);

		table.addCell(cell);
		table.writeSelectedRows(0, -1, rect.getLeft(), rect.getTop(), cb);
	}
}
