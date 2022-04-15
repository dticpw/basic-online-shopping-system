<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- alt+'/'补全好帮手 -->
<!-- 如何快速添加这样的路径呢 -->
<!-- 引入c标签 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath }" scope="application"></c:set>
<!-- scope="application"范围为全局 -->

<!-- 重定向至  article/index -->
<c:redirect url="index.action"></c:redirect>