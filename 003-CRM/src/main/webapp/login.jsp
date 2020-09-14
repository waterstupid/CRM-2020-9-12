<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:
${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		// 页面加载完毕之后
		$(function(){
			// 首先需要将光标定格在输入一栏
			$("#name").focus()
			// 每次设置name框中内容为""
			$("#name").val("")
			// 然后需要判断用户名和密码都不能为空
			// trim函数的作用:清除空格
			$("#bto").click(function(){
				var username=$("#name").val()
				var password=$("#pwd").val()
				// 判断用户名和密码是否为为空
				isNull()

				// 如果程序执行到这,则需要向服务器发送一个ajax请求
				$.post("setting/user/login.do",{"username":username,"password":password},function(data){
					login(data)


				})
			})
			// 给整个页面绑定键盘单击事件
			// 绑定键盘单击事件
			// event代表的是键盘单击事件对象，每个键盘单击事件都有一个Keycode属性
			$(window).keydown(function(event){
				var username=$("#name").val()
				var password=$("#pwd").val()
				// enter的键值为13
				if(event.keyCode==13){
					// 判断用户和密码是否为空
					isNull()
					// 也可以进行登录
					$.post("setting/user/login.do",{"username":username,"password":password},function(data){
						login(data)


					})

				}
			})

		})
		// 该方法用来通过从后端传来的标志位来判断是否登录成功
		function login(data){

			// 从服务器中得来得是json数据
			// 要通过标志符来判断是否登录成功了
			// 数据类型:{"success":false/true}
			if(data.success){
				// 如果为真，则为登录成功,跳转到工作页面
				window.location.href="workbench/index.jsp"

			}else{
				// 如果为假,则为登录失败,需要在<span>标签中输出对应的数据
				// msg由后端传来的异常信息
				$("#msg").html(data.msg)

			}

		}
		// 该方法用来判断用户密码是否为空
		function isNull(){
			var username=$("#name").val()
			var password=$("#pwd").val()
			// 如果用户名和密码都为空,那么就输出错误信息
			if($.trim(username)==""|| $.trim(password)=="" ){
				$("#msg").html("用户名和密码不能为空")
				// 程序执行到这，就需要停止该方法了
				return false
			}

		}


	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.jsp" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="name">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="pwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style="color:red"></span>
						
					</div>
					<button type="button" id="bto" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>