<% Option Explicit %>

<%
Response.CharSet = "utf-8"
Session.CodePage = "65001"
Response.CodePage = "65001"

	dim sCallback, sB, sC, sD 
	sCallback = Request.QueryString("callback")
	sB = Request.QueryString("B")
	sC = Request.QueryString("C")
	sD = Request.QueryString("D")

	Dim sQry, objConn
	Set objConn=server.CreateObject("ADODB.Connection")
	With objConn
		.Provider = "MSDASQL"
		.ConnectionString = "Password=XXXX;Persist Security Info=True;User ID=XXX;Data Source=XXX;Initial Catalog=XXX"
		.Open
	End With


	sQry = " INSERT INTO TEST " 
	sQry = sQry & " ( A, B, C, D ) "
	sQry = sQry & " VALUES ( TO_CHAR(SYSDATE, 'yyyy-mm-dd hh24:mi:ss'), '" & sB & "', '" & sC & "', '" & sD & "' ) "

'	response.write sQry

 	objConn.Execute sQry

	objConn.Close
%>
<%=sCallback%>({"message":"success"});