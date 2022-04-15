package com.zx.util.pager;

/**
 * 分页实体：用来封装分页相关的信息   比如：当前页码   每页显示多少条数据   总记录数
 */
public class PageModel {

	 private Integer pageIndex = 1;//当前页码
	 private Integer pageSize = 8;//每页显示的记录数  
	 private Integer totalNum;//总记录数
	 // 比起int integer有可以接收null的优势
	  
	  
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	
	//计算查询的开始行号   0   8  16
	public int getStartNum(){
		           
		return (this.getPageIndex()-1)*this.getPageSize();
	}
	  
	  
}
