<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        $("#albName_add").textbox({
            required: true,
        });
        $("#albGName_add").textbox({
            required: true,
        });
        $("#albBor_add").textbox({
            required: true,
        });
        $("#albBri_add").textbox({
            required: true,
        });
        $("#albImg_add").filebox({
            required: true,
        });
        $("#add_album_sub").linkbutton({
            iconCls: "icon-add",
            onClick: function () {
                $("#add_album_form").form("submit", {
                    url: "${pageContext.request.contextPath}/album/insertOne",
                    onSubmit: function () {
                        $("#add_album_form").form("validate");
                    },
                    success: function (result) {
                        if (result) {
                            $.messager.show({
                                title: "成功",
                                msg: "添加专辑成功"
                            })
                            $("#album_add-dialog").window("close");
                            $("#album-table").treegrid("reload");
                        }
                    }
                })
            }
        });
        $("#add_album_rest").linkbutton({
            iconCls: "icon-clear",
            onClick: function () {
                $("#add_album_form").form("clear");
            }
        });
    });
</script>


<form id="add_album_form" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>专辑名：</td>
            <td><input id="albName_add" name="name"></td>
        </tr>
        <tr>
            <td>专辑读者：</td>
            <td><input id="albBor_add" name="broadcast"></td>
        </tr>
        <tr>
            <td>专辑上师：</td>
            <td><input id="albGName_add" name="guru.dharma"></td>
        </tr>
        <tr>
            <td>专辑描述：</td>
            <td><input id="albBri_add" name="brief"></td>
        </tr>
        <tr>
            <td>选择专辑图像：</td>
            <td><input id="albImg_add" name="imgFile"></td>
        </tr>
        <tr>
            <td><a id="add_album_sub">添加</a></td>
            <td><a id="add_album_rest">重置</a></td>
        </tr>
    </table>
</form>