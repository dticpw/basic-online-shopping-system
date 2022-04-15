package com.zx.bean;

/**
 * OrderItem 数据传输类
 * @version 1.0
 */
public class OrderItem implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int orderId;
	private int articleId;
	private int orderNum;
	
	private Article article = new Article();

	/** setter and getter method */
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	public int getOrderId(){
		return this.orderId;
	}
	public void setArticleId(int articleId){
		this.articleId = articleId;
	}
	public int getArticleId(){
		return this.articleId;
	}
	public void setOrderNum(int orderNum){
		this.orderNum = orderNum;
	}
	public int getOrderNum(){
		return this.orderNum;
	}
	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}
	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	
}