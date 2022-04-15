package com.zx.controller.front;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.bean.User;
import com.zx.service.UserService;
import com.zx.util.ConstantUtil;

/**
 * 跳转至登录页面
 * 处理登录请求
 */
@WebServlet("/login.action")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * nav.jsp中登录用的是a标签 即 get方法
	 * 这里的doGet方法的功能即是跳转到登录页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/front/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginName = request.getParameter("loginName");
		String passWord = request.getParameter("passWord");
		
		UserService us = new UserService();
		//根据账号密码获取用户信息
		User user = us.getUserByNameAndPass(loginName,passWord);
		
		if(user == null)
		{
			//跳转至登录页面
			request.setAttribute("mess", "账号或密码不正确");
			request.getRequestDispatcher("/WEB-INF/view/front/login.jsp").forward(request, response);
		}else if(user.getDisabled().equals("0")) {
			// 此即已经提交注册信息到数据库中但还没有激活的用户 或者还未得到超级管理员认证的管理员
			request.setAttribute("mess", user.getRole() == 1 ? "您尚未激活，请登录邮箱【"+user.getEmail()+"】进行激活！" : "请联系超级管理员进行审核！");
			request.getRequestDispatcher("/WEB-INF/view/front/login.jsp").forward(request, response);
			
		}else {
			request.getSession().setAttribute(ConstantUtil.SESSION_USER,user);
			//重定向至首页 贴心地加上了项目路径
			response.sendRedirect(request.getContextPath()+"/index.action");
		}
		
		
	}

}
