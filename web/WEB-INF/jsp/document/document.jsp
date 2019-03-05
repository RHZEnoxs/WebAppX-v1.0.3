<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系統 ——文檔管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx }/css/css.css" type="text/css" rel="stylesheet" />
	<link href="${ctx }/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<link href="${ctx }/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css" />
	<script src="${ctx }/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx }/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx }/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
	<script src="${ctx }/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
	<script type="text/javascript">
        $(function(){

            var boxs = $("input[type='checkbox'][id^='box_']");
            /** 給全選按鈕綁定點擊事件  */
            $("#checkAll").click(function(){
                // this是checkAll  this.checked是true
                // 所有數據行的選中狀態與全選的狀態一致
                boxs.attr("checked",this.checked);
            })

            /** 給每個數據行綁定點擊事件：判斷如果數據行都選中全選也應該選中，反之  */
            boxs.click(function(event){
                /** 去掉復選按鈕的事件傳播：點擊復選會觸發行點擊：因為復選在行中 */
                event.stopPropagation();

                /** 判斷當前選中的數據行有多少個  */
                var checkedBoxs = boxs.filter(":checked");
                /** 判斷選中的總行數是否等於總行數：以便控制全選按鈕的狀態   */
                $("#checkAll").attr("checked",checkedBoxs.length == boxs.length);
            })

            /** 給數據行綁定鼠標覆蓋以及鼠標移開事件  */
            $("tr[id^='data_']").hover(function(){
                $(this).css("backgroundColor","#eeccff");
            },function(){
                $(this).css("backgroundColor","#ffffff");
            }).click(function(){
                /** 控制該行是否需要被選中 */
                /** 獲取此行的復選框id */
                var checkboxId = this.id.replace("data_","box_");

                /** 觸發本行的復選點擊 */
                $("#"+checkboxId).trigger("click");
            })

            /** 刪除員工綁定點擊事件 */
            $("#delete").click(function(){
                /** 獲取到用戶選中的復選框  */
                var checkedBoxs = boxs.filter(":checked");
                if(checkedBoxs.length < 1){
                    $.ligerDialog.error("請選擇一個需要刪除的文檔！");
                }else{
                    /** 得到用戶選中的所有的需要刪除的ids */
                    var ids = checkedBoxs.map(function(){
                        return this.value;
                    })

                    $.ligerDialog.confirm("確認要刪除嗎?","刪除文檔",function(r){
                        if(r){
                            // alert("刪除："+ids.get());
                            // 發送請求
                            window.location = "${ctx }/document/removeDocument?ids=" + ids.get();
                        }
                    });
                }
            })

            /** 下載文檔功能 */
            $("a[id^='down_']").click(function(){
                /** 得到需要下載的文檔的id */
                var id = this.id.replace("down_","");
                /** 下載該文檔 */
                window.location = "${ctx}/document/downLoad?id="+id;
            })



        })

        function down(id){
            $("a[id='down_"+id+"']").trigger("click");
        }

	</script>
</head>
<body>
<!-- 導航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10"></td></tr>
	<tr>
		<td width="15" height="32"><img src="${ctx }/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;當前位置：文檔管理 &gt; 文檔查詢</td>
		<td width="15" height="32"><img src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
	</tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	<!-- 查詢區  -->
	<tr valign="top">
		<td height="30">
			<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
				<tr>
					<td class="fftd">
						<form name="documentform" method="post" id="documentform" action="${ctx }/document/selectDocument">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="font3">
										標題：<input type="text" name="title" />
										<input type="submit"  value="搜索"/>
										<input type="button" id="delete" value="刪除">
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
					<td><input type="checkbox" id="checkAll" ></td>
					<td>標題</td>
					<td>創建時間</td>
					<td>創建人</td>
					<td>描述</td>
					<td>操作</td>
					<td>下載</td>
				</tr>
				<c:forEach items="${requestScope.documents}" var="document" varStatus="stat">
					<tr ondblclick="down(${document.id});"  class="main_trbg" align="center" id="data_${stat.index}">
						<td><input type="checkbox" id="box_${stat.index}" value="${document.id}"></td>
						<td>${document.title }</td>
						<td>
							<f:formatDate value="${document.createDate}"
										  type="date" dateStyle="long"/>
						</td>
						<td>${document.user.username }</td>
						<td>${document.remark }</td>
						<td align="center" width="40px;"><a href="${ctx }/document/updateDocument?flag=1&id=${document.id}">
							<img title="修改" src="${ctx }/images/update.gif"/></a>
						</td>
						<td align="center"  width="40px;"><a href="#" id="down_${document.id }">
							<img width="20" height="20" title="下載" src="${ctx }/images/downLoad.png"/></a>
						</td>
					</tr>
				</c:forEach>


			</table>
		</td>
	</tr>
	<!-- 分頁標籤 -->
	<tr valign="top"><td align="center" class="font3">
		<fkjava:pager
				pageIndex="${pageModel.pageIndex}"
				pageSize="${pageModel.pageSize}"
				recordCount="${pageModel.recordCount}"
				submitUrl="${ctx}/document/selectDocument.action?pageModel.pageIndex={0}&document.title=${document.title}"
				style="flickr"
		/>
	</td></tr>
</table>
<div style="height:10px;"></div>
</body>
</html>