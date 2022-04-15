package com.zx.service;

import java.util.List;

import com.zx.bean.ArticleType;
import com.zx.dao.ArticleTypeDao;

public class ArticleTypeService {

	
	private ArticleTypeDao typeDao = new ArticleTypeDao();
	/**
	 * 获取所有一级类型
	 * @return
	 */
	public List<ArticleType> getAllFType() {
		// TODO Auto-generated method stub
		List<ArticleType> types = typeDao.getAllFType();
		return types;
	}
	
	/*
	 * @param typeCode
	 * @return
	 * 根据一级商品类型获取对应的二级商品类型	传入的是一级商品类型
	 */
	public List<ArticleType> getSecondTypesByCode(String typeCode) {
		// TODO //根据一级商品类型获取对应的二级商品类型	传入的是一级商品类型
		//查询语句为
		//SELECT * FROM ec_article_type WHERE CODE LIKE '0001%' AND LENGTH(CODE)=8;
		List<ArticleType> types = typeDao.getSecondTypesByCode(typeCode+"%");
		return types;
	}

	/**
	 * @return
	 * 获取所有的商品信息
	 */
//	private ArticleDao articleDao = new ArticleDao();
//	//导完ArticleDao去查商品信息
//
//	public List<Article> getAllArticle() {
//		// TODO Auto-generated method stub
//		List<Article> articles = articleDao.getAllArticle();
//		
//		return articles;//把上面得到的数据返回出去
//	}

}
