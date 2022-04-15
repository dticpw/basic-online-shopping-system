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
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="resources/css/taobao.css" rel="stylesheet" />
<script src="resources/jquery/jquery.js"></script>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/js/taobao.js"></script>
</head>
	<script>
	//校验用户输入的数据是否合法	onblur="validNum(this)
		function validNum(obj){
			//获取用户输入的值
			var val=obj.value;
			if(isNaN(val) || val<1){
				obj.value=1;
			}else{
				//向上取整
				obj.value = Math.ceil(obj.value) > "${article.storage}" ?
						"${article.storage}" : Math.ceil(obj.value) ;
			}
		}
	</script>


<body>
	<!-- 横幅导航条开始 -->
	<%@ include file="/WEB-INF/view/front/nav/nav.jsp" %>
	<!--  横幅下方的主体开始 -->
	<div class="container">

		<div class="row row-offcanvas row-offcanvas-right">

			<!-- 内容主体开始 -->
			<div class="col-xs-12 col-sm-12">
				<div class="col-xs-12 col-sm-6">
					<img alt="商品的图片" src="${ctx}/resources/image/article/${article.image}">
				</div>
				<div class="col-xs-12 col-sm-6">
					<p>${article.title}</p>
					<p>${article.locality}</p>
					<p>${article.supplier}</p>
					<p>
						<!-- 价格 和 折扣价 横线是class="price"规定的 -->
						<span class="price">${article.price}</span>
						<span style="color:red"><fmt:formatNumber value="${article.price * article.discount}" pattern="0.00"></fmt:formatNumber></span>

					</p>
					<p>
						库存量：<span id="storage">${article.storage}</span>
					</p>
					<p>
					<!-- 这里的后缀是 .do 方便过滤器是识别 -->
					<form method="get" action="${ctx}/addToCar.do">
						<input type="hidden" name="id" value="${article.id}" />
						<span
							style="font-weight: bold; font-size: 18px; cursor: pointer; margin-left: 10px; margin-right: 10px;"
							onclick="$('#number').val() - 1 < 1 ? $('#number').val('1') : $('#number').val($('#number').val() - 1)">-</span>
						<input id="number" onblur="validNum(this)" name="number" value="1" style="width: 50px;" />
						
						<!-- $('#number').val() 使用JQuery封装的val()方法获取txtNumber标签中的value属性值，赋值给变量num -->
						<!-- onblur属性会在用户离开输入 字段时对其验证-->
						<span
							style="font-weight: bold; font-size: 18px; cursor: pointer; margin-left: 10px; margin-right: 10px;"
							onclick="$('#number').val() - 0 + 1 > $('#storage').text() ? $('#number').val($('#storage').text()) : $('#number').val($('#number').val() - 0 + 1);">+</span>
						<button>
							<span class="glyphicon glyphicon-shopping-cart"
								style="color: red;"></span>加入购物车
						</button>
					</form>

				</div>
				<div class="col-xs-12">
					<fieldset>
						<legend>介绍</legend>
						${article.description }
					</fieldset>
				</div>
			</div>
			<!--/.col-xs-12.col-sm-9-->
			<!-- 内容主体结束 -->
		</div>
		<!--/row-->
		<hr>
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>

	</div>
	<!--/.container-->
	<!--  横幅下方的主体结束 -->

</body>
</html>