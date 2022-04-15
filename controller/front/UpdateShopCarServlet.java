package com.zx.controller.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.bean.User;
import com.zx.service.ShopCarService;
import com.zx.util.ConstantUtil;


/**
 * 更新购物车中商品的购买数量
 */
@WebServlet("/updateShopCar.do")
public class UpdateShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShopCarServlet() {
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
		String id = req.getParameter("articleId");
		//购买数量
		String number = req.getParameter("buynum");
		//获取用户信息
		User user = (User)req.getSession().getAttribute(ConstantUtil.SESSION_USER);
		
		//  进行更新操作
		shopCarService.updateShopCar(user.getId(),id,Integer.valueOf(number));
		
		
		// 重定向至展示购物车中商品信息页面
//		resp.sendRedirect("/showShopCar.do");	//直接这样写会404
		resp.sendRedirect(req.getContextPath()+"/showShopCar.do");
		// req.getContextPath()即项目名
		
	}

	

}
