package com.zx.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zx.bean.Order;
import com.zx.bean.OrderItem;
import com.zx.bean.User;
import com.zx.dao.OrderDao;

public class OrderService {
	
	private OrderDao orderDao = new OrderDao();

	/**
	 * @param totalAmount
	 * @param articleInfo
	 * @param user
	 * 保存订单
	 */
	public void saveOrder(String totalAmount, String articleInfo, User user) {
		
		Order order = new Order();
		
		//添加日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuffer sb = new StringBuffer();
		sb.append("PO-").append(sdf.format(new Date())).append(user.getId());
		
		//设置订单编号 OR-yyyymmddhhmmss-userId
		order.setOrderCode(sb.toString());
		
		//设置下单时间
		order.setCreateDate(new Date());
		//指定用户信息
		order.setUserId(user.getId());
		//指定订单总金额
		order.setAmount(Double.valueOf(totalAmount));
		
		//保存订单并获取id
		int orderId = orderDao.saveOrder(order);
		
		//保存订单之后获取订单id，插入至订单明细中 
		//#2_1#1_1 => 2_1#1_1
		articleInfo = articleInfo.substring(1);
		//按照 # 进行切分 => {2_1,1_1}
		String[] aInfos = articleInfo.split("#");
		
		System.out.println("articleInfo");
		
		for(String info : aInfos) {
			//获取商品id与购买数量
			String[] id_num = info.split("_");
			OrderItem item = new OrderItem();
			item.setArticleId(Integer.valueOf(id_num[0]));
			item.setOrderNum(Integer.valueOf(id_num[1]));
			
			item.setOrderId(orderId);
			orderDao.saveItem(item);
			
			
		}
		
		
	}

	/**
	 * @param id
	 * @return
	 * 根据用户id获取订单信息
	 * 那么现在如何封装订单的信息
	 */
	public List<Order> getOrdersByUserId(int id) {
		
		List<Order> orders = orderDao.getOrdersByUserId(id);
		
		for(int i=0;i<orders.size();i++) {
			//根据订单id获取订单详情
			//根据id获取那么封装的时候id得封进去
			List<OrderItem> items = orderDao.getItemsByOrderId(orders.get(i).getId());
			//将订单详情存放在订单中
			orders.get(i).setItems(items);
		}
		
		return orders;
	}

	

}
