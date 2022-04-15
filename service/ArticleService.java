package com.zx.service;

import java.util.List;

import com.zx.bean.Article;
import com.zx.dao.ArticleDao;
import com.zx.util.pager.PageModel;

public class ArticleService {

	/**
	 * @return
	 * 获取所有的商品信息
	 */
	private ArticleDao articleDao = new ArticleDao();
	//导完ArticleDao去查商品信息

	public List<Article> getAllArticle(String typeCode, String keyword, PageModel pageModel) {
		// TODO Auto-generated method stub
		//这个百分号"%"是数据库查询语句中要用的
		typeCode= typeCode==null? "%":typeCode+"%";
		
		//给传进来的keyword加个判断
		//没有查询关键字就是查所有的数据 也就是"%%"的全匹配
		keyword = keyword == null ? "%%" : "%"+keyword+"%";
		
		// 查询总记录数
		int totalNum = articleDao.getTotalNum(typeCode, keyword);
		// 将总记录数封装至pageModel对象中
		pageModel.setTotalNum(totalNum);
		
		List<Article> articles = articleDao.getAllArticle(typeCode, keyword, pageModel);
		
		return articles;//把上面得到的数据返回出去
	}

	/**
	 * @param id
	 * @return
	 * 根据商品id获取商品信息
	 */
	public Article getArticleById(String id) {
		//化成整型就可以根据这个id调用articleDao进数据库取数据了
		return articleDao.getArticleById(Integer.valueOf(id));
	}

}
