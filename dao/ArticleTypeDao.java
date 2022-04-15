package com.zx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zx.bean.ArticleType;
import com.zx.util.ConnectionFactory;

public class ArticleTypeDao {

	

	public List<ArticleType> getAllFType() {
		
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		
		try {
			//获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//定义sql语句	查所有的一级类型 只要第一级 即四位的
			String sql = "select * from ec_article_type where length(code) = 4";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			
			//进行查询
			rs = pstm.executeQuery();
			
			//创建Article集合用于封装数据
			List<ArticleType> articleTypes = new ArrayList<>();
			//遍历
			while(rs.next()) {
				//封装商品类型信息
				ArticleType articleType = new ArticleType();
				articleType.setCode(rs.getString("code"));
				articleType.setName(rs.getString("name"));
				//将商品的信息存放在集合中
				articleTypes.add(articleType);
			}
			//将上面弄到的商品信息返回
			return articleTypes;
			
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
	 * @param string
	 * @return
	 * 根据一级商品类型获取对应的二级商品类型	传入的是一级商品类型+"%"
	 * 查询语句为
	 * SELECT * FROM ec_article_type WHERE CODE LIKE '0001%' AND LENGTH(CODE)=8;
	 */
	public List<ArticleType> getSecondTypesByCode(String code) {
		// TODO Auto-generated method stub
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		
		try {
			//获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//定义sql语句	查所有的一级类型 只要第一级 即四位的
			String sql = "SELECT * FROM ec_article_type WHERE CODE LIKE ? AND LENGTH(CODE)=8";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			//这里的setString函数也许就是用来将上面的sql语句中的?替换成传入的变量code的吧
			pstm.setString(1, code);
			
			//进行查询
			rs = pstm.executeQuery();
			
			//创建Article集合用于封装数据
			List<ArticleType> articleTypes = new ArrayList<>();
			//遍历
			while(rs.next()) {
				//封装商品类型信息
				ArticleType articleType = new ArticleType();
				articleType.setCode(rs.getString("code"));
				articleType.setName(rs.getString("name"));
				//将商品的信息存放在集合中
				articleTypes.add(articleType);
			}
			//将上面弄到的商品信息返回
			return articleTypes;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//关闭连接
			ConnectionFactory.closeCon(rs, pstm, con);
		}

		return null;
	}
}
