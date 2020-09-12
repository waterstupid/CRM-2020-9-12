<%--
  Created by IntelliJ IDEA.
  User: fmk
  Date: 2020/9/8
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/js.js"></script>
<script type="text/javascript">
    $(function(){
        // 首先用val()来对表单中的数据进行存取
        $("#button01").click(function(){
            var value=$("#username").val();
            alert(value)
            // 给表单的数据进行赋值
            $("#username").val("张三")
        })

      /*  $("#button02").click(function(){
            $.get("fourajax.do",function(data){
                $("#body").html("<td>${name}<td>")


            })


        })*/
        $("#button02").click(function(){
            $.get("fourajax.do",function(data){
                /*alert(data[0].name)
                alert(data[0].age)*/
                $.each(data,function(index,value){
                    // html元素可以动态显示数据，但是不能连续添加数据
                    $("#div2").html("<font color='red'>"+value.name+"</font>")
                    $("#div2").html("<font color='red'>"+value.age+"</font>")

                })

            })



        })
        $("#button03").click(function(){
            $.get("fourajax.do",function(data){
                $.each(data,function(index,value){
                    alert(index)
                    if(index===0){
                        $("#body").append("<td>"+value.name+"</td>").append("<td>"+value.age+"</td>")
                    }else{
                        $("#foot").append("<td>"+value.name+"</td>").append("<td>"+value.age+"</td>")
                    }
                })



            })



        })
        $("#button04").click(function(){
            $.get("fourajax.do",function(data){
                /*alert(data[0].name)
                alert(data[0].age)*/
                $.each(data,function(index,value){
                    // html元素可以动态显示数据，但是不能连续添加数据
                    $("#div2").text("<font color='red'>"+value.name+"</font>")
                    $("#div2").text("<font color='red'>"+value.age+"</font>")

                })

            })



        })

    })



</script>
<div align="center">
    <form>
        用户姓名:<input type="text" name="username" id="username"><br>
        密码:<input type="password" name="password" id="password"><br><br>
        登录:<input type="submit">
    </form>
    <button id="button01" ></button><br><br>
    <input type="button" id="button02"><br>
    <input type="button" id="button03"><br>
    <input type="button" id="button04"><br>
    <table>
        <tr>
            <td>姓名</td>
            <td>年龄</td>
        </tr>
        <tbody id="body">

        </tbody>
        <tfoot id="foot">

        </tfoot>

    </table>
</div><br><br>
<div id="div2" align="center" >

</div>

