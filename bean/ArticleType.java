package com.zx.bean;

/**
 * ArticleType 数据传输类
 * @version 1.0
 */
public class ArticleType implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String code;//商品类型code 4位代表一级类型 8位代表二级类型
	private String name;//商品类型名字
	private String remark;//备注信息

	/** setter and getter method */
	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return this.code;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}

}