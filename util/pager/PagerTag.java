/**
 * 
 */
package com.zx.util.pager;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class PagerTag extends TagSupport {
	
	//请求地址
	private String submitUrl;
	//分页相关信息
	private PageModel pageModel;

	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		System.out.println("----doEndTag----");
		return super.doEndTag();
	}

	
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		
		/* <a href="javascript:goPage(1)">首页</a> <a
				href='javascript:goPage(1)'>上一页</a> <a
				href='javascript:goPage(2)'>下一页</a> <a
				href='javascript:goPage(7)'>尾页</a> &nbsp; <span>第&nbsp;
				1&nbsp;页/共&nbsp; 7页 ，共 52 条数据 </span>*/
		StringBuffer page = new StringBuffer();
		try {
			JspWriter write =  this.pageContext.getOut();
			
			//判断总数量 :如果获取到的总记录数 小于 每页需要显示的记录数，则不需要做分页    比如 每页显示 8条  但是总共只查询到5条数据 
			if(pageModel.getTotalNum()>pageModel.getPageSize()){
				
				
				//计算总页数
				int totalPage = pageModel.getTotalNum() % pageModel.getPageSize() == 0 ?pageModel.getTotalNum() /  pageModel.getPageSize() : pageModel.getTotalNum() /  pageModel.getPageSize() +1;
				
				
				//判断首页是否可以点击
				if(pageModel.getPageIndex()==1){
					//当前页码在第一页
					page.append("<a href='#'>首页</a>");
					page.append("<a href='#'>上一页</a>");
					
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageModel.getPageIndex()+1)+"'>下一页</a>");
					
					page.append("<a href='"+submitUrl+"&pageIndex="+totalPage+"'>尾页</a>");
					
					page.append("&nbsp; <span>第&nbsp;"+pageModel.getPageIndex()+"&nbsp;页/共&nbsp; "+totalPage+"页 ，共 "+pageModel.getTotalNum()+"条数据 </span>");
				
				//当前页码是尾页
				}else if(pageModel.getPageIndex() == totalPage){
					//当前页码在尾页
					page.append("<a href='"+submitUrl+"&pageIndex="+1+"'>首页</a>");
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageModel.getPageIndex()-1)+"'>上一页</a>");
					
					page.append("<a href='#'>下一页</a>");
					
					page.append("<a href='#'>尾页</a>");
					
					page.append("&nbsp; <span>第&nbsp;"+pageModel.getPageIndex()+"&nbsp;页/共&nbsp; "+totalPage+"页 ，共 "+pageModel.getTotalNum()+"条数据 </span>");
					
				}else{
					//当前页码在中间
					page.append("<a href='"+submitUrl+"&pageIndex="+1+"'>首页</a>");
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageModel.getPageIndex()-1)+"'>上一页</a>");
					
                    page.append("<a href='"+submitUrl+"&pageIndex="+(pageModel.getPageIndex()+1)+"'>下一页</a>");
					
					page.append("<a href='"+submitUrl+"&pageIndex="+totalPage+"'>尾页</a>");
					
					page.append("&nbsp; <span>第&nbsp;"+pageModel.getPageIndex()+"&nbsp;页/共&nbsp; "+totalPage+"页 ，共 "+pageModel.getTotalNum()+"条数据 </span>");
				}
				
				
			}
			
			write.print(page.toString());
			
			write.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return SKIP_BODY;
	}



	/**
	 * @return the submiturl
	 */
	public String getSubmitUrl() {
		return submitUrl;
	}


	/**
	 * @param submiturl the submiturl to set
	 */
	public void setSubmitUrl(String submitUrl) {
		System.out.println("submiturl:"+submitUrl);
		this.submitUrl = submitUrl;
	}


	/**
	 * @return the pageModel
	 */
	public PageModel getPageModel() {
		return pageModel;
	}


	/**
	 * @param pageModel the pageModel to set
	 */
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}	
	   
}
