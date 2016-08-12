<% 
		Response.CharSet = "utf-8"
		Session.CodePage = "65001"
		Response.CodePage = "65001"

		Dim sDate
		sDate = "20160101"
		 
		dim sAceDBIP		' 사용
		dim sAceDBUserID	' 사용
		dim sAceDBUserPwd	' 사용
		dim sAceCatalog		' 사용

		sAceDBIP = "XXX.XX.XX.XXX"
		sAceDBUserID = "XX"
		sAceDBUserPwd = "XXX"
		sAceCatalog = "XXX"
		 
		'=========== SQL(기간계) 접속 ==============================================
		Set objCon = Server.CreateObject("ADODB.Connection") 
		dim strCon
		
		strCon = "Provider=SQLOLEDB.1;Data Source="&sAceDBIP&",2127;Initial Catalog="&sAceCatalog&";User id="&sAceDBUserID&";Password="&sAceDBUserPwd 'TEST : 테스트 성공

		Response.Write strCon
				
		objCon.CommandTimeout = 7200000
		
		objCon.Open  strCon 'OLEDB DB 접속
		
		
		Response.Write now()&"--  proc start <br>"
		
		'Response.End
		'=========== SQL(기간계) 접속 ==============================================
		
		Set objComm = Server.CreateObject("ADODB.Command" )

		'Set a stored procedure
		objComm.CommandText = "ApplicationDataForUnPaidCall"
		objComm.CommandType = 4
		objComm.ActiveConnection = objCon

		Set objRs = Server.CreateObject("ADODB.Recordset" )
		
		Response.write "해피콜리스트 생성<br>"

		objCon.CSHappyCallList sDate, "FF", objRs
		'objCon.CSHappyCallList sDate, "CS", objRs
			
		Response.Write now()&" --  proc end  <br>"
		
		
		Do Until objRs.EOF  '반환값 있을 때만 다음 실행
			dim i
			For i = 0 to 39
	           Response.Write i & " : " & objRs(i) & "<br>"  
			next
			Response.Write "<br>"
			objRs.MoveNext
		Loop
		Response.Write "끝 "&now()&"<br>"
		
		Set objCmd = Nothing

		objRs.Close
		Set objRs = Nothing


		'SQL 연결 해제	
		Set objComm = Nothing
		objCon.Close
		Set objCon = Nothing
		Response.Write now()&"<br>"		
%>
