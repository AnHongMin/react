package com.mpc.test;

import java.io.FileOutputStream;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
 
public class PDFEncryption {
	
    private static String USER_PASS = "123"; 
    private static String OWNER_PASS = "123";     
     
    public static void main(String[] args) {
        try {     
        	PdfReader reader = new PdfReader("test1.pdf");
        	FileOutputStream fos = new FileOutputStream("result.pdf");
        	PdfStamper stamper = new PdfStamper(reader,fos);
        	
        	stamper.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(),
                    PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        	stamper.close();
        	fos.close();
        	reader.close();        	
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
}