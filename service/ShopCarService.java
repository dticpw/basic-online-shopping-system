package com.zx.service;


import java.util.List;

import com.zx.bean.Shopcar;
import com.zx.bean.User;
import com.zx.dao.ShopCarDao;


public class ShopCarService {

	private ShopCarDao carDao = new ShopCarDao();
	// 提交订单的时候再减库存，只是加到购物车里面不用减 于是这里不用private ArticleDao
	/**
	 * @param id
	 * @param id2
	 * @return boolean 即是否已在购物车中
	 * 根据用户信息以及商品id查询购物车详情表 判断该商品是否存在于用户的购物车中
	 */
	public Shopcar getShopCarByUserIdAndArticleId(int uid, String aid) {
		// TODO Auto-generated method stub
		return carDao.getShopCarByUserIdAndArticleId(uid, Integer.valueOf(aid));
	}
	/**
	 * @param uid
	 * @param aid
	 * @param buynum
	 * 进行更新操作 更新购物车中已有的商品数量
	 */
	public void updateShopCar(int uid, String aid, int buynum) {
		// TODO Auto-generated method stub
		carDao.updateShopCar(uid,Integer.valueOf(aid),buynum);
	}
	/**
	 * @param uid
	 * @param aid
	 * @param buynum
	 * 进行添加新条目到购物车中的操作
	 */
	public void addShopCar(int uid, String aid, int buynum) {
		// 添加商品至购物车
		carDao.addShopCar(uid,Integer.valueOf(aid),buynum);
		
	}
	/**
	 * @param user
	 * @return
	 * //根据当前用户的id来查该用户的购物车
	 */
	public List<Shopcar> findAllShopCarByUserId(User user) {
		List<Shopcar> shopcars = carDao.findAllShopCarByUserId(user.getId());
		
		return shopcars;
	}
	/**
	 * @param userId
	 * @param aritcleId
	 * 进行删除操作
	 */
	public void deleteShopCar(int userId, String aritcleId) {
		// TODO Auto-generated method stub
		carDao.deleteShopCar(userId,Integer.valueOf(aritcleId));
	}

	

}
