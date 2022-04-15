<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>注册页面</title>
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="resources/css/taobao.css" rel="stylesheet" />
<script src="resources/jquery/jquery.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/bootstrap/js/bootstrapValidator.min.js"></script>

	<script type="text/javascript">
		function formSubmit(){
			//校验用户输入的信息是否合法
			var loginName = document.getElementById("loginName").value;
			var name = document.getElementById("name").value;
			var password = document.getElementById("password").value;
			var okPassword = document.getElementById("okPassword").value;
			var phone = document.getElementById("phone").value;
			var address = document.getElementById("address").value;
			var email = document.getElementById("email").value;
			
			var tip = "";
			if(!/^[a-zA-Z0-9]{5,16}$/.test(loginName)){
				tip="您输入的账号不合法";
			}else if(!/^[a-zA-Z0-9_\u4E00-\u9FA5]{2,10}$/.test(name)){
				tip="您输入的名字不合法";
			}else if(!/^[a-zA-Z0-9_]{2,10}$/.test(password)){
				tip="您输入的密码不合法";
				alert("校验密码");
			}else if(!/^[a-zA-Z0-9_]{2,10}$/.test(okPassword)){
				tip="您输入的确认不合法";
				alert("校验确认密码");
			}else if(password != okPassword){
				tip="两次输入的密码不一致，请重新输入";
				alert("对比确认");
			}else if(!/^[0-9]{11}$/.test(phone)){
				tip="您输入的手机号码不合法";
// 			}else if(!/^[a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z0-9])(\w|\-){3,20}$/.test(email)){
// 				tip="您输入的邮箱不合法";
// 			}else if(!/^w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*$/.test(email)){
//  				tip="您输入的邮箱不合法";
			}
			
			//如果有字段校验不通过则给出用户提示，表单不需要提交
			if(tip){
				alert(tip);
// 				document.getElementById("message").innerHTML = tip;
			}else{
				//校验通过
				alert("开始提交");
				document.getElementById("registerForm").submit();
				alert("已提交");
			}
			
		}
		
		$(function(){
			// 为登录名绑定失去焦点事件
			$("#loginName").blur(function(){
				// 获取用户输入的登录名
				var val = this.value;
				//alert(val);
				
				if(val){
					// 发送异步请求校验用户输入的信息是否合法	url		data	callback	type
					$.ajax({
						url : "${pageContext.request.contextPath}/validName.action",
						type : "post",
						data : "loginName="+val,
						dataType : "text",
						success : function(result){
							if(result){
								$("#nameMsg").html("<font color='red'>"+ result + "</font>");
								// 用户名存在 清空输入框 用户重新输入
								$("#loginName").val("");
							}
							
						},error : function(){
							alert("网络加载异常!");
						}
						
					})
				}
				
			}).focus(function(){
				$("#nameMsg").html("");
			})
			
		})
		
		
	</script>

</head>
<body>
	<!-- 横幅导航条开始 -->
	<%@ include file="/WEB-INF/view/front/nav/nav.jsp" %>
	<!--  横幅下方的主体开始 -->
	
	<div class="container">
		<div class="row info-content">
			<form id="registerForm" class="form-horizontal" method="post"
				action="${ctx}/register.action" style="margin-top: 20px;">
				
<!-- 				//提示开始 -->
<!-- 				<div class="form-group"> -->
<!-- 					<label class="col-sm-2 control-label"></label> -->
<!-- 					<div class="col-sm-3"> -->
<!-- 						<span style="color: red;" id="message"></span> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
				<div class="form-group">
					<label class="col-sm-2 control-label">登录名称</label>
					<div class="col-sm-3">
						<input type="text" value="" id="loginName" name="loginName"
							class="form-control" placeholder="请输入您的登陆名称">
						<span id="nameMsg"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">真实姓名</label>
					<div class="col-sm-3">
						<input type="text" id="name" value="" name="name"
							class="form-control" placeholder="请输入您的真实姓名">
					</div>

				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">登陆密码</label>
					<div class="col-sm-3">
						<input type="password" id="password" name="password"
							class="form-control" placeholder="请输入您的密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-3">
						<input type="password" id="okPassword" name="okpassword"
							class="form-control" placeholder="请输入您的确认密码">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-3">
						<label>男:</label><input name="sex" value="1" type="radio"
							checked="checked"> <label>女:</label><input
							name="sex" value="2" type="radio">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">角色</label>
					<div class="col-sm-3">
						<label>会员:</label><input name="role" value="1" type="radio"
							checked="checked"> 
						<label>管理员:</label><input
							name="role" value="2" type="radio">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">联系电话</label>
					<div class="col-sm-3">
						<input type="text" id="phone" value="" name="phone"
							class="form-control" placeholder="请输入您的电话">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">地址</label>
					<div class="col-sm-8">
						<textarea id="address" name="address" rows="4" cols="30"
							class="form-control" placeholder="请输入您的地址信息"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">电子邮箱:</label>
					<div class="col-sm-3">
						<input type="text" id="email" value="" name="email"
							class="form-control" placeholder="请输入您的邮箱">
					</div>
					<div class="col-sm-3">
						<span style="color: red;">${message}</span>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-3">
						<button onclick="formSubmit()" type="button" id="btn_submit" class="btn btn-success">
							<span class="glyphicon glyphicon-user">注册</span>
						</button>
						<button type="reset" class="btn btn-info">
							<span class="glyphicon glyphicon-edit">重置</span>
						</button>
					</div>
				</div>
			</form>
		</div>
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>

		<script>
			$('#registerForm').bootstrapValidator({
				//       live: 'disabled',
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					"name" : {
						validators : {
							notEmpty : {
								message : '真实姓名不能为空'
							}
						}
					},
					"loginName" : {
						message : '用户名验证失败',
						validators : {
							notEmpty : {
								message : '用户名不能为空'
							},
							stringLength : {
								min : 5,
								max : 30,
								message : '用户名长度5到30位'
							}
						}
					},
					"email" : {
						validators : {
							notEmpty : {
								message : '邮箱不能为空'
							},
							emailAddress : {
								message : '邮箱格式不正确'
							}
						}
					},
					"phone" : {
						validators : {
							notEmpty : {
								message : '电话不能为空'
							},
							phone : {
								message : '电话号码错误',
								country : 'CN'
							}
						}
					},
					"password" : {
						validators : {
							notEmpty : {
								message : '密码不能为空'
							},
							different : {
								field : 'user.loginName',
								message : '用户名和密码不能相同'
							}
						}
					},
					"okpassword" : {
						validators : {
							notEmpty : {
								message : '确认密码不能为空'
							},
							identical : {
								field : 'user.password',
								message : '两次密码不相同'
							},
							different : {
								field : 'user.loginName',
								message : '用户名和密码不能相同'
							}
						}
					},
					"address" : {
						validators : {
							notEmpty : {
								message : '地址不能为空'
							}
						}
					}
				}
			});

			$('#btn_submit').click(function() {
				$('#registerForm').bootstrapValidator('validate');
			});
		</script>
	</div>
	<!--  横幅下方的主体结束 -->
</body>
</html>