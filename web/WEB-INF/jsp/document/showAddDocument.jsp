<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
	<link href="fkjava.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${ctx }/css/css.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script type="text/javascript" src="${ctx}/js/tiny_mce/tiny_mce.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	<script type="text/javascript">

        $(document).ready(function() {

            /** 表單提交的校驗 */
            $("#btn").click(function(){
                var title = $("#title").val();
                var file = $("#file").val();

                if($.trim(title).length <= 2){
                    alert("請輸入兩個字符以上的標題");
                    return ;
                }else if(!file){
                    alert("請上傳文檔！");
                    return ;
                }

                $("#documentForm").submit();

            })
        });


	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10"></td></tr>
	<tr>
		<td width="15" height="32"><img src="${ctx }/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;當前位置：文檔管理  &gt; 上傳文檔
		</td>
		<td width="15" height="32"><img src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
	</tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
	<tr valign="top">
		<td>

			<form id="documentForm" name="documentForm" action="${ctx }/document/addDocument" enctype="multipart/form-data" method="post">
				<!-- 隱藏表單，flag表示添加標記 -->
				<input type="hidden" name="flag" value="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">

					<tr><td class="font3 fftd">


						文檔標題：<input type="text" name="title" size="30" id="title"/></td>
					</tr>
					<tr><td class="main_tdbor"></td></tr>


					<tr><td class="font3 fftd">文檔描述：<br/>
						<textarea name="remark" cols="88" rows="11" id="content"></textarea>
					</td></tr>
					<tr><td class="main_tdbor"></td></tr>

					<tr><td class="font3 fftd">文檔：<br/>
						<input type="file" name="file" id="file"  size="30"/>
					</td></tr>
					<tr><td class="main_tdbor"></td></tr>



					<tr><td class="font3 fftd">
						<input type="button" id="btn" value="上傳">
						<input type="reset" value="重置">
					</td></tr>
					<tr><td class="main_tdbor"></td></tr>


				</table>
			</form>
		</td>
	</tr>
</table>
<div style="height:10px;"></div>
</body>
</html>