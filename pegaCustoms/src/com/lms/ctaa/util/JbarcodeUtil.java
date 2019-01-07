package com.lms.ctaa.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.jbarcode.JBarcode;
import org.jbarcode.JBarcodeFactory;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;


public class JbarcodeUtil {


	public static void main(String[] args) {
//		JbarcodeUtil.createBarcode("REE35002017101700102", new File("C:/Users/dengmingxing/Desktop/xx/barcode1.png"),
//				"D");
		/*System.out.println(System.getProperty("user.dir"));
		System.out.println(Class.class.getClass().getResource("/").getPath());*/
	}

	private static final int BARCODE_DPI = ImageUtil.DEFAULT_DPI;// 精细度。也有设置为150的
	private static final String FONT_FAMILY = "console";
	private static final int FONT_SIZE = 25;
	private static String TEXT = "";
	private static JBarcode jbc = null;

	public static JBarcode getJbarcode() throws InvalidAtributeException {
		// 注：
		// 1.常量条形码的高度和字体大小设置很重要，若是设置小了会看不到设置的文件
		// 2.生成的条码偶尔扫描不了，是因为条形码密度太厚，故"setXDimension()"很重要，
		// 值越小密度越细，条形码宽度越宽(高度越高，图片宽度越窄)
		if (jbc == null) {
			// 生成code128
			jbc = JBarcodeFactory.getInstance().createCode128();
			// jbc.setEncoder(Code128Encoder.getInstance());
			// jbc.setTextPainter(CustomTextPainter.getInstance());
			// jbc.setBarHeight(BARCODE_HEIGHT);
			
			jbc.setShowText(true);
			// 推荐样式
			jbc.setEncoder(Code128Encoder.getInstance());// 设置编码
			jbc.setTextPainter(BaseLineTextPainter.getInstance());
			jbc.setBarHeight(17);// 设置高度

			jbc.setXDimension(Double.valueOf(0.8).doubleValue());// 设置尺寸、大小、密集程度
			jbc.setPainter(WidthCodedPainter.getInstance());
			jbc.setWideRatio(Double.valueOf(30).doubleValue());// 设置宽度比率
			jbc.setCheckDigit(true);// 是否检查数字
			jbc.setShowCheckDigit(false);// 显示检查数字
		}
		return jbc;
	}

	/**
	 * 生成条形码文件
	 * 
	 * @param msg条形码内容
	 * @param file生成文件
	 * @param text
	 *            前缀
	 */
	public static void createBarcode(String msg, File file, String text) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			createBarcode(msg, fos, text);
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 生成条形码并写入指定输出流
	 * 
	 * @param msg条形码内容
	 * @param os输出流
	 * @param text
	 *            前缀
	 */
	public static void createBarcode(String msg, OutputStream os, String text) {
		try {
			TEXT = text;
			// 创建条形码的BufferedImage图像
			BufferedImage image = getJbarcode().createBarcode(msg);
			ImageUtil.encodeAndWrite(image, ImageUtil.PNG, os, BARCODE_DPI, BARCODE_DPI);
			os.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 静态内部类 自定义的TextPainter,允许定义字体大小、文本等
	 * 参考底层实现：BaseLineTextPainter.getInstance()
	 * 
	 * @author dengmingxing
	 *
	 */
	protected static class CustomTextPainter implements TextPainter {

		private static CustomTextPainter instance = new CustomTextPainter();

		public static CustomTextPainter getInstance() {
			return instance;
		}

		@Override
		public void paintText(BufferedImage image, String text, int width) {
			// 绘图
			Graphics g2d = image.getGraphics();
			// 创建字体
			Font font = new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE * width);
			g2d.setFont(font);
			FontMetrics fm = g2d.getFontMetrics();
			int height = fm.getHeight();
			int center = (image.getWidth() - fm.stringWidth(text)) / 2;
			g2d.setColor(Color.white);
			g2d.fillRect(0, 0, image.getWidth(), image.getHeight() * 1 / 20);
			g2d.fillRect(0, image.getHeight() - (height * 9 / 10), image.getWidth(), height * 9 / 10);
			g2d.setColor(Color.BLACK);
			// 绘制文本
			g2d.drawString(TEXT, 0, 145);
			// 绘制条形码
			g2d.drawString(text, center, image.getHeight() - (height / 10) - 2);
		}

	}


}
