<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<table>
<tr>
	<th>이중코드구분</th>
	<th>CONTACTLOG_ID</th>
	<th>통화일자</th>
	<th>통화시간</th>
	<th>상담원명</th>
	<th>고객연락처</th>
	<th>상담경로</th>
	<th>통화자</th>
	<th>언어</th>
	<th>통화결과</th>
	<th>상담대분류</th>
	<th>중분류</th>
	<th>소분류</th>
	<th>상품구분</th>
	<th>상담내용</th>
	<th>연결점</th>
	<th>브랜드</th>
	<th>상태</th>
	<th>지점구분</th>
</tr>
<c:forEach var="list" items="${list}">
<tr>
	<td>${list.ORDER_V1}</td>
	<td>${list.contactlog_id}</td>
	<td>${list.contact_dt}</td>
	<td style="mso-number-format:\@">${list.contactstart_tt}</td>
	<td>${list.usernm}</td>
	<td style="mso-number-format:\@">${list.telcontact}</td>
	<td>${list.channel}</td>
	<td>${list.telcust_nm}</td>
	<td>${list.lang}</td>
	<td>${list.DIALRESULT}</td>
	<td>${list.UPTYPE}</td>
	<td>${list.MIDTYPE}</td>
	<td>${list.DETAILTYPE}</td>
	<td>${list.goods_gb}</td>
	<td>${list.content}</td>
	<td>${list.conn_branch_id}</td>
	<td>${list.conn_brand_id}</td>
	<td>${list.STATUS}</td>
	<td>${list.BRANCH_GB}</td>
</tr>
</c:forEach>
</table>
</body>
</html>

