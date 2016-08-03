package com.mpc.util.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * 
 * PDFReader
 * 
 * http://pdfbox.apache.org/download.cgi
 * : fontbox-1.8.7.jar, jempbox-1.8.7.jar, pdfbox-1.8.7.jar 
 * 
 * http://findjar.com/jar/org/apache/directory/server/apacheds-all/1.5.5/apacheds-all-1.5.5.jar.html
 * : apacheds-all-1.5.4.jar
 * 
 * http://pdfbox.apache.org/docs/1.8.6/javadocs/
 * 
 * @author hongmin.an
 *
 */
public class PDFReader {
	@SuppressWarnings({ "rawtypes" })
	public static void main(String args[]) {
		try {
			PDFReader pdfr = new PDFReader();
			String str ="D:\\createPDF.pdf";
			File file = new File(str);
			String password = "4567";
			HashMap<String, Object> map = pdfr.read(file, password);
			Iterator i = map.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry e = (Map.Entry)i.next();
                Object value = e.getValue();
                System.out.println(e.getKey() + " : "+value);
            }
            /*
			if(map.containsKey("Text")){
				ArrayList<String> text = (ArrayList<String>)map.get("Text");
				for(int i=0; i<text.size(); i++){
					System.out.println(text.get(i));
				}
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Object> read(File file, String password) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>(); 
		InputStream in = new FileInputStream(file);

		PDFParser pdfp = new PDFParser(in);
		pdfp.parse();

		PDDocument pdd = pdfp.getPDDocument();
		if(password!=null){
			pdd.decrypt(password);
		}
		COSDocument cos = pdfp.getDocument();

		PDDocumentInformation pddi = pdd.getDocumentInformation();
		// 총 페이지수
		map.put("NumberOfPages", pdd.getNumberOfPages());
		// 제목
		map.put("Title", pddi.getTitle());
		// 제목
		map.put("Subject", pddi.getSubject());
		// 작성자
		map.put("Author", pddi.getAuthor());
		// 작성기
		map.put("Creator", pddi.getCreator());

		// 본문내용
		ArrayList<String> text = new ArrayList<String>();
		for(int i=1; i<=pdd.getNumberOfPages(); i++){
			PDFTextStripper pdfts = new PDFTextStripper();
			pdfts.setLineSeparator("\n");
			pdfts.setWordSeparator(" ");
			pdfts.setStartPage(i);
			pdfts.setEndPage(i);

			String temp = pdfts.getText(pdd);
			text.add(temp);
		}
		map.put("Text", text);
		
		cos.close();
		pdd.close();
		return map;
	}
}