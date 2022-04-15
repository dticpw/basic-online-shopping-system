<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>购物车</title>
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" />

<link href="resources/css/taobao.css" rel="stylesheet" />
<script src="resources/jquery/jquery-1.11.0.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.2.1.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/js/taobao.js"></script>
<script type="text/javascript">
// 	$(function() {
// 		// 全部的单选 
// 		boxs = $("input[type='checkBox'][id^='box_']");

// 		//全选事件 
// 		$("#checkAll").click(function() {
// 			boxs.attr("checked", this.checked);

// 			updateSureNum();
// 		});

// 		// 给每个单选按钮绑定一个点击数事件 
// 		boxs.click(function() {
// 			// 获取当前选中的所有按钮 
// 			var checkedBoxs = boxs.filter(":checked");
// 			// 判断选中的按钮是否等于总的按钮数 等于 全选也应该被选中反之 
// 			$("#checkAll").attr("checked", boxs.length == checkedBoxs.length);

// 			updateSureNum();
// 		});

// 		// 提交订单绑定点击事件 
// 		$("#commitOrder").click(function() {
// 			// 获取当前选中的商品的id 去下订单 
// 			var checkedBoxs = boxs.filter(":checked");
// 			if (checkedBoxs.length > 0) {
// 				// 获取这些单选按钮的value属性即是选中商品的id 
// 				/*  checkedBoxs.each(function(){
// 				  alert(this.value);
// 				 }) */
// 				var maps = checkedBoxs.map(function() {
// 					return this.value;
// 				});

// 				var ids = maps.get();
// 				// 提交到后台进行订单的提交动作 
// 				window.location = "shop/order/addOrder.action?ids=" + ids + "";
// 			} else {
// 				alert("请选择需要购买的商品");
// 			}

// 		})
// 	})

// 	function updateSureNum() {

// 		var checkedBoxs = boxs.filter(":checked");

// 		$(".badge").html(checkedBoxs.length);

// 		var totalMoney = 0.0;

// 		// 计算总价 
// 		checkedBoxs.each(function() {
// 			var id = this.id;
// 			// 获取存放此商品总价的那个id  
// 			var totalId = id.replace("box_", "total_");
// 			// 取此商品的总价 
// 			var money = $("#" + totalId).text();

// 			totalMoney += parseFloat(money); // totalMoney = totalMoney + parseFloat(money)
// 		});

// 		$("#totalMoney").html(totalMoney + "元");
// 	}
	
	function num(flag,articleId,buyNumId,storage){
		
		//获取购买数量 对应 输入框的dom对象
		var domObj = document.getElementById(buyNumId);
		//获取当前购买数量
		var num = parseInt(domObj.value);
		
		if(flag == 'min' && num>1){
			domObj.value = num - 1;
		}else if(flag == 'plus' && num < storage){
			domObj.value = num + 1;
		}
		
		window.location = "${ctx}/updateShopCar.do?articleId="+articleId+"&buynum="+domObj.value;
	}
	
	//删除购物车中商品信息
	function deleteShopcar(id){
		//bom操作
		if(window.confirm("是否确认删除？")){
			window.location =  "${ctx}/deleteShopCar.do?articleId="+id;
		}
	}
	
	//进行全选操作
	function checkALL(obj){
		//获取所有的子checkbox
		var boxes = document.getElementsByName("box");
		for(var i=0; i<boxes.length; i++){
			// 让子box的状态和全选的状态一样就行了
			boxes[i].checked = obj.checked;
		}
		// 修改articleId后面的文本值 给出购物车条目总数量
		document.getElementById("articleNum").innerText = obj.checked ? boxes.length : 0 ;
		
		// 计算提交的订单的总金额
		var totalPrice = 0.0;
		var xjs = document.getElementsByName("xj");
		for(var i=0; i<xjs.length; i++){
			// 网页上显示的那些都算字符串 都用parseFloat转一下
			totalPrice += parseFloat(xjs[i].innerText);
		}
		document.getElementById("totalMoney").innerHTML = obj.checked ? totalPrice.toFixed(2) : "0" ;
		document.getElementById("totalAmount").value = obj.checked ? totalPrice.toFixed(2) : "0" ;

		// 这里用innerText也行
		
		//调用函数用于得到选中的商品id一级购买的商品数量
		getCheckedArticleInfo();
		
	}
	
	// obj参数指示当前框是否被选中 xj为小计栏文本
	function boxcli(obj,xj){
		//alert("articleId:"+articleId+"buynum:"+buynum);
		// 获取下单的条目总数量
		var num = document.getElementById("articleNum").innerText;
		// 获取下单的商品总金额
		var totalPrice = document.getElementById("totalMoney").innerText;
		
		if(obj.checked){
			// 当前checkbox是选中状态
			document.getElementById("articleNum").innerText = parseInt(num)+1;
			document.getElementById("totalMoney").innerText = (parseFloat(totalPrice)+parseFloat(xj)).toFixed(2);
			// totalAmount是隐藏框 要被带到那里去呢
			document.getElementById("totalAmount").value = (parseFloat(totalPrice)+parseFloat(xj)).toFixed(2);
		}else{
			// 未选中
			document.getElementById("articleNum").innerText = parseInt(num)-1;
			document.getElementById("totalMoney").innerText = (parseFloat(totalPrice)-parseFloat(xj)).toFixed(2);
			document.getElementById("totalAmount").value = (parseFloat(totalPrice)-parseFloat(xj)).toFixed(2);
		}
		
		//调用函数用于得到选中的商品id一级购买的商品数量
		getCheckedArticleInfo();
	}
	
	function getCheckedArticleInfo(){
		
		var info = "";
		//获取所有的子checkbox
		var boxes = document.getElementsByName("box");
		for(var i=0; i<boxes.length; i++){
			// 对所有的子box判断当前的box有没有被选中 获取选中的checkbox的value值 
			// value="${shopcar.article.id}_${shopcar.buynum}"
			if(boxes[i].checked){
				info = "#"+boxes[i].value + info;
			}
		}
		
		document.getElementById("articleInfo").value = info;	
	}
	// 提交订单
	function formSubmit(){
		var len = 0;
		var boxes = document.getElementsByName("box");
		for(var i=0; i<boxes.length; i++){
			if(boxes[i].checked){
				len++;
			}
		}
		if(len == 0){
			alert("请选择需要购买的商品！");
		}else{
			//提交表单
			document.getElementById("form").submit();
		}
		
	}
	
</script>

</head>
<body>
	<!-- 横幅导航条开始 -->
	<%@ include file="/WEB-INF/view/front/nav/nav.jsp" %>
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">

			<!-- 内容主体开始 -->
			<div class="col-xs-12 col-sm-12">
				<div>当前位置：我的购物车</div>
				<div class="table-responsive">
					<table class="table table-hover table-bordered table-striped">
						<thead>
							<tr>
								<!-- 这里的this代表当前标签的dom对象 拿到checkbox的状态(是否勾选) -->
								<th><input type="checkbox" id="checkAll" onclick="checkALL(this)"></th>
								<th style="width: 15%">图片</th>
								<th style="width: 32%">名称</th>
								<th style="width: 12%">价格</th>
								<th style="width: 12%">数量</th>
								<th style="width: 14%">小计</th>
								<th style="width: 12%">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${requestScope.list}" var="shopcar" varStatus="stat">
							<tr>
								<td style="vertical-align: middle;"><input type="checkbox"
									name="box" value="${shopcar.article.id}_${shopcar.buynum}" onclick="boxcli(this,'${shopcar.article.discountPrice * shopcar.buynum}')" /> 
									<span id="total_1" style="display: none;">${shopcar.article.price}</span></td>
								<td><img alt="商品图片"
									src="resources/image/article/${shopcar.article.image}"></td>
								
								<td>${shopcar.article.title}</td><!-- 名称 -->
								
								<!-- 价格 和 折后价-->
								<td><span class="price">${shopcar.article.price}</span>&nbsp;
								<span style="color:red"><fmt:formatNumber value="${shopcar.article.discountPrice}" pattern="0.00"></fmt:formatNumber></span></td>
								<!-- 但这里的article.discountPrice是怎么调用到的 -->
								
								<!-- 购买数量栏 -->
								<td><span class="glyphicon glyphicon-minus" style="cursor: pointer;"
									onclick="num('min','${shopcar.article.id}','${stat.index}','${shopcar.article.storage}')"></span>
									
									<input id="${stat.index}" value="${shopcar.buynum}"
									style="width: 40px; text-align: center;"
									onkeydown="return checkInputNumber(event)"
									onchange="updateNumber(1, this.value, 100)" /> 
									
									<span class="glyphicon glyphicon-plus" style="cursor: pointer;"
									onclick="num('plus','${shopcar.article.id}','${stat.index}','${shopcar.article.storage}')"></span>
								</td>
								
								<!-- 小计 -->
								<td name="xj"><fmt:formatNumber value="${shopcar.article.discountPrice * shopcar.buynum}" pattern="0.00"></fmt:formatNumber></td><!-- 小计 -->
								<td><a href="javascript:deleteShopcar('${shopcar.article.id}')"
									onclick="">删除</a>
									<a>收藏</a></td>
							</tr>
						</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
			<!-- 内容主体结束 -->
		</div>
		<!--/row-->

		<form action="${pageContext.request.contextPath}/saveOrder.do" method="post" id="form">
		
		<!-- 存放商品总金额的隐藏输入框 -->
		<input type="text" name="totalAmount" id="totalAmount"/>
		<!-- 存放商品id以及购买数量的隐藏输入框 -->
		<input type="text" name="articleInfo" id="articleInfo"/>
		
		<div align="right">
			总金额:<span id="totalMoney" style="color: red;">0</span>元&nbsp;&nbsp;&nbsp;&nbsp;
			
			<!-- 当然地 button尖括号括在一起的就是button的属性 -->
			<!-- 标签夹住的内容表现在button上 -->
			<button id="commitOrder" class="btn btn-danger" type="button" onclick = "formSubmit()">
				提交订单 <span class="badge" id="articleNum">0</span>
			</button>
		</div>
		</form>
		
		<hr>
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>
	</div>
	<!--/.container-->
	<!--  横幅下方的主体结束 -->
</body>
</html>