$(function(){
    //使用datagrid绑定数据库
    $('#dg').datagrid({
        url:'getDistrict',
        title:"区域信息",
        toolbar:"#ToolBar",
        pagination:true,
        pageSize:5,
        pageList:[3,5,7,10],
        columns:[[
            {field:"cb",checkbox:true},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'区域名称',width:100},
            {field:'s',title:'操作',width:150,
                formatter: function(value,row,index){
                    var str="<a href='javascript:delDistrict("+row.id+");'>删除</a>|<a href='javascript:updateDialog()'>修改</a>|<a href='javascript:OpenshowStreetDialog("+row.id+")'>查看街道</a>";
                    return str;
                }
            }
        ]]

    });
});
//点击添加，打开窗口
function addDialog(){
    $('#AddDialog').window('setTitle',"添加区域");
    $('#AddDialog').window('open');
}
//关闭对话框
function CloseAddDialog(id){
    $('#'+id).window('close');
}
//显示修改的对话框
function updateDialog(){
    //判断用户选择
    //1.获取dagagrid的选中行
    var SelectRows = $("#dg").datagrid('getSelections');  //返回数组
    if(SelectRows.length==1){
        $('#updateDialog').window('setTitle',"编辑区域");
        $('#updateDialog').window('open');
        //获取当前行的数据:获取主键，查询数据库获取单行数据
        //如果当显示的数据在dagagrid中存在，无需查询数据库，如果当显示的数据在datagrid中不全，需要查询数据库获单条
        //发异步请求查询数据库
        $.post("getOneDistrict",{"id":SelectRows[0].id},function(data){
            $("#form2").form('load',data); //将对象还原表单
        },"json");
        //将信息还原到表单中.
        //$("#form1").form('load',json对象);
        //$("#form2").form('load',{"name":"默认值"});  //name表示表单对象名称
        //$("#form2").form('load',SelectRows[0]);  //{"id":1001,"name":"东城"}
    }else{
        $.messager.alert('>>提示','你没有选择行或者选择多行，给我小心点!','warn');  //提示框
    }
}


function SaveDistrict1(){
    //1.获取数据，发送异步请求实现添加
    //$.post("addDistrict",给服务器传参,function(data){},"json");
    /* $.post("addDistrict",{"name":$("#name2").val()},function(data){
         alert(data);
     },"json");*/

    //2.使用easuyi插件以异步方式提交表单
    $('#form1').form('submit',{
        url:"addDistrict",
        success:function(data){  //data表示的字符串
            //将返回的json字符串转化为json对象
            data=$.parseJSON(data);
            if(data.result>0){
                //刷新页面
                $('#dg').datagrid("reload");
                $.messager.alert('>>提示','添加成功！','info');  //提示框
                $('#AddDialog').window('close'); //关闭
            }else{
                $.messager.alert('>>提示','添加失败！','error');
                $('#AddDialog').window('close'); //关闭
            }
        }
    });
}
function SaveDistrict2() {
    $('#form2').form('submit',{
        url:"updateDistrict",
        success:function(data){  //data表示的字符串
            //将返回的json字符串转化为json对象
            data=$.parseJSON(data);
            if(data.result>0){
                $('#dg').datagrid("reload");
                $.messager.alert('>>提示','修改成功！','info');  //提示框
                $('#updateDialog').window('close'); //关闭
            }else{
                $.messager.alert('>>提示','修改失败！','error');
                $('#updateDialog').window('close'); //关闭
            }
        }
    });
}
function delDistrict(id) {
    $.messager.confirm('>>提示','确定删除吗?想好再点!',function (r) {
        if (r) {
            $.post("delDistrict",{"id":id },function (data) {
                if (data.result>0){
                    $('#dg').datagrid("reload");
                } else {
                    $.messager.alert('>>提示','删除失败！','error');
                }
            },"json");
        }

    });
}
//批量删除区域
function deleteMoreDistrict(){
    //1.获取dagagrid的选中行
    var SelectRows = $("#dg").datagrid('getSelections');  //返回数组
    //2.判断是否选择的行
    if(SelectRows.length==0){
        $.messager.alert('>>提示','请选择后再进行删除.','warn');
    }else{
        //确认删除
        $.messager.confirm('>>提示','确定删除吗?想好在点!',function(r){
            if(r){  //删除
                //3.获取选中项的值,拼接字符串 格式:1,2,3
                var val="";
                for (var i=0;i<SelectRows.length;i++){
                    val=val+SelectRows[i].id+",";
                }
                val=val.substring(0,val.length-1);
                //4.发送异步请求到服务器实现删除
                $.post("delMoreDistrict",{"ids":val},function(data){
                    if(data.result>0){
                        //刷新datagrid
                        $('#dg').datagrid("reload");
                    } else{
                        $.messager.alert('>>提示','删除多项失败！','error');
                    }
                },"json");
            }
        });
    }
}
/*打开显示街道的对话框*/
function OpenshowStreetDialog(did){
    $('#showStreetDialog').window('setTitle',"街道信息");
    $('#showStreetDialog').window("open");
    $('#dgStreet').datagrid({
        url:'getStreetByDid?did='+did,
        title:"街道信息",
        toolbar:"#ToolBar",  //指定工具栏
        pagination:true,
        pageList:[3,6,9,15,20],
        pageSize:3,
        //singleSelect:true,
        columns:[[
            {field:"cb",checkbox:true},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'街道名称',width:100,
                formatter: function(value,row,index){
                    var str="<input type='text' value='"+value+"'>";
                    return str;
                }},
            {field:'s',title:'操作',width:200,
                formatter: function(value,row,index){
                    var str="<a href='javascript:updateStreetDialog()'>更新</a>";
                    
                    return str;
                }
            }
        ]]
    });
}
function updateStreetDialog() {
    $.messager.confirm('>>提示','确定修改吗?想好再点!',function (r) {
        if (r) {
            $.post("updateSteet1",{"id":id },function (data) {
                if (data.result>0){
                    $('#dg').datagrid("reload");
                } else {
                    $.messager.alert('>>提示','删除失败！','error');
                }
            },"json");
        }


    })}

