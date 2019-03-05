<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系統 ——用戶管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript">
        $(function(){
            /** 獲取上一次選中的部門數據 */
            var boxs  = $("input[type='checkbox'][id^='box_']");

            /** 給數據行綁定鼠標覆蓋以及鼠標移開事件  */
            $("tr[id^='data_']").hover(function(){
                $(this).css("backgroundColor","#eeccff");
            },function(){
                $(this).css("backgroundColor","#ffffff");
            })


            /** 刪除員工綁定點擊事件 */
            $("#delete").click(function(){
                /** 獲取到用戶選中的復選框  */
                var checkedBoxs = boxs.filter(":checked");
                if(checkedBoxs.length < 1){
                    $.ligerDialog.error("請選擇一個需要刪除的用戶！");
                }else{
                    /** 得到用戶選中的所有的需要刪除的ids */
                    var ids = checkedBoxs.map(function(){
                        return this.value;
                    })

                    $.ligerDialog.confirm("確認要刪除嗎?","刪除用戶",function(r){
                        if(r){
                            // alert("刪除："+ids.get());
                            // 發送請求
                            window.location = "${ctx }/user/removeUser?ids=" + ids.get();
                        }
                    });
                }
            })
        })
	</script>
</head>
<body>
<!-- 導航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10"></td></tr>
	<tr>
		<td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;當前位置：用戶管理 &gt; 用戶查詢</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	</tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	<!-- 查詢區  -->
	<tr valign="top">
		<td height="30">
			<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
				<tr>
					<td class="fftd">
						<form name="empform" method="post" id="empform" action="${ctx}/user/selectUser">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="font3">
										用戶名：<input type="text" name="username">
										用戶狀態：<input type="text" name="userstatus">
										<input type="submit" value="搜索"/>
										<input id="delete" type="button" value="刪除"/>
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
		</td>
	</tr>

	<!-- 數據展示區 -->
	<tr valign="top">
		<td height="20">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
				<tr class="main_trbg_tit" align="center">
					<td><input type="checkbox" name="checkAll" id="checkAll"></td>
					<td>登錄名</td>
					<td>密碼</td>
					<td>用戶名</td>
					<td>狀態</td>
					<td>創建時間</td>
					<td align="center">操作</td>
				</tr>
				<c:forEach items="${requestScope.users}" var="user" varStatus="stat">
					<tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
						<td><input type="checkbox" id="box_${stat.index}" value="${user.id}"></td>
						<td>${user.loginname }</td>
						<td>${user.password }</td>
						<td>${user.username }</td>
						<td>${user.userstatus }</td>
						<td><f:formatDate value="${user.createDate}"
										  type="date" dateStyle="long"/></td>
						<td align="center" width="40px;"><a href="${ctx}/user/updateUser?flag=1&id=${user.id}">
							<img title="修改" src="${ctx}/images/update.gif"/></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
	<!-- 分頁標籤 -->
	<tr valign="top"><td align="center" class="font3">
		<fkjava:pager
				pageIndex="${requestScope.pageModel.pageIndex}"
				pageSize="${requestScope.pageModel.pageSize}"
				recordCount="${requestScope.pageModel.recordCount}"
				style="digg"
				submitUrl="${ctx}/employee/selectEmployee?pageIndex={0}"/>
	</td></tr>
</table>
<div style="height:10px;"></div>
</body>
</html>