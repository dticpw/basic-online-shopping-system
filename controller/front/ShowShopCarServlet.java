package com.zx.controller.front;

import java.io.IOException;
import java.util.List;

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
 * 展示购物车信息
 */
@WebServlet("/showShopCar.do")
public class ShowShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowShopCarServlet() {
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
		//拿用户的信息
		User user = (User)req.getSession().getAttribute(ConstantUtil.SESSION_USER);
		//根据当前用户的id来查该用户的购物车
		List<Shopcar> list = shopCarService.findAllShopCarByUserId(user);
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/view/front/shopcar.jsp").forward(req, resp);
		
	}

	

}
