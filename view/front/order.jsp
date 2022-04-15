<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh_CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品详情</title>
    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
     <link rel="stylesheet" href="resources/bootstrap/style.css" />
    <link href="resources/css/taobao.css" rel="stylesheet" />
   <script src="resources/jquery/jquery.js"></script>
     
  </head>
  
  <body>

	

  
 <!-- 横幅导航条开始 -->
    <%@ include file="/WEB-INF/view/front/nav/nav.jsp" %>
	<!--  横幅下方的主体开始 -->
    <div class="container">
				<div class="table-responsive">
				<table class="table table-hover table-bordered table-striped">
				
					<c:forEach var="order" items="${orders}">
					<thead>
						<tr style="background-color:green">
							<th style="width: 13%">订单号</th>
							<th style="width: 50%">下单时间</th>
							<th style="width: 10%">发货时间</th>
							<th style="width: 8%">订单状态</th>
							<th style="width: 9%">订单总金额</th>
						</tr>
					</thead>
					<tbody>
					
							<tr>
								<!-- 订单号 -->
								<td>
									${order.orderCode }
								</td>
								<!-- 下单时间 -->
								<td>
									 <fmt:formatDate value="${order.createDate}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
								</td>
								<!-- 发货时间 -->
								<td>
									<fmt:formatDate value="${order.sendDate }" pattern="yyyy年MM月dd日  HH:mm:ss"/>
								</td>
								<!-- 订单状态 -->
								<td>
									<!-- 这里直接 == 0 唔 结果上来说是成功了 但为什么string类型的能直接等于不加引号的 0 -->
									${order.status==0 ? '未发货' : '已发货' }
								</td>
								
								<!-- 订单总金额 -->
								<td>
									${order.amount }
								</td>
							</tr>
						
					</tbody>
					
					<!-- forEach这里面items当然得=<list>型 -->
					<c:forEach items="${order.items}" var="item">
						<thead>
							<tr>
								<th style="width: 15%">图片</th>
								<th style="width: 50%">名称</th>
								<th style="width: 10%">价格</th>
								<th style="width: 8%">数量</th>
								<th style="width: 7%">操作</th>
							</tr>
						</thead>
						<tbody>

							<tr>
								<!-- 图片 -->
								<td>
									<img alt="商品图片" src="${ctx}/resources/image/article/${item.article.image}">
								</td>
								<!-- 名称 -->
								<td>
									 ${item.article.title}
								</td>
								<!-- 价格 -->
								<td>
									<span class="price">${item.article.price}</span>
									
									<fmt:formatNumber value="${item.article.discountPrice }" pattern="0.00"></fmt:formatNumber>
								</td>
								<!-- 数量 	购买数量是直接放在item里的而不是article里-->
								<td>
									${item.orderNum }
								</td>
								<td>
<!-- 									<a href="shop/delete.action?id=36" -->
<!-- 										onclick="return confirm('确定要把\n  摩托罗拉 T5 公众对讲机 7折疯抢，便携之王，最具性价比，出游首选，数量有限，先到先得。  \n从购物车中删除吗？')">删除</a> -->
<!-- 									<a>收藏</a> -->
										<a href="${ctx}/detail.action?id=${item.article.id}">查看商品</a>
								</td>
							</tr>
						
						</tbody>
					
					</c:forEach>
					
					
	
					
					
					</c:forEach>
				</table>
			</div>
				 

      <footer>
        <p>&copy; 版权所有，欢迎借鉴</p>
      </footer>

    </div><!--/.container-->
    <!--  横幅下方的主体结束 -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   
    <script src="resources/bootstrap/js/bootstrap.js"></script>

    <script src="resources/js/taobao.js"></script>
  </body>
</html>