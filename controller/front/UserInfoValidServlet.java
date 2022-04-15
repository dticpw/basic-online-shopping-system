package com.zx.controller.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.service.UserService;

/**
 * 用户信息校验
 */
@WebServlet("/validName.action")
public class UserInfoValidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoValidServlet() {
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

			// 获取登录名
			String loginName = req.getParameter("loginName");
			// System.out.println(loginName);
			

			String result = us.getUserByName(loginName);
			// result拿到字符串 "用户已存在":""
			
			resp.setCharacterEncoding("utf-8");
			PrintWriter pw = resp.getWriter();
			pw.write(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
