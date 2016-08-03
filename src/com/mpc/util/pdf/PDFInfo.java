package com.mpc.util.pdf;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

// PDF 기본정보 출력하기
public class PDFInfo {
	public static void main(String[] args) {
		printPDFInfo("D:\\Chrysanthemum.jpg.pdf");
	}

	public static void printPDFInfo(String PDFFile) {
		PDDocument document = null;
		try {
			document = PDDocument.load(PDFFile);
		} catch (IOException e) {
			System.out.println("PDFInfo-printPDFInfo ERROR : " + e.getMessage());
		}

		PDDocumentInformation information = document.getDocumentInformation();
		System.out.println("PDFInfo-printPDFInfo INFO : getNumberOfPages = "+ document.getNumberOfPages());
		System.out.println("PDFInfo-printPDFInfo INFO : isEncrypted      = "+ document.isEncrypted());
		System.out.println("PDFInfo-printPDFInfo INFO : getAuthor        = "+ information.getAuthor());
		System.out.println("PDFInfo-printPDFInfo INFO : getCreator       = "+ information.getCreator());
		System.out.println("PDFInfo-printPDFInfo INFO : getKeywords      = "+ information.getKeywords());
		System.out.println("PDFInfo-printPDFInfo INFO : getProducer      = "+ information.getProducer());
		System.out.println("PDFInfo-printPDFInfo INFO : getSubject       = "+ information.getSubject());
		System.out.println("PDFInfo-printPDFInfo INFO : getTitle         = "+ information.getTitle());
		System.out.println("PDFInfo-printPDFInfo INFO : getTrapped       = "+ information.getTrapped());
	}
}