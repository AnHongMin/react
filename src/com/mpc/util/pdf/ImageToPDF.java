package com.mpc.util.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.io.RandomAccess;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDCcitt;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

// Image파일을 PDF로 변환하기 
public class ImageToPDF {
	public static void main(String[] args) {
		generateImageToPDF(
			"D:/Chrysanthemum.jpg.pdf"
			,"D:/Chrysanthemum.jpg"
		);
	}

	public static boolean generateImageToPDF(String destPDFFile, String srcImageFile) {
		boolean result = true;
		PDDocument doc = null;
		try {
			doc = new PDDocument();
			PDXObjectImage ximage = null;
			if (srcImageFile.toLowerCase().endsWith(".jpg")) {
				FileInputStream inputStream = new FileInputStream(srcImageFile);
				ximage = new PDJpeg(doc, inputStream);
			} else if (srcImageFile.toLowerCase().endsWith(".tif") || srcImageFile.toLowerCase().endsWith(".tiff")) {
				ximage = new PDCcitt(doc, (RandomAccess) new RandomAccessFile(new File(srcImageFile), "r"));
			} else {
				throw new Exception("Image type not supported:" + srcImageFile);
			}

			PDPage page = new PDPage(getPageSize(ximage.getWidth(), ximage.getHeight()));
			int marginX = ((int) page.getMediaBox().getWidth() - ximage.getWidth()) / 2;
			int marginY = ((int) page.getMediaBox().getHeight() - ximage.getHeight()) / 2;
			page.getMediaBox().move(-marginX, -marginY);
			doc.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(doc,page);
			contentStream.drawImage(ximage, 0, 0);
			closeQuietly(contentStream);
			doc.save(destPDFFile);
		} catch (Exception e) {
			System.out.println("ImageToPDF-generateImageToPDF ERROR : "+ e.getMessage());
		} finally {
			closeQuietly(doc);
		}
		return result;
	}

	/**
	 * 
	 * 이미지 크기에 맞는 용지 크리를 반환함<br />
	 * 
	 * default 용지는 A4
	 * 
	 * @param imageWidth
	 * 
	 * @param imageHeight
	 * 
	 * @return
	 */

	public static PDRectangle getPageSize(int imageWidth, int imageHeight) {
		PDRectangle pdRectangle = null;
		pdRectangle = PDPage.PAGE_SIZE_A4;
		return pdRectangle;

	}

	public static void closeQuietly(PDPageContentStream pdPageContentStream) {
		try {
			if (pdPageContentStream != null) {
				pdPageContentStream.close();
			}
		} catch (IOException ioe) {
			System.out.println("IOUtils-closeQuietly ERROR : "+ ioe.getMessage());
		}
	}

	public static void closeQuietly(PDDocument pdDocument) {
		try {
			if (pdDocument != null) {
				pdDocument.close();
			}
		} catch (IOException ioe) {
			System.out.println("IOUtils-closeQuietly ERROR : "+ ioe.getMessage());
		}
	}
}