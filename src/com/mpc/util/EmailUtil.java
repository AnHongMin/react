package com.mpc.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

/**
 * 
 * 이메일 관련 유틸리티
 * http://commons.apache.org/proper/commons-email/
 * 
 * @author hongmin.an
 *
 */
public class EmailUtil {

	/**
	 * 이메일을 보냅니다.
	 * 
	 * @param str_to_email		받는사람 이메일
	 * @param str_to_name		받는사람 이름
	 * @param str_from_email	보내는사람 이메일
	 * @param str_from_name		보내는사람 이름
	 * @param str_title			제목
	 * @param str_content		내용
	 * @param vec_files			첨부파일 경로
	 * @return
	 * @throws Exception
	 */
	public static int setSendMail(ArrayList<String> str_to_email, ArrayList<String> str_to_name, String str_from_email, String str_from_name, String str_title, String str_content, Vector<String> vec_files) throws Exception {
		int int_result = 0;
		
		try {
			int int_i = 0; // 임시변수

			EmailAttachment attachment = new EmailAttachment(); // 이메일첨부파일객체

			// 이메일 메시지를 만듭니다.
			HtmlEmail email = new HtmlEmail();

			// gmail 인증 처리
			email.setAuthentication("dailyclosing@hmart.com", "!1234abcd");
			email.setTLS(true);
			email.setHostName("smtp.gmail.com"); // SMTP 서버
			email.setSmtpPort(587);
			
			email.setCharset("UTF-8"); // 인코딩셋팅
			for(int i=0; i<str_to_email.size(); i++){
				email.addTo(str_to_email.get(i), str_to_name.get(i)); // 받는사람 정보	
			}
			email.setFrom(str_from_email, str_from_name); // 보내는사람 정보
			email.setSubject(str_title); // 제목
			email.setHtmlMsg(EmailUtil.setHtmlCOntents(str_content)); // 내용

			// 첨부파일이 있으면 갯수 만큼 처리합니다.
			if (vec_files != null) {
				for (int_i = 0; int_i < vec_files.size(); int_i++) {
					File file = new File(vec_files.elementAt(int_i));
					if (file.isFile()) {
						attachment.setPath(vec_files.elementAt(int_i));
						attachment.setDisposition(EmailAttachment.ATTACHMENT);
						attachment.setDescription("commons-email api");
						attachment.setName(MimeUtility.encodeText(attachment.getName()));
						email.attach(attachment);
					}
				}
			}

			// 보내기
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

		return int_result;
	}

	public static String setHtmlCOntents(String contents) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'> \n");
		sb.append("<html xmlns='http://www.w3.org/1999/xhtml'>  							\n");
		sb.append("<head>                                       							\n");
		sb.append("<meta http-equiv='Content-Type' content='text/html; charset=euc-kr' />   \n");
		sb.append("<meta http-equiv='Content-Script-Type' content='text/tcl' />             \n");
		sb.append("<meta http-equiv='Cache-Control' content='no-cache' />                   \n");
		sb.append("<meta http-equiv='Pragma' content='no-cache' />                          \n");
		sb.append("<meta http-equiv='imagetoolbar' content='no' />                          \n");
		sb.append("<meta name='robots' content='index,follow' />                            \n");
		sb.append("<title></title>                                   						\n");
		sb.append("</head>                                                                  \n");
		sb.append("<body>                                                                   \n");
		sb.append(contents);
		sb.append("</body>                      											\n");
		sb.append("</html>                      											\n");
		return sb.toString();
	}
}
