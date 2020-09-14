<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:
${pageContext.request.serverPort}${pageContext.request.contextPath}/">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>


	<script type="text/javascript">



	$(function(){
		// 需要调用分页插件
		pageList(1,2)
		// 这个是做日期按钮的
		// 然后需要用插件来调试日期控件
		$(".time").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});
		// 给点击搜索绑定单击事件
		$("#select-btn").click(function(){
			pageList(1,2)

		})
		// 给创建按钮绑定单击事件
		$("#createBtn").click(function(){
			pageList(1,2)
			// 采用ajax请求查询用户表中的所有内容
			$.get(
					"workbench/activity/getUser.do",
					function(data){
						// 从后端传来的数据应该是一个json数组
						//  该data是一个Json数组
						var $Obj=$("#create-owner")
						var html=""
						$.each(data,function(index,object){
							// 给下拉框添加下拉项
							// $("#create-marketActivity").append("<option value='"+object.id+"'>"+object.name+"<option>")
							// $Obj.append("<option value='"+object.id+"'>"+object.name+"<option>")
							// $Obj.append("<option value='"+object.id+"'>"+1+"<option>")
							html+="<option value='"+object.id+"'>"+object.name+"</option>"
						})
						$Obj.html(html)
					}
			)
			// 然后需要在所有者框中显示当前用户
			// 使用EL表达式，需要注意一点是:EL表达式在js中使用需要用""括起来
			var id="${user.id}"
			$("#create-activity").val(id);

			// 将模块窗口打开
			$("#createActivityModal").modal("show")

		})
		// 点击保存事件发送一个ajax请求
		$("#saveBtn").click(function(){
			$.post(
					"workbench/activity/save.do",
					{
						"name":$("#create-marketActivityName").val(),
						"startDate":$("#create-startDate").val(),
						"endDate":$("#create-endDate").val(),
						"cost":$("#create-cost").val(),
						"description":$("#create-describe").val(),
					},
					function(data){
						if(data.success){
							// 添加成功则把模态框close
							$("#createActivityModal").modal("hide")
							pageList(1,2)
						}else{
							// 如果失败，就弹出添加失败
							alert("添加失败，请重新添加")
						}
					}
			)
		})
		// 需要给多选框绑定事件
		$("#qx").click(function(){
			// 首先需要找到所有单选框
			$("input[name='dx']").prop("checked",this.checked)
		})
		// 然后需要给所有单选框绑定事件
		// 由于单选框是动态生成的,不能直接使用事件
		// 而是需要找到其有效外部元素,然后调用.on()方法
		// on()方法有三个参数，第一个是需要绑定的事件,第二个参数就是要绑定事件的标签，第三个就是回调函数
		$("#tBody").on("click","input[name='dx']",function(){
			$("#qx").prop("checked",$("input[name='dx']").size()==$("input[name='dx']:checked").size())
		})



	});
	// 首先要动态查询出activity表中的数据
	// 需要动态查询出activity表中数据有以下几种情况
	// 1.首先点击市场活动的时候，需要查出第一页
	// 2.然后点击创建，修改，删除，查询的时候也需要动态查询数据
	// 3.点击分页按钮的时候需要动态查询数据
	// 所以要将这个操作写成一个方法,便于重复使用
	// 该方法用来查询表中的数据
	function pageList(pageNo,pageSize){
		// 首先使用ajax来完成这次局部刷新
		// 因为是查询操作，所以使用get
		$.get(
				"workbench/activity/selectActivityList.do",
				{
					"pageNo":pageNo,
					"pageSize":pageSize,
					"name":$("#select-name").val(),
					// 注意这个参数我们需要查询的是姓名
					// 而不是32位的字符
					"owner":$("#select-owner").val(),
					"startDate":$("#select-startDate").val(),
					"endDate":$("#select-endDate").val()
				},
				function(data){
					// 注意:我们从后端传来的数据应该是一个List<Activity>
					// 但是因为我们要做分页查询,所以也要一个参数就是total,查出来的总条数
					// json--->{"total":"","dataList":["activity1",....]}
					var html=""
					alert(1)
					$.each(data.dataList,function(n,a){
						alert(n)
						html+="<tr class=\"active\">"
						html+="<td><input type=\"checkbox\" name='dx'/></td>"
						html+="<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='detail.jsp';\">"+a.name+"</a></td>"
						html+="<td>"+a.owner+"</td>"
						html+="<td>"+a.startDate+"</td>"
						html+="<td>"+a.endDate+"</td>"
						html+="</tr>"
					})
					$("#tBody").html(html)
					var pageCount=data.total % pageSize==0 ? data.total / pageSize : parseInt(data.total / pageSize) + 1
					// 这个是做分页处理的
						$("#activityPage").bs_pagination({
						currentPage: pageNo, // 页码
						rowsPerPage: pageSize, // 每页显示的记录条数
						maxRowsPerPage: 20, // 每页最多显示的记录条数
						totalPages:pageCount,
						totalRows: data.total, // 总记录条数
						visiblePageLinks: 3, // 显示几个卡片
						showGoToPage: true,
						showRowsPerPage: true,
						showRowsInfo: true,
						showRowsDefaultInfo: true,
						// 当点击分页框的页数的时候
						onChangePage: function (event,data) {
							// 需要在点击分页框的时候来从隐藏域中取出数据
							// 在每次pageList方法开始的时候，需要从隐藏域中取出数据
							$("#select-name").val($("#select-hidden-name").val())
							$("#select-owner").val($("#select-hidden-owner").val())
							$("#select-startDate").val($("#select-hidden-startDate").val())
							$("#select-endDate").val($("#select-hidden-endDate").val())
							pageList(data.currentPage, data.rowsPerPage);
						}
					});
					// 需要在pageList最后将搜索框中四个input域中保存数据
					$("#select-hidden-name").val($("#select-name").val())
					$("#select-hidden-owner").val($("#select-owner").val())
					$("#select-hidden-startDate").val($("#select-startDate").val())
					$("#select-hidden-endDate").val($("#select-endDate").val())
				}
		)





	}
</script>
</head>
<body>
<%--
	隐藏域是用来保留页数
--%>
<input type="hidden" id="select-hidden-name">
<input type="hidden" id="select-hidden-owner">
<input type="hidden" id="select-hidden-startDate">
<input type="hidden" id="select-hidden-endDate">

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">
								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-marketActivityName">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate">
							</div>
							<label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endDate">
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="saveBtn" class="btn btn-primary" data-dismiss="modal">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">
								  <option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option>
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startTime" value="2020-10-10">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endTime" value="2020-10-20">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="select-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="select-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="select-startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="select-endDate">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="select-btn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
					<%--
						由于我们需要在模块窗口打开之前来做一些操作，所以不能直接在属性中书写对应的属性
					--%>
				  <button type="button" id="createBtn" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#editActivityModal"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="tBody">

					</tbody>
				</table>
			</div>
			<%--
				这个div是用来做分页处理的
			--%>
			<div style="height: 50px; position: relative;top: 30px;" id="activityPage">

			</div>
			
		</div>
		
	</div>
</body>
</html>