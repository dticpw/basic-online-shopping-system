package com.zx.controller.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.bean.Article;

import com.zx.service.ArticleService;


/**
 * 展示商品详情信息
 */
@WebServlet("/detail.action")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
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
		ArticleService as = new ArticleService();
		//获取商品id
		String id = req.getParameter("id");
		
		//根据商品id获取商品信息
		Article article = as.getArticleById(id);
		//将商品信息存放至request对象中
		req.setAttribute("article", article);
		
		
		//跳转到商品详情页面
		req.getRequestDispatcher("/WEB-INF/view/front/detail.jsp").forward(req, resp); 
		//WEB-INF下面的view下面的front下面的articleIndex.jsp	//右键copy qualified name
		//req:request resp:response
				
		//服务层得去调用dao层
	}

	

}
