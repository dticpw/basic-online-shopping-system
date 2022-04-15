package com.zx.controller.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.bean.Shopcar;
import com.zx.bean.User;
import com.zx.service.ShopCarService;
import com.zx.util.ConstantUtil;


/**
 * 将商品信息加入至购物车
 */
@WebServlet("/addToCar.do")
public class AddShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShopCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 下面该去查询数据库
     * 加一些service方法
     * 调用service包中的ArticleService类方法
     */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//创建服务层对象
		ShopCarService shopCarService = new ShopCarService();
		
		//获取页面传递的参数
		//商品id
		String id = req.getParameter("id");
		//购买数量
		String number = req.getParameter("number");
		//获取用户信息
		User user = (User)req.getSession().getAttribute(ConstantUtil.SESSION_USER);
		//根据用户信息以及商品id查询购物车详情表 判断该商品是否存在于用户的购物车中
		//最终选择了返回Shopcar对象 这样比较方便
		Shopcar shopcar = shopCarService.getShopCarByUserIdAndArticleId(user.getId(),id);
		if(shopcar != null) {
			// 如果该用户有添加过该商品  进行更新操作
			// 第三个参数应该是Integer.valueOf(number)+shopcar.getBuynum()
			shopCarService.updateShopCar(user.getId(),id,Integer.valueOf(number)+shopcar.getBuynum());
		}else {
			// 用户没有添加过该商品  此时在购物车中添加新的条目
			//shopCarService.addShopCar(user.getId(),id,number+shopcar.getBuynum());
			shopCarService.addShopCar(user.getId(),id,Integer.valueOf(number));
		}
		
		// 重定向至展示购物车中商品信息页面
//		resp.sendRedirect("/showShopCar.do");	//直接这样写会404
		resp.sendRedirect(req.getContextPath()+"/showShopCar.do");
		// req.getContextPath()即项目名
		
	}

	

}
