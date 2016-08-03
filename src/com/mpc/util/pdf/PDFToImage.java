package com.mpc.util.pdf;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFImageWriter;

// PDF 페이지를 이미지로 변환하기(PDF To Image) 
public class PDFToImage {
	public static void main(String[] args) {
		extractPagesAsImage("D:/Chrysanthemum.jpg.pdf", 300, "");
	}

	/**
	 * 
	 * @param sourceFile
	 * @param resolution
	 * @param password
	 * 
	 * @return 이미지 형태의 PDF Page 추출결과를 반환
	 */
	public static boolean extractPagesAsImage(String sourceFile, int resolution, String password) {
		boolean result = false;
		String imageFormat = "png";
		int endOfPages = 0;
		PDDocument pDDocument = null;
		try {
			pDDocument = PDDocument.load(sourceFile);
			endOfPages = pDDocument.getNumberOfPages();
		} catch (IOException ioe) {
			System.out.println("PDFToImage-extractPagesAsImage ERROR : "+ ioe.getMessage());
		}

		String destFilePrefix = destFilePrefix(sourceFile);
		PDFImageWriter imageWriter = new PDFImageWriter();
		try {
			result = imageWriter.writeImage(pDDocument, imageFormat, password, 1, endOfPages, destFilePrefix, BufferedImage.TYPE_INT_RGB, resolution);
		} catch (IOException ioe) {
			System.out.println("PDFToImage-extractPagesAsImage ERROR : "+ ioe.getMessage());
		}
		return result;
	}

	public static String destFilePrefix(String sourceFile) {
		String result = "";
		int lastSeparatorIndex = sourceFile.lastIndexOf("/") + 1;
		int lastCommaIndex = sourceFile.lastIndexOf(".");
		result = sourceFile.substring(0, lastSeparatorIndex);
		result = result+ (sourceFile.substring(lastSeparatorIndex, lastCommaIndex));
		return result;
	}
}