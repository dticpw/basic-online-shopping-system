package com.zx.controller.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.service.UserService;

/**
 * Servlet implementation class IndexServlet
 * 激活用户信息
 * index.action在WebContent/index.jsp中也有其的重定向
 */
@WebServlet("/userActive.action")
public class ActiveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 下面该去查询数据库
     * 加一些service方法
     */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			UserService us = new UserService();
			
			//获取激活码
			String activeCode = req.getParameter("active");
			
			us.activeUser(activeCode);
			req.setAttribute("mess", "恭喜您，激活成功！");
			
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("mess", "本次激活失败"+e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		//跳转到登录页面
		req.getRequestDispatcher("/WEB-INF/view/front/login.jsp").forward(req, resp); 
		//WEB-INF下面的view下面的front下面的articleIndex.jsp	//右键copy qualified name
		//req:request resp:response
				
		//服务层得去调用dao层
	}

	

}
