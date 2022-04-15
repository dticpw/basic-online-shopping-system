package com.zx.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;



public class ConnectionFactory {

	//构建数据源
	//没连上就检查一下数据库名 账号密码啥啥的
	private static BasicDataSource dataSource = new BasicDataSource();
	
	static {
		//指定数据库连接的url地址
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/cosmeticsshop?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
		//指定数据库驱动
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		//账号
		dataSource.setUsername("root");
		//密码
		dataSource.setPassword("Cao12342234");
		//最大活动数
		//dataSource.setMaxActive(100);
		//最大保留数
		dataSource.setMaxIdle(50);
		//超时等待时间5s
		//dataSource.setMaxWait(5000);
	}
	
	
	//获取连接
	public static Connection getCon() {
	
		try {
			return dataSource.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//关闭连接
	public static void closeCon(ResultSet rs,PreparedStatement pstm,Connection con) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstm!=null) {
				pstm.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
