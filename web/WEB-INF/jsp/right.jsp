<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系統 ——後台管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10"></td></tr>
	<tr>
		<td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;當前位置：後台管理  &gt; 當前用戶</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	</tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
	<tr valign="top">
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
				<tr><td class="font3 fftd">用戶姓名：<font color="red" size="2">${sessionScope.user_session.username}</font></td></tr>
				<tr><td class="main_tdbor"></td></tr>

				<tr><td class="font3 fftd">用戶狀態：<font color="red" size="2">
					<c:choose>
						<c:when test="${sessionScope.user_session.userstatus == 1}">
							已審核
						</c:when>
						<c:otherwise>
							未審核
						</c:otherwise>
					</c:choose>
				</font></td></tr>
				<tr><td class="main_tdbor"></td></tr>
				<tr>
					<td class="font3 fftd">
						創建時間：<font color="red" size="2">
						<f:formatDate value="${sessionScope.user_session.createDate}"
									  type="date" dateStyle="long"/>
					</font>
					</td>
				</tr>
				<tr><td></td></tr>
			</table>
		</td>
	</tr>
</table>
<div style="height:10px;"></div>
</body>
</html>