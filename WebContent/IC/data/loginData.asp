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
	Dim sUSER_ID
	Dim sUSERPWD

	Dim rUSER_ID
	Dim rUSERLINE_NO
	Dim rUSERTEL_ID
	Dim rPBXAGENT_ID
	Dim rTEAM_CD
	
	sCallback = Request.QueryString("callback") 		
	sUSER_ID = Request("sUSER_ID")
	sUSERPWD = Request("sUSERPWD")

	dim sEncryptPwd
	dim cEnc
	dim pErr
	
	set cEnc = server.CreateObject("Scheduler.shBSchMng.1")
	pErr = cEnc.TWEncrypt(sUSERPWD, sEncryptPwd) 

   	strSql =	"SELECT   UPPER(USER_ID) AS USER_ID, " &_
		        "         USERLINE_NO, "&_
		        "         USERTEL_ID, "&_
		        "         PBXAGENT_ID, "&_
		        "         TEAM_CD "&_
				" FROM SS_USER "&_
				" WHERE USER_ID = UPPER('"&sUSER_ID&"')"&_
				" AND USERPWD = '"&sEncryptPwd&"'"				
	Set rs = dbconn.Execute(strSql)

	server.ScriptTimeout = 10000000
	
	dim i
	If rs.eof Then '질의의 결과가 존재하지 않는다면
		i = 0
	Else 
		i = 0	
		Do Until rs.eof
			rUSER_ID = rs("USER_ID") 
			rUSERLINE_NO = rs("USERLINE_NO")
			rUSERTEL_ID = rs("USERTEL_ID")
			rPBXAGENT_ID = rs("PBXAGENT_ID")
			rTEAM_CD = rs("TEAM_CD")
			rs.MoveNext
			i = i + 1
		Loop
	End If
	
	Set objCmd = Nothing
	dbconn.Close
	Set dbconn = Nothing 
%>
<%=sCallback%>({"count":"<%=i%>","rUSER_ID":"<%=rUSER_ID%>","rUSERLINE_NO":"<%=rUSERLINE_NO%>","rUSERTEL_ID":"<%=rUSERTEL_ID%>","rPBXAGENT_ID":"<%=rPBXAGENT_ID%>","rTEAM_CD":"<%=rTEAM_CD%>"});