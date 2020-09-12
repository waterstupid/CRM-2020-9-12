<%--
  Created by IntelliJ IDEA.
  User: fmk
  Date: 2020/9/8
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
    https://localhost:8080/项目名/
--%>
<script src="js/js.js"></script>
<script type="text/javascript">
    $(function(){
        // 给点击按钮绑定事件
        $("#button01").click(function(){
            // 页面加载完毕开始执行
            $.ajax({
                url:"firstajax.do",
                data:"name=zhangsan&age=12",
                type:"get",
                dataType:"json",
                success:function(data){
                    $.each(data,function(a,i){
                        alert(i)
                        alert(i.name)
                        alert(i.age)
                    })
                }
            })
        })

        $.get("json1.do",function(data){
            alert(data)
            alert(data.name)
            alert(data,age)

        })

        /**
         * 采用get的请求方式
         */
      /*  $.get("json.do","json",function(data){
            alert(data)
            alert(data.name);
            alert(data.age);
        })
*/


    })


</script>
<div align="center" id="div01">
    点击:<button id="button01"></button>



</div>


