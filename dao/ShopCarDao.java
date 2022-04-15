package com.zx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zx.bean.Article;
import com.zx.bean.Shopcar;
import com.zx.util.ConnectionFactory;

public class ShopCarDao {

	/**
	 * @param uid
	 * @param aid
	 * @return boolean
	 * 根据用户信息以及商品id查询购物车详情表 判断该商品是否存在于用户的购物车中
	 */
	public Shopcar getShopCarByUserIdAndArticleId(int uid, int aid) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//1.获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//2.定义sql语句	查所有的商品
			String sql = "select * from ec_shopcar where user_id = ? and article_id = ?";
			//3.准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, uid);
			pstm.setInt(2, aid);
			
			
			//4.进行查询
			rs = pstm.executeQuery();
			
			Shopcar shopcar = null;
			
			//遍历 if有数据就返回true
			if(rs.next()) {
				shopcar = new Shopcar();
				shopcar.setBuynum(rs.getInt("buynum"));
				// rs拿出了那一条数据 可以直接找到buynum属性的数据
			}
			return shopcar;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//关闭连接
			ConnectionFactory.closeCon(rs, pstm, con);
		}
		//没找到
		return null;
	}

	/**
	 * @param uid
	 * @param valueOf
	 * @param buynum
	 * 进行更新操作 更新购物车中商品的购买数量
	 */
	public void updateShopCar(int uid, int aid, int buynum) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//1.获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//2.定义sql语句	查所有的商品
			String sql = "update ec_shopcar set buynum = ? where user_id = ? and article_id = ? ";
			//3.准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, buynum);
			pstm.setInt(2, uid);
			pstm.setInt(3, aid);
			
			
			//4.进行更新
			pstm.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//关闭连接
			ConnectionFactory.closeCon(rs, pstm, con);
		}
		
	}

	/**
	 * @param uid
	 * @param aid
	 * @param buynum
	 * 进行添加新条目到购物车中的操作
	 */
	public void addShopCar(int uid, int aid, int buynum) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//1.获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//2.定义sql语句	查所有的商品
			String sql = "insert into ec_shopcar(buynum,user_id,article_id) values(?,?,?) ";
			//3.准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, buynum);
			pstm.setInt(2, uid);
			pstm.setInt(3, aid);
			
			//4.进行更新
			pstm.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//关闭连接
			ConnectionFactory.closeCon(rs, pstm, con);
		}
		
	}

	/**
	 * @param id
	 * @return
	 * 根据当前用户的id来查该用户的购物车
	 */
	public List<Shopcar> findAllShopCarByUserId(int id) {
		// TODO Auto-generated method stub
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//1.获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//2.定义sql语句	查所有的商品
			String sql = "SELECT * FROM ec_shopcar s, ec_article a WHERE s.article_id = a.id AND s.user_id = ?";
			//3.准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			//4.进行查询
			rs = pstm.executeQuery();
			
			List<Shopcar> list = new ArrayList<>();
			//创建集合用于封装数据
			while(rs.next()) {
				Shopcar shopcar = new Shopcar();
				//封装购买数量 图片 名称 id 价格
				shopcar.setBuynum(rs.getInt("buynum"));
				
				//把商品有关的封装到article中
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setImage(rs.getString("image"));
				article.setTitle(rs.getString("title"));
				article.setPrice(rs.getDouble("price"));
				article.setDiscount(rs.getDouble("discount"));
				article.setStorage(rs.getInt("storage"));
				
				//再把article封装到shopcar中
				shopcar.setArticle(article);
				
				//将商品的信息存放在集合中
				list.add(shopcar);
			}
			
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//关闭连接
			ConnectionFactory.closeCon(rs, pstm, con);
		}
		// 发生异常时会返回这个空
		return null;
		
	}

	/**
	 * @param userId
	 * @param valueOf
	 * 进行删除操作
	 * 这个int和Integer在sql语句中没有差别 都可以setInt()
	 */
	public void deleteShopCar(int userId, Integer articleId) {
		// TODO Auto-generated method stub
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//1.获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//2.定义sql语句	查所有的商品
			String sql = "DELETE FROM ec_shopcar where user_id = ? and article_id = ?";
			//3.准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, userId);
			pstm.setInt(2, articleId);
			
			//4.进行删除语句
			pstm.executeUpdate();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//关闭连接
			ConnectionFactory.closeCon(rs, pstm, con);
		}
	}
}
