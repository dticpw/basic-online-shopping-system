<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 横幅导航条开始 -->

	<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">显示导航条</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${ctx}/index.action">疯狂购物</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${ctx}/index.action">首页</a></li>
					<li><a href="${ctx}/showShopCar.do">购物车</a></li>
					<li><a href="${ctx}/showOrder.do">我的订单</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"> <span style='color: red;'></span>
					</a></li>
					
					<!-- 做个判断 判断是否已登录 -->
					<c:choose>
						<%-- 什么时候显示的是登录呢？ --%>
						<%-- c:choose中注释不是<!--而是<%-- --%>
						<c:when test="${empty session_user}">
							<li><a href="${ctx}/login.action">登录</a></li>
						</c:when>
						<c:otherwise>
							<%-- href="#"只是链接到当前页面 --%>
							<li><a href="#">欢迎【<font color="red">${session_user.name}</font>】登录</a></li>
							<li><a href="${ctx}/logout.action">退出登录</a></li>
						</c:otherwise>
						
					</c:choose>
					
					
					<li><a href="${ctx}/register.action">免费注册</a></li>
					<c:if test="${session_user.role != 1}">
						<li><a href="${ctx}/mindex.do">后台管理</a></li>
					</c:if>
					
				</ul>
			</div>
			<!-- /.nav-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<!-- 横幅导航条结束 -->