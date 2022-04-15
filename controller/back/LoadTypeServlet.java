package com.zx.controller.back;

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

import net.sf.json.JSONArray;

/**
 * 异步加载二级商品类型信息
 * 把一级商品类型code传进来 从数据库中拿出该一级商品类型下的二级商品类型过来
 */
@WebServlet("/ajaxLoadSeTypes.do")
public class LoadTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadTypeServlet() {
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
		
		ArticleTypeService as = new ArticleTypeService();
		
		// 获取一级商品类型的code
		String code = req.getParameter("code");
		
		// 获取二级商品类型信息
		List<ArticleType> seTypes = as.getSecondTypesByCode(code);
		JSONArray jsonObj = JSONArray.fromObject(seTypes);
		String jsonStr = jsonObj.toString();
		// System.out.println(str);
		resp.getWriter().write(jsonStr);
		
//		req.setAttribute("seTypes", seTypes);
		
	}

	

}
