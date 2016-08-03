package com.mpc.util.pdf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

// PDF Text 추출하기
public class TextExtraction {
	public static void main(String[] args) {
		extractingText("D:\\createPDF.pdf");
	}

	public static boolean extractingText(String targetFile) {
		boolean result = true;
		String extractTxtFile = targetFile + ".txt";
		PDDocument pdDocument = null;
		try {
			pdDocument = PDDocument.load(targetFile);
			pdDocument.decrypt("4567");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CryptographyException e) {
			e.printStackTrace();
		}

		FileOutputStream fileOutputStream = openOutputStream(new File(extractTxtFile));
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

		BufferedWriter bufferedWriter = null;
		bufferedWriter = new BufferedWriter(outputStreamWriter);
		PDFTextStripper stripper = null;
		try {
			stripper = new PDFTextStripper();
		} catch (IOException e) {
			System.out.println("TextExtraction-extractingText ERROR: PDFTextStripper 객체생성 실패");
		}
		stripper.setStartPage(1);
		// stripper.setEndPage(11);

		try {
			stripper.writeText(pdDocument, bufferedWriter);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("TextExtraction-extractingText ERROR: text 추출 실패");
		}

		try {
			pdDocument.close();
		} catch (IOException e) {
			System.out.println("TextExtraction-extractingText ERROR: PDDocument close 실패");
		}

		IOUtils.closeQuietly(bufferedWriter);
		return result;
	}

	public static FileOutputStream openOutputStream(File file) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
		} catch (Exception e) {
			System.out.println("TextExtraction-openOutputStream ERROR: "+ e.getMessage());
		}
		return fileOutputStream;
	}
}