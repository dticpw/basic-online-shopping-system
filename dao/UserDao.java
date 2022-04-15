package com.zx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zx.bean.User;
import com.zx.util.ConnectionFactory;

public class UserDao {

	/**
	 * @param loginName
	 * @param passWord
	 * @return
	 */
	public User getUserByNameAndPass(String loginName, String passWord) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try {
			//获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//定义sql语句	查所有的商品
			String sql = "select * from ec_user where login_name = ? and password = ?";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setString(1, loginName);
			pstm.setString(2, passWord);
			
			//进行查询
			rs = pstm.executeQuery();
			
			//只拿一个
			if(rs.next()) {
				User u =new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setRole(rs.getInt("role"));
				u.setDisabled(rs.getString("disabled"));
				u.setEmail(rs.getString("email"));
				return u;
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
	 * @param user
	 * 保存用户
	 */
	public void saveUser(User user) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try {
			//获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//定义sql语句	查所有的商品
//			String sql = "select * from ec_user where login_name = ? and password = ?";
			String sql = "insert into ec_user(login_name,password,name,sex,email,phone,address,create_date,active,role) values(?,?,?,?,?,?,?,?,?,?)";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getLoginName());
			pstm.setString(2, user.getPassword());
			pstm.setString(3, user.getName());
			pstm.setInt(4, user.getSex());
			pstm.setString(5, user.getEmail());
			pstm.setString(6, user.getPhone());
			pstm.setString(7, user.getAddress());
			pstm.setDate(8, new java.sql.Date(user.getCreateDate().getTime()));
			pstm.setString(9, user.getActive());
			
			pstm.setInt(10, user.getRole());
			//这下面不用把string类型的sql作为参数传到excuteUpdate()函数中去吗？
			//进行更新
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
	 * @param activeCode
	 * 用户信息激活
	 * 将该用户那一栏的disable从0改为1代表已激活可用 disabled是String类型
	 */
	public void activeUser(String activeCode) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try {
			//获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//定义sql语句	查所有的商品
			String sql = "update ec_user set disabled = '1' where active = ?";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setString(1, activeCode);
			
			//进行更新
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
	 * @param loginName
	 * @return
	 * 根据账号获取用户信息
	 */
	public boolean getUserByName(String loginName) {
		Connection  con =null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try {
			//获取连接
			con = ConnectionFactory.getCon();
			
			//准备需要发送的sql语句
			//定义sql语句	查所有的商品
			String sql = "select * from ec_user where login_name = ?";
			//准备PreparedStatement对象 用于发送sql语句
			pstm = con.prepareStatement(sql);
			pstm.setString(1, loginName);
			
			//进行查询
			rs = pstm.executeQuery();
			
			//只拿一个
			if(rs.next()) {
				// System.out.println("用户存在");
				return true;
			}
			//将上面弄到的商品信息返回
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//关闭连接
			ConnectionFactory.closeCon(rs, pstm, con);
		}
		
		return false;
	}
}
