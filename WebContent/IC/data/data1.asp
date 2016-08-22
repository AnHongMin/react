<% Option Explicit %>

<%
Response.CharSet = "utf-8"
Session.CodePage = "65001"
Response.CodePage = "65001"

	dim sCallback, sCONTACTLOG_ID, sCONTACTAGENT_ID, sTEAM_CD, sTELCONTACT, sINNO_REC_KEY, sCHANNEL_CD, sUSER_NM, sUSERLINE_NO
	sCallback = Request.QueryString("callback")
	sCONTACTLOG_ID = Request.QueryString("CONTACTLOG_ID")
	sCONTACTAGENT_ID = Request.QueryString("CONTACTAGENT_ID")
	sTEAM_CD = Request.QueryString("TEAM_CD")
	sTELCONTACT = Request.QueryString("TELCONTACT")
	sINNO_REC_KEY = Request.QueryString("INNO_REC_KEY")
	sCHANNEL_CD = Request.QueryString("CHANNEL_CD")
	sUSER_NM = Request.QueryString("USER_NM")
	sUSERLINE_NO = Request.QueryString("USERLINE_NO")

	Dim sQry, objConn
	Set objConn=server.CreateObject("ADODB.Connection")
	With objConn
		.Provider = "MSDASQL"
		.ConnectionString = "Password=xxx;Persist Security Info=True;User ID=xxx;Data Source=xxx;Initial Catalog=xxx"
		.Open
	End With

	sQry = " INSERT INTO CS_ASM_CONTACTLOG " 
	sQry = sQry & " ( CONTACTLOG_ID, CONTACT_DT, CONTACTSTART_TT, CONTACTAGENT_ID, TEAM_CD, TELCONTACT, INNO_REC_KEY, CHANNEL_CD, USER_NM, USERLINE_NO ) "
	sQry = sQry & " SELECT "
	sQry = sQry & " '" & sCONTACTLOG_ID & "', "	
	sQry = sQry & " TO_CHAR(SYSDATE, 'yyyymmdd'), "
	sQry = sQry & " TO_CHAR(SYSDATE, 'hh24miss'), "
	sQry = sQry & " '" & sCONTACTAGENT_ID & "', "
	sQry = sQry & " '" & sTEAM_CD & "', "
	sQry = sQry & " '" & sTELCONTACT & "', "
	sQry = sQry & " '" & sINNO_REC_KEY & "', "
	sQry = sQry & " '" & sCHANNEL_CD & "', "
	sQry = sQry & " ( SELECT USER_NM FROM SS_USER WHERE USER_ID = '" & sCONTACTAGENT_ID & "' ) , "
	sQry = sQry & " '" & sUSERLINE_NO & "' "
	sQry = sQry & " FROM DUAL "

'	response.write sQry

 	objConn.Execute sQry

	objConn.Close
%>
<%=sCallback%>({"message":"success"});