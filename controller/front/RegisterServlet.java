package com.zx.controller.front;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.bean.User;
import com.zx.service.UserService;

//import com.zx.bean.User;
//import com.zx.service.UserService;
//import com.zx.util.ConstantUtil;

/**
 * 跳转至注册页面
 * 处理注册请求
 */
@WebServlet("/register.action")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 跳转至注册页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/front/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService us = new UserService();
		
		//获取用户注册时提交的信息 用servlet取的话只能用request一个一个地取 学过框架后会好很多
		String loginName = request.getParameter("loginName");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String okpassword = request.getParameter("okpassword");
		String sex = request.getParameter("sex");
		String role = request.getParameter("role");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		//注册时应该校验账号时是否存在，建议使用异步请求进行校验 
		if(!password.equals(okpassword)) {
			//提示用户两次密码不一致 注册失败
			request.setAttribute("message", "两次密码不一致，注册失败");
			
		}else {
			try {
				//保存用户信息
				User user = new User();
				user.setLoginName(loginName);
				user.setName(name);
				user.setAddress(address);
				user.setCreateDate(new Date());
				user.setEmail(email);
				user.setPassword(password);
				user.setPhone(phone);
				user.setSex(Integer.valueOf(sex));
				user.setRole(Integer.valueOf(role));
				
				us.saveUser(user,request.getContextPath());
				
				//尝试注册 注册成功则放出提示信息
				request.setAttribute("message", user.getRole() == 1 ? "注册成功,请登录邮箱【"+user.getEmail()+"】进行激活！" : "注册成功，正在审核中！");
				
			} catch (Exception e) {
				request.setAttribute("message", e.getMessage());
			}
			//跳回页面
			request.getRequestDispatcher("/WEB-INF/view/front/register.jsp").forward(request, response);
			
		}
		
	}

}
