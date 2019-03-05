<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>人事管理系統 ——後台管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script language="javascript" type="text/javascript">
        $(function(){
            /** 給左側功能菜單綁定點擊事件  */
            $("td[id^='navbg']").click(function(){
                /** 獲取一級菜單的id */
                var navbgId = this.id;
                /** 獲取對應的二級菜單id */
                var submenuId = navbgId.replace('navbg','submenu');
                /** 控制二級菜單顯示或隱藏  */
                $("#"+submenuId).toggle();
                /** 控制關閉或者開啟的圖標*/
                $("#"+navbgId).toggleClass("left_nav_expand");

                /** 控制其他的一級菜單的二級菜單隱藏按鈕都關閉  */
                $("tr[id^='submenu']").not("#"+submenuId).hide();
                /** 控制其他一級菜單的圖片顯示關閉  */
                $("td[id^='navbg']").not(this).removeClass().addClass("left_nav_closed");


            })
        })
	</script>
</head>
<body>
<div style="margin:10px;background-color:#FFFFFF; text-align:left;">
	<table width="200" height="100%" border="0" cellpadding="0" cellspacing="0" class="left_nav_bg">
		<tr><td class="left_nav_top"><div class="font1">用戶管理</div></td></tr>
		<tr valign="top">
			<td class="left_nav_bgshw" height="50">
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="user/selectUser" target="main">用戶查詢</a></img></p>
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="user/addUser?flag=1" target="main">添加用戶</a></img></p>
			</td>
		</tr>
		<tr><td height="2"></td></tr>

		<tr><td id="navbg1" class="left_nav_closed" ><div class="font1">部門管理</div></td></tr>
		<tr valign="top" id="submenu1" style="display: none">
			<td class="left_nav_bgshw" height="50">
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="dept/selectDept" target="main">部門查詢</a></img></p>
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="dept/addDept?flag=1" target="main">添加部門</a></img></p>
			</td>
		</tr>
		<tr><td height="2"></td></tr>

		<tr><td id="navbg2" class="left_nav_closed" ><div class="font1">職位管理</div></td></tr>
		<tr valign="top" id="submenu2" style="display: none">
			<td class="left_nav_bgshw" height="50">
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="job/selectJob" target="main">職位查詢</a></img></p>
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="job/addJob?flag=1" target="main">添加職位</a></img></p>
			</td>
		</tr>
		<tr><td height="2"></td></tr>

		<tr><td id="navbg3" class="left_nav_closed" ><div class="font1">員工管理</div></td></tr>
		<tr valign="top" id="submenu3" style="display: none">
			<td class="left_nav_bgshw" height="50">
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/employee/selectEmployee" target="main">員工查詢</a></img></p>
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/employee/addEmployee?flag=1" target="main">添加員工</a></img></p>
			</td>
		</tr>
		<tr><td height="2"></td></tr>

		<tr><td id="navbg4" class="left_nav_closed" ><div class="font1">公告管理</div></td></tr>
		<tr valign="top" id="submenu4" style="display: none">
			<td class="left_nav_bgshw tdbtmline" height="50">
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/notice/selectNotice" target="main">公告查詢</a></img></p>
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/notice/addNotice?flag=1" target="main">添加公告</a></img></p>
			</td>
		</tr>
		<tr><td height="2"></td></tr>

		<tr><td id="navbg5" class="left_nav_closed" onClick="showsubmenu(5)"><div class="font1">下載中心</div></td></tr>
		<tr valign="top" id="submenu5" style="display: none">
			<td class="left_nav_bgshw tdbtmline" height="50">
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/document/selectDocument" target="main">文檔查詢</a></img></p>
				<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/document/addDocument?flag=1" target="main">上傳文檔</a></img></p>
			</td>
		</tr>
		<tr><td height="2"></td></tr>

		<tr valign="top"><td height="100%" align="center"><div class="copycct"><br /><strong>技術支持：</strong><br><strong>廣東為學教育有限公司</strong><br>Http://www.fkjava.org</div></td></tr>
		<tr><td height="10"><img src="${ctx}/images/left_nav_bottom.gif" height="10"></img></td></tr>
		<tr><td height="10" bgcolor="#e5f0ff">&nbsp;</td></tr>
	</table>
</div>
</body>
</html>