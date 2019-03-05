package org.fkit.hrm.util.tag;

import org.fkit.hrm.util.common.HrmConstants;

/**
 *  分頁實體
 */
public class PageModel {
	/** 分頁總數據條數  */
	private int recordCount;
	/** 當前頁面 */
	private int pageIndex ;
	/** 每頁分多少條數據   */
	private int pageSize = HrmConstants.PAGE_DEFAULT_SIZE = 4;

	/** 總頁數  */
	private int totalSize;

	public int getRecordCount() {
		this.recordCount = this.recordCount <= 0 ? 0:this.recordCount;
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageIndex() {
		this.pageIndex = this.pageIndex <= 0?1:this.pageIndex;
		/** 判斷當前頁面是否超過了總頁數:如果超過了默認給最後一頁作為當前頁  */
		this.pageIndex = this.pageIndex>=this.getTotalSize()?this.getTotalSize():this.pageIndex;

		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		this.pageSize = this.pageSize <= HrmConstants.PAGE_DEFAULT_SIZE?HrmConstants.PAGE_DEFAULT_SIZE:this.pageSize;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		if(this.getRecordCount() <=0){
			totalSize = 0 ;
		}else{
			totalSize = (this.getRecordCount() -1)/this.getPageSize() + 1;
		}
		return totalSize;
	}


	public int getFirstLimitParam(){
		return (this.getPageIndex()-1)*this.getPageSize() ;
	}




}
