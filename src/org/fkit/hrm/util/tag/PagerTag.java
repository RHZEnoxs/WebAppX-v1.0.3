package org.fkit.hrm.util.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 分頁標籤
 */
public class PagerTag extends SimpleTagSupport {

	/** 定義請求URL中的佔位符常量 */
	private static final String TAG = "{0}";

	/** 當前頁碼 */
	private int pageIndex;
	/** 每頁顯示的數量 */
	private int pageSize;
	/** 總記錄條數 */
	private int recordCount;
	/** 請求URL page.action?pageIndex={0}*/
	private String submitUrl;
	/** 樣式 */
	private String style = "sabrosus";

	/** 定義總頁數 */
	private int totalPage = 0;

	/**  在頁面上引用自定義標籤就會觸發一個標籤處理類   */
	@Override
	public void doTag() throws JspException, IOException {
		/** 定義它拼接是終的結果 */
		StringBuilder res = new StringBuilder();
		/** 定義它拼接中間的頁碼 */
		StringBuilder str = new StringBuilder();
		/** 判斷總記錄條數 */
		if (recordCount > 0){   //1499 / 15  = 100
			/** 需要顯示分頁標籤，計算出總頁數 需要分多少頁 */
			totalPage = (this.recordCount - 1) / this.pageSize + 1;

			/** 判斷上一頁或下一頁需不需要加a標籤 */
			if (this.pageIndex == 1){ // 首頁
				str.append("<span class='disabled'>上一頁</span>");

				/** 計算中間的頁碼 */
				this.calcPage(str);

				/** 下一頁需不需要a標籤 */
				if (this.pageIndex == totalPage){
					/** 只有一頁 */
					str.append("<span class='disabled'>下一頁</span>");
				}else{
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
					str.append("<a href='"+ tempUrl +"'>下一頁</a>");
				}
			}else if (this.pageIndex == totalPage){ // 尾頁
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='"+ tempUrl +"'>上一頁</a>");

				/** 計算中間的頁碼 */
				this.calcPage(str);

				str.append("<span class='disabled'>下一頁</span>");
			}else{ // 中間
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='"+ tempUrl +"'>上一頁</a>");

				/** 計算中間的頁碼 */
				this.calcPage(str);

				tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
				str.append("<a href='"+ tempUrl +"'>下一頁</a>");
			}

			/** 拼接其它的信息 */
			res.append("<table width='100%' align='center' style='font-size:13px;' class='"+ style +"'>");
			res.append("<tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>" + str.toString());
			res.append("&nbsp;跳轉到&nbsp;&nbsp;<input style='text-align: center;BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none' type='text' size='2' id='pager_jump_page_size'/>");
			res.append("&nbsp;<input type='button' style='text-align: center;BORDER-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; PADDING-LEFT: 6px; PADDING-BOTTOM: 2px; BORDER-LEFT: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none' value='確定' id='pager_jump_btn'/>");
			res.append("</td></tr>");
			res.append("<tr align='center'><td style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>");
			/** 開始條數 */
			int startNum = (this.pageIndex - 1) * this.pageSize + 1;
			/** 結束條數 */
			int endNum = (this.pageIndex == this.totalPage) ? this.recordCount : this.pageIndex * this.pageSize;

			res.append("總共<font color='red'>"+ this.recordCount +"</font>條記錄，當前顯示"+ startNum +"-"+ endNum +"條記錄。");
			res.append("</td></tr>");
			res.append("</table>");
			res.append("<script type='text/javascript'>");
			res.append("   document.getElementById('pager_jump_btn').onclick = function(){");
			res.append("      var page_size = document.getElementById('pager_jump_page_size').value;");
			res.append("      if (!/^[1-9]\\d*$/.test(page_size) || page_size < 1 || page_size > "+ this.totalPage +"){");
			res.append("          alert('請輸入[1-"+ this.totalPage +"]之間的頁碼！');");
			res.append("      }else{");
			res.append("         var submit_url = '" + this.submitUrl + "';");
			res.append("         window.location = submit_url.replace('"+ TAG +"', page_size);");
			res.append("      }");
			res.append("}");
			res.append("</script>");


		}else{
			res.append("<table align='center' style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>總共<font color='red'>0</font>條記錄，當前顯示0-0條記錄。</td></tr></table>");
		}
		this.getJspContext().getOut().print(res.toString());
	}


	/** 計算中間頁碼的方法 */
	private void calcPage(StringBuilder str) {
		/** 判斷總頁數 */
		if (this.totalPage <= 11){
			/** 一次性顯示全部的頁碼 */
			for (int i = 1; i <= this.totalPage; i++){
				if (this.pageIndex == i){
					/** 當前頁碼 */
					str.append("<span class='current'>"+ i +"</span>");
				}else{
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
				}
			}
		}else{
			/** 靠近首頁 */
			if (this.pageIndex <= 8){
				for (int i = 1; i <= 10; i++){
					if (this.pageIndex == i){
						/** 當前頁碼 */
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}
				str.append("...");
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
				str.append("<a href='"+ tempUrl +"'>"+ this.totalPage +"</a>");
			}
			/** 靠近尾頁 */
			else if (this.pageIndex + 8 >= this.totalPage){
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+ tempUrl +"'>1</a>");
				str.append("...");

				for (int i = this.totalPage - 10; i <= this.totalPage; i++){
					if (this.pageIndex == i){
						/** 當前頁碼 */
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}
			}
			/** 在中間 */
			else{
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+ tempUrl +"'>1</a>");
				str.append("...");

				for (int i = this.pageIndex - 4; i <= this.pageIndex + 4; i++){
					if (this.pageIndex == i){
						/** 當前頁碼 */
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}

				str.append("...");
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
				str.append("<a href='"+ tempUrl +"'>"+ this.totalPage +"</a>");
			}
		}
	}

	/** setter 方法 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}
	public void setStyle(String style) {
		this.style = style;
	}
}