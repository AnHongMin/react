package com.mpc.util.pdf;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

// PDF문서 생성하기
public class DocumentCreatioin {
	public static void main(String[] args) {
		try {
			createPDF();
		} catch (Exception e) {
		}
	}

	public static boolean createPDF() throws CryptographyException, IOException {
		boolean result = true;
		PDDocument document = null;
		document = new PDDocument();
		PDDocumentInformation pddi = document.getDocumentInformation();
		pddi.setCreator("Hongmin An");
		PDPage blankPage = new PDPage();
		document.addPage(blankPage);
		PDFont font = PDType1Font.TIMES_ITALIC;
		PDPageContentStream contentStream = null;

		try {
			contentStream = new PDPageContentStream(document, blankPage);
			contentStream.setFont(font, 50);
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(0, 100);
			contentStream.drawString("Hello Apache PDFBox");
			contentStream.endText();
		} catch (IOException e) {
			System.out.println("DocumentCreatioin-createPDF ERROR : "+ e.getMessage());
		} finally {
			try {
				contentStream.close();
			} catch (IOException e) {
				System.out.println("DocumentCreatioin-createPDF ERROR : "+ e.getMessage());
			}
		}

		try {
			String ownerPassword = "1234";
			String userPassword = "4567";
			document.encrypt(ownerPassword, userPassword);
			document.save("D:\\createPDF.pdf");
		} catch (COSVisitorException e) {
			result = false;
			System.out.println("DocumentCreatioin-createPDF ERROR : "+ e.getMessage());
		} catch (IOException e) {
			result = false;
			System.out.println("DocumentCreatioin-createPDF ERROR : "+ e.getMessage());
		}

		try {
			document.close();
		} catch (IOException e) {
			result = false;
			System.out.println("DocumentCreatioin-createPDF ERROR : "+ e.getMessage());
		}
		return result;
	}
}
