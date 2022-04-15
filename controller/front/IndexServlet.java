package com.zx.controller.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.bean.Article;
import com.zx.bean.ArticleType;
import com.zx.service.ArticleService;
import com.zx.service.ArticleTypeService;
import com.zx.util.pager.PageModel;

/**
 * Servlet implementation class IndexServlet
 * 访问 前台|买家 商品首页
 * index.action在WebContent/index.jsp中也有其的重定向
 */
@WebServlet("/index.action")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
		
		//获取商品类型
		String typeCode = req.getParameter("typeCode");
		req.setAttribute("typeCode", typeCode);
		//获取查询关键字
		String keyword = req.getParameter("keyword");
		//将用户输入的关键字存储 保留在搜索栏
		req.setAttribute("keyword", keyword);
		
		// 创建分页实体用于封装分页相关的信息
		PageModel pageModel = new PageModel();
		
		// 获取页码
		String pageIndex = req.getParameter("pageIndex");
		if(pageIndex != null && !pageIndex.equals("") ) {
			pageModel.setPageIndex(Integer.valueOf(pageIndex));
		}
		
		//创建服务层对象
		ArticleService as = new ArticleService();
		//创建商品类型服务层对象
		ArticleTypeService articleTypeService = new ArticleTypeService();
		
		
		//获取所有商品信息	这个方法在com.zx.service.ArticleService下
		List<Article> articles = as.getAllArticle(typeCode, keyword, pageModel);
		//ctrl+左键	跳转至函数定义
		//将商品信息存放至request对象中
		req.setAttribute("articles", articles);
		//request在当次的请求的url之间有效一次传参数，速度快，缺点是参数只能取一次
		
		//获取商品的一级类型
		List<ArticleType> types = articleTypeService.getAllFType();
		//将一级商品类型的信息存储
		req.setAttribute("types", types);
		// 存pageModel
		req.setAttribute("pageModel", pageModel);
		
		
		//typeCode不为空且不为空串
		if(typeCode != null && !typeCode.equals("")) {
			//获取一级类型的code
			String firstCode = typeCode.substring(0, 4);
			req.setAttribute("firstCode", firstCode);
			//根据一级商品类型获取对应的二级商品类型	传入的是一级商品类型  二级的话截前面4位变成一级的
			List<ArticleType> seTypes = articleTypeService.getSecondTypesByCode(firstCode);
				req.setAttribute("seTypes", seTypes);
		}
		
		
		
		
		
		
		
		
		
		//跳转到首页
		req.getRequestDispatcher("/WEB-INF/view/front/articleIndex.jsp").forward(req, resp); 
		//WEB-INF下面的view下面的front下面的articleIndex.jsp	//右键copy qualified name
		//req:request resp:response
				
		//服务层得去调用dao层
	}

	

}
