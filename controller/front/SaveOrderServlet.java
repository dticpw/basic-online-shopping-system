package com.zx.controller.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.bean.User;
import com.zx.service.OrderService;
import com.zx.util.ConstantUtil;

/**
 * 保存订单信息
 */
@WebServlet("/saveOrder.do")
public class SaveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 下面该去查询数据库
     * 加一些service方法
     */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 创建service对象
		OrderService os = new OrderService();
		
		//拿用户信息 
		User user = (User)req.getSession().getAttribute(ConstantUtil.SESSION_USER);
		
		// 获取订单总金额
		String totalAmount = req.getParameter("totalAmount");
		// 获取商品id以及购买数量
		String articleInfo = req.getParameter("articleInfo");
		//保存订单
		os.saveOrder(totalAmount,articleInfo,user);
		
		
		//重定向至展示订单信息页面
		resp.sendRedirect(req.getContextPath()+"/showOrder.do");
//		req.getRequestDispatcher("/WEB-INF/view/front/login.jsp").forward(req, resp); 
		//WEB-INF下面的view下面的front下面的articleIndex.jsp	//右键copy qualified name
		//req:request resp:response
				
		//服务层得去调用dao层
	}

	

}
