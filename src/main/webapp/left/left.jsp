<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    $(function () {
        $("#menu").accordion("add", {
            title: "新标题",
            context: "新内容",
            selected: false
        });
    });
</script>
