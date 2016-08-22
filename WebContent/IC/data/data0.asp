<% 
	Response.CharSet = "utf-8"
	Session.CodePage = "65001"
	Response.CodePage = "65001"
	
'	=========== 오라클 접속 =====================================================
	'오라클(테스트DB) 연결
	Set dbconn = Server.CreateObject("ADODB.Connection")	'ODBC로 DB 연결
	dbconn.connectiontimeout = 600
	dbconn.Open  "DSN=xxx;uid=xxx;pwd=xxx"	'ODBC DB 접속
	'=========== 오라클 접속 =====================================================

	Set objCmd = Server.CreateObject("ADODB.Command")
	
	''################################################## DB CONNECT 연결 ##################################################
	
	Dim sCallback
	Dim rCONTACTLOG_ID
	
	sCallback = Request.QueryString("callback")

   	strSql =	" SELECT SEQ_ASMCONTACTLOG_ID.NextVal AS CONTACTLOG_ID " &_
				" FROM DUAL "	
	Set rs = dbconn.Execute(strSql)

	server.ScriptTimeout = 10000000
	
	dim i
	If rs.eof Then '질의의 결과가 존재하지 않는다면
		i = 0
	Else 
		i = 0	
		Do Until rs.eof
			rCONTACTLOG_ID = rs("CONTACTLOG_ID") 
			rs.MoveNext
			i = i + 1
		Loop
	End If
	
	Set objCmd = Nothing
	dbconn.Close
	Set dbconn = Nothing 
%>
<%=sCallback%>({"count":"<%=i%>","rCONTACTLOG_ID":"<%=rCONTACTLOG_ID%>"});