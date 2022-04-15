package com.zx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zx.bean.Article;
import com.zx.util.ConnectionFactory;
import com.zx.util.pager.PageModel;

public class ArticleDao {

	/*
	 * 获取所有的商品信息
	 * 这里要连接数据库 快去写数据连接工程
	 * 在com.zx.util下
	 */
	public List<Article> getAllArticle(String typeCode, String keyword, PageModel pageModel){
		
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//1.获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//2.定义sql语句	查所有的商品
			String sql = "select * from ec_article where type_code like ? and title like ? limit ?,?";
			//3.准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setString(1, typeCode);
			pstm.setString(2, keyword);
			pstm.setInt(3, pageModel.getStartNum());	// limit从哪里开始
			pstm.setInt(4, pageModel.getPageSize());	// limit要多少条
			
			
			//4.进行查询
			//查询的时候不用传入sql参数  有参数时pstm自己知道语句是什么  没有?不需要传入参数时可以传入sql
			rs = pstm.executeQuery();
			
			//创建Article集合用于封装数据
			List<Article> articles = new ArrayList<>();
			//遍历
			while(rs.next()) {
				//封装商品信息
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
				article.setPrice(rs.getDouble("price"));
				article.setImage(rs.getString("image"));
				article.setDiscount(rs.getDouble("discount"));
				
				//将商品的信息存放在集合中
				articles.add(article);
			}
			//将上面弄到的商品信息返回
			return articles;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//关闭连接
			ConnectionFactory.closeCon(rs, pstm, con);
		}

		return null;
	}

	/**
	 * @param valueOf
	 * @return
	 * 根据商品id获取商品信息
	 */
	public Article getArticleById(Integer id) {
		// TODO Auto-generated method stub
		
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//定义sql语句	查所有的商品
			String sql = "select * from ec_article where id = ?";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			//进行查询
			rs = pstm.executeQuery();
			
			//只拿一个
			if(rs.next()) {
				//封装商品信息
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
				article.setPrice(rs.getDouble("price"));
				article.setDiscount(rs.getDouble("discount"));
				article.setImage(rs.getString("image"));
				article.setLocality(rs.getString("Locality"));
				article.setSupplier(rs.getString("supplier"));
				article.setStorage(rs.getInt("storage"));
				article.setDescription(rs.getString("description"));

				return article;
			}
			//将上面弄到的商品信息返回
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//关闭连接
			ConnectionFactory.closeCon(rs, pstm, con);
		}
		
		return null;
	}

	/**
	 * @param typeCode
	 * @param keyword
	 * @return
	 * 查询总记录数
	 */
	public int getTotalNum(String typeCode, String keyword) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//1.获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//2.定义sql语句	查所有的商品
			String sql = "select count(*) from ec_article where type_code like ? and title like ?";
			//3.准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setString(1, typeCode);
			pstm.setString(2, keyword);
			
			//4.进行查询
			//查询的时候不用传入sql参数  有参数时pstm自己知道语句是什么  没有?不需要传入参数时可以传入sql
			rs = pstm.executeQuery();

			
			if(rs.next()) {
				return rs.getInt(1);	// get查询结构的第一个参数(count)就好
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//关闭连接
			ConnectionFactory.closeCon(rs, pstm, con);
		}

		return 0;
	}
}
