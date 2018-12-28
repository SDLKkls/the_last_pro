<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
    <script type="text/javascript" src="../js/china.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        var goEasy = new GoEasy({
            appkey: 'BC-2b66fbf505a54de1a1ca0b060dc1be20'
        });
        $(function () {
            /*发送ajax查询数据库中的所有一级分类*/
            $.ajax({
                type: "POST",
                dataType: "JSON",
                url: "${pageContext.request.contextPath}/menu/getmenu",
                success: function (result) {
                    /*遍历构建一级分类*/
                    $.each(result, function (index, value) {
                        $("#menu").accordion("add", {
                            title: value.text,
                            selected: false,
                            content: "<div><ul id='tree" + value.id + "'></ul></div>",
                        });
                        /*发送ajax查询构建出的当前一级分类下包含的所有二级分类，并将二级分类构建成树*/
                        $.ajax({
                            type: "POST",
                            dataType: "JSON",
                            url: "${pageContext.request.contextPath}/menu/gettree",
                            data: "pid=" + value.id,
                            success: function (result) {
                                $("#tree" + value.id).tree({
                                    data: result,
                                    onClick: function (node) {
                                        if ($("#tt").tabs("exists", node.text)) {
                                            $("#tt").tabs("select", node.text);
                                        } else {
                                            $("#tt").tabs("add", {
                                                fit: true,
                                                title: node.text,
                                                iconCls: node.iconcls,
                                                href: "${pageContext.request.contextPath}/center" + node.url,
                                                closable: true
                                            });
                                        }
                                    }
                                });
                            }
                        });
                    });
                }
            });
            $("#tt").tabs("add", {
                title: "首页",
                iconCls: "icon-neighbourhood",
                href: "${pageContext.request.contextPath}/main/img.jsp"
            });
            $("#dialog-change").dialog({
                closed: true,
                title: "修改密码",
                width: 400,
                height: 200,
                cache: false,
                modal: true
            });
            $("#changeadminpass").linkbutton({
                iconCls: "icon-edit",
                text: "修改密码",
                onClick: function () {
                    $("#dialog-change").window("open");
                }
            });


        });
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 400px;float:right;padding-top:15px">
        欢迎您:${sessionScope.admin.realName}
        &nbsp;<a id="changeadminpass" href="#"></a>&nbsp;&nbsp;<a href="#"
                                                                  class="easyui-linkbutton"
                                                                  data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south'" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单'" style="width:220px;">
    <div id="menu" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
    </div>
</div>
<div id="dialog-change"></div>
</body>
</html>