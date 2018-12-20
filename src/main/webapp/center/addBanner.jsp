<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript">
    $(function () {
        $("#addSub").linkbutton({
            iconCls: "icon-save",
            onClick: function () {
                $("#addForm").form("submit", {
                    url: "${pageContext.request.contextPath}/banner/insertBanner",
                    onSubmit: function () {
                        $("#addForm").form("validate");
                    },
                    success: function (data) {
                        if (data) {
                            $("#add-dialog").window("close");
                            $("#banner-table").datagrid("reload");
                            $.messager.show({
                                title: "成功",
                                msg: '数据添加成功'
                            })
                        } else {
                            $.messager.show({
                                title: "失败",
                                msg: '数据添加错误'
                            })
                        }
                    }
                });
            }
        })
        $("#addName").textbox({
            required: true,
        });
        $("#addStatus").combobox({
            required: true,
            editable: false,
            valueField: "id",
            textField: "text",
            url: "../center/banner_json.jsp",
        });
        $("#addDescription").textbox({
            required: true,
        });
        $("#addImg").filebox({
            required: true,
            editable: false,
            buttonText: "添加图片",
            multiple: false,
        });
        $("#addRest").linkbutton({
            iconCls: "icon-clear",
            onClick: function () {
                $("#addForm").form("reset");
            }
        })
    });
</script>

<form id="addForm" method="POST" enctype="multipart/form-data">
    <table>

        <tr>
            <td>简名：</td>
            <td><input id="addName" name="name"></td>
        </tr>
        <tr>
            <td>状态：</td>
            <td><input id="addStatus" name="status"></td>
        </tr>
        <tr>
            <td>描述：</td>
            <td><input id="addDescription" name="description"></td>
        </tr>
        <tr>
            <td>图片：</td>
            <td><input id="addImg" name="uploadImg"></td>
        </tr>
        <tr>
            <td><a id="addSub">保存</a></td>
            <td><a id="addRest">重置</a></td>
        </tr>

    </table>
</form>
