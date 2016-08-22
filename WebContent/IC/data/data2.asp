<% Option Explicit %>

<%
Response.CharSet = "utf-8"
Session.CodePage = "65001"
Response.CodePage = "65001"

	dim sCallback, sCONTACTLOG_ID, sTELCUST_NM, sSOCIAL_ID, sPOLICY_NO, sACCIDENT_NO, sINNO_REC_KEY
	sCallback = Request.QueryString("callback")
	sCONTACTLOG_ID = Request.QueryString("CONTACTLOG_ID")
	sTELCUST_NM = Request.QueryString("TELCUST_NM")
	sSOCIAL_ID = Request.QueryString("SOCIAL_ID")
	sPOLICY_NO = Request.QueryString("POLICY_NO")
	sACCIDENT_NO = Request.QueryString("ACCIDENT_NO")
	sINNO_REC_KEY = Request.QueryString("INNO_REC_KEY")
	
	Dim sQry, objConn
	Set objConn=server.CreateObject("ADODB.Connection")
	With objConn
		.Provider = "MSDASQL"
		.ConnectionString = "Password=xxx;Persist Security Info=True;User ID=xxx;Data Source=xxx;Initial Catalog=xxx"
		.Open
	End With

	sQry = "UPDATE CS_ASM_CONTACTLOG " 
	sQry = sQry & " SET CONTACTEND_TT = TO_CHAR(SYSDATE, 'hh24miss'), "
	sQry = sQry & " TELCUST_NM = '" & sTELCUST_NM & "', "	
	If sSOCIAL_ID <> "" then
		sQry = sQry & " SOCIAL_ID = crypt_mpc.encrypt3('" & sSOCIAL_ID & "',getcode('X','crypt_mpc')), "
	End if
	sQry = sQry & " POLICY_NO = '" & sPOLICY_NO & "', "
	sQry = sQry & " ACCIDENT_NO = '" & sACCIDENT_NO & "' "
	sQry = sQry & " WHERE CONTACTLOG_ID = '" & sCONTACTLOG_ID & "' "

'	response.write sQry

 	objConn.Execute sQry

	objConn.Close
%>
<%=sCallback%>({"message":"success"});