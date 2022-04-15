package com.zx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zx.bean.Article;
import com.zx.bean.Order;
import com.zx.bean.OrderItem;
import com.zx.util.ConnectionFactory;

public class OrderDao {



	/**
	 * @param order
	 * @return
	 * //保存订单并获取id
	 */
	public int saveOrder(Order order) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//定义sql语句	查所有的商品
			String sql = "insert into ec_order(order_code,create_date,amount,user_id) values(?,?,?,?)";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, order.getOrderCode());
			//这里的setDate的参数得是java.sql类型的date而不能是java.util的
			pstm.setDate(2, new java.sql.Date(order.getCreateDate().getTime()));
			pstm.setDouble(3, order.getAmount());
			pstm.setInt(4, order.getUserId());
			
			//进行数据插入
			int num = pstm.executeUpdate();
			
			if(num == 1) {
				//数据插入成功
				rs = pstm.getGeneratedKeys();
				rs.next();
				//取rs的第一列就好
				int orderId = rs.getInt(1);
				//终于可以返回一直想拿的东西了
				//System.out.print(orderId);
				return orderId;
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

	/**
	 * @param item
	 * 保存订单详情
	 */
	public void saveItem(OrderItem item) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//定义sql语句	查所有的商品
			String sql = "insert into ec_order_item(order_id,article_id,order_num) values(?,?,?)";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, item.getOrderId());
			pstm.setInt(2, item.getArticleId());
			pstm.setInt(3, item.getOrderNum());
			
			System.out.println("进行数据插入");
			//进行数据插入
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
	 * 根据用户id获取订单信息
	 */
	public List<Order> getOrdersByUserId(int id) {
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
			String sql = "SELECT * FROM ec_order WHERE USER_ID = ?";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			//day0601_23m边上
			pstm.setInt(1, id);

			//获取结果集
			rs = pstm.executeQuery();
			
			List<Order> orders = new ArrayList<>();
			//接下来用while取出上面取出的订单中的各个条目
			//需要拿到 订单号 下单时间 发货时间 订单状态
			while(rs.next()) {
				Order order = new Order();
				//在这里要把id也封装进去	要不然哪里用foreach
				order.setId(rs.getInt(id));
				
				order.setOrderCode(rs.getString("order_code"));
				order.setCreateDate(rs.getDate("create_date"));
				order.setSendDate(rs.getDate("send_date"));
				order.setAmount(rs.getDouble("amount"));
				order.setStatus(rs.getString("status"));
				
				orders.add(order);
			}
			return orders;
			
			
			
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
	 * @param id
	 * @return
	 * 根据订单id获取订单详情
	 */
	public List<OrderItem> getItemsByOrderId(int id) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		//while(resultset.next())组合
		
		try {
			//获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//定义sql语句	查所有的商品
			String sql = "SELECT * FROM ec_order_item i,ec_article e WHERE ORDER_ID = ? and e.id = i.article_id";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			//day0601_23m边上
			pstm.setInt(1, id);

			//获取结果集
			rs = pstm.executeQuery();
			
			List<OrderItem> orderItems = new ArrayList<>();
			//接下来用while取出上面取出的订单中的各个条目
			//需要拿到 订单号 下单时间 发货时间 订单状态
			while(rs.next()) {
				OrderItem item = new OrderItem();
				//封装购买的数量
				item.setOrderNum(rs.getInt("order_num"));
				//封装商品相关的信息
				Article article = item.getArticle();
				//SQL里面列名的不区分大小写
				article.setId(rs.getInt("id"));
				article.setImage(rs.getString("image"));
				article.setTitle(rs.getString("title"));
				article.setPrice(rs.getDouble("price"));
				article.setDiscount(rs.getDouble("discount"));
				
				//不用item.setArticle(article); 因为上面已经是item.getArticle()的取法了
				//将article存放在item中
				item.setArticle(article);
				//将item存放在集合中
				orderItems.add(item);
				
			}
			return orderItems;
			
			
			
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
