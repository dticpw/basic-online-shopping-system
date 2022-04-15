<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录页面</title>
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="resources/css/taobao.css" rel="stylesheet" />
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/jquery/jquery.js"></script>

<script type="text/javascript">
	function formcommit(){
// 		alert(1);
		//获取用户输入的账号
		var loginNameObj = document.getElementById("loginName");
		//获取用户输入的密码
		var passWordObj = document.getElementById("passWord");
// 		alert(loginNameObj.value);
// 		alert(passWordObj.value);
		
		if(!/^[a-zA-Z0-9]{5,12}$/.test(loginNameObj.value)){
			document.getElementById("mess").innerHTML = "<font color='red'>账号不合法！</font>";
		}else if(!/^[a-zA-Z0-9]{5,12}$/.test(passWordObj.value)){
			document.getElementById("mess").innerHTML = "<font color='red'>密码不合法！</font>";
		}else{
// 			alert(3);
			//账号和密码都合法才提交表单
			document.getElementById("loginForm").submit();
		}
	}
</script>

</head>
<body>
	<!-- 横幅导航条开始 -->
	<%@ include file="/WEB-INF/view/front/nav/nav.jsp" %>
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<!-- 入门-->
		<!-- 登录界面 -->
		<div class="page-header">
			<h1>用户登录</h1>
		</div>
		
		<!-- post请求 form -->
		<form id="loginForm" class="form-horizontal" method="post"
			action="${ctx}/login.action">
			<div class="form-group">
				<div class="col-sm-4">
					<input class="form-control" value="" placeholder="用户名/邮箱"
						type="text" id="loginName" name="loginName" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-4">
					<input class="form-control" placeholder="密码" id="passWord"
						type="password" name="passWord" />
				</div>
			</div>
			
			<!-- 放提示 -->
			<div class="form-group">
				<div class="col-sm-4">
					<span style="color: red;" id="mess" >${mess}</span>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-4">
					<div class="btn-group btn-group-justified" role="group"
						aria-label="...">
						
						<div class="btn-group" role="group">
							<button type="button" onclick="formcommit()" id="loginBtn" class="btn btn-success">
								<span class="glyphicon glyphicon-log-in" ></span>&nbsp;登录
							</button>
<!-- 							<button> -->
<!-- 								<span onclick="formcommit()"></span>&nbsp;登录 -->
<!-- 							</button> -->
						</div>
						<div class="btn-group" role="group">
							<a href="${ctx}/register.action">
							<button type="button" class="btn btn-danger">
								<span class="glyphicon glyphicon-edit"></span>注册
							</button></a>
						</div>
					</div>
				</div>
			</div>
		</form>
		<hr>

		<!-- 彈出框-->
		<div id="myModal" class="modal bs-example-modal-sm fade">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">登录提示</h4>
					</div>
					<div class="modal-body">
						<p id="tip"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button id="sure" type="button" class="btn btn-primary"
							data-dismiss="modal">确定</button>
					</div>
				</div>
			</div>
		</div>

		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>

	</div>
	<!--/.container-->
	<!--  横幅下方的主体结束 -->
</body>
</html>