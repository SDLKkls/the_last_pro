<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        $("#chp_name").textbox({
            required: true,
        });
        $("#chp_alId").combobox({
            required: true,
            editable: false,
            url: "${pageContext.request.contextPath}/album/getSimpleAll",
            valueField: "id",
            textField: "name",
        });
        $("#chp_status").combobox({
            url: "${pageContext.request.contextPath}/center/banner_json.jsp",
            valueField: "id",
            textField: "text",
            required: true,
            editable: false,
        });
        $("#chp_mp3").filebox({
            required: true,
        });
        $("#add_chapter_sub").linkbutton({
            iconCls: "icon-add",
            onClick: function () {
                $("#add_chapter_form").form("submit", {
                    url: "${pageContext.request.contextPath}/chapter/insertOne",
                    onSubmit: function () {
                        $("#add_chapter_form").form("validate");
                    },
                    success: function (result) {
                        if (result) {
                            $.messager.show({
                                title: "成功",
                                msg: "添加章节成功"
                            })
                            $("#chapter_add-dialog").window("close");
                            $("#album-table").treegrid("reload");
                        }
                    }
                })
            }
        });
        $("#add_chapter_rest").linkbutton({
            iconCls: "icon-clear",
            onClick: function () {
                $("#add_album_form").form("clear");
            }
        });
    });
</script>


<form id="add_chapter_form" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>章节名：</td>
            <td><input id="chp_name" name="name"></td>
        </tr>
        <tr>
            <td>所属专辑：</td>
            <td><input id="chp_alId" name="albumId"></td>
        </tr>
        <tr>
            <td>章节状态：</td>
            <td><input id="chp_status" name="status"></td>
        </tr>
        <tr>
            <td>选择文件：</td>
            <td><input id="chp_mp3" name="upMp"></td>
        </tr>
        <tr>
            <td><a id="add_chapter_sub">添加</a></td>
            <td><a id="add_chapter_rest">重置</a></td>
        </tr>
    </table>
</form>