package cn.proem.dagl.web.fileStamp.dto;

import com.itextpdf.text.BaseColor;

public class PdfFileStamp{
	private Float horizonPosition;//水平位置
	
	private Float verticalPosition;//垂直位置;
	
	private Float width;//列宽
	
	private Float height;//列高
	
	private Float rownums;//行数
	
	private Float colnums;//列数
	
	private Float lineSize;//边框大小
	
	private Float fontSize;//字体大小	
	
	private BaseColor lineColor;//边框颜色
	
	private BaseColor fontColor;//字体颜色
	
	private String inputPath;//输入地址
	
	private String outputPath;//输出地址
	
	
	
	private String[][] fonts;
	

	public BaseColor getLineColor() {
		return lineColor;
	}


	public void setLineColor(BaseColor lineColor) {
		this.lineColor = lineColor;
	}


	public BaseColor getFontColor() {
		return fontColor;
	}


	public void setFontColor(BaseColor fontColor) {
		this.fontColor = fontColor;
	}


	public Float getHorizonPosition() {
		return horizonPosition;
	}


	public void setHorizonPosition(Float horizonPosition) {
		this.horizonPosition = horizonPosition;
	}


	public Float getVerticalPosition() {
		return verticalPosition;
	}


	public void setVerticalPosition(Float verticalPosition) {
		this.verticalPosition = verticalPosition;
	}


	public Float getWidth() {
		return width;
	}


	public void setWidth(Float width) {
		this.width = width;
	}


	public Float getHeight() {
		return height;
	}


	public void setHeight(Float height) {
		this.height = height;
	}


	public Float getRownums() {
		return rownums;
	}


	public void setRownums(Float rownums) {
		this.rownums = rownums;
	}


	public Float getColnums() {
		return colnums;
	}


	public void setColnums(Float colnums) {
		this.colnums = colnums;
	}


	public Float getLineSize() {
		return lineSize;
	}


	public void setLineSize(Float lineSize) {
		this.lineSize = lineSize;
	}


	public Float getFontSize() {
		return fontSize;
	}


	public void setFontSize(Float fontSize) {
		this.fontSize = fontSize;
	}


	public String getInputPath() {
		return inputPath;
	}


	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}


	public String getOutputPath() {
		return outputPath;
	}


	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}


	public String[][] getFonts() {
		return fonts;
	}


	public void setFonts(String[][] fonts) {
		this.fonts = fonts;
	}
	

}