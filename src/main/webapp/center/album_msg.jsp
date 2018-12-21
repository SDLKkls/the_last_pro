<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        $.ajax({
            type: "POST",
            data: "id=" + aid,
            dataType: "JSON",
            url: "${pageContext.request.contextPath}/album/getOneById",
            success: function (result) {
                $("#albImg").prop("src", "${pageContext.request.contextPath}/center/img" + result.img);
                $("#albId").textbox({
                    value: result.id,
                    readonly: true
                });
                $("#albcNum").textbox({
                    value: result.chapterNum,
                    readonly: true
                });
                $("#albGName").textbox({
                    value: result.name,
                    readonly: true
                });
                $("#albBor").textbox({
                    value: result.broadcast,
                    readonly: true
                });
                $("#albRat").textbox({
                    value: result.rating,
                    readonly: true
                });
                $("#albPub").datebox({
                    value: result.pubDate,
                    readonly: true
                });
                $("#albBri").textbox({
                    value: result.brief,
                    readonly: true
                });
            }
        });

    });
</script>


<table>
    <tr>
        <td>专辑名：</td>
        <td><input id="albId"></td>
    </tr>
    <tr>
        <td>章节数量：</td>
        <td><input id="albcNum"></td>
    </tr>
    <tr>
        <td>上师：</td>
        <td><input id="albGName"></td>
    </tr>
    <tr>
        <td>读者：</td>
        <td><input id="albBor"></td>
    </tr>
    <tr>
        <td>评分：</td>
        <td><input id="albRat"></td>
    </tr>
    <tr>
        <td>发布时间：</td>
        <td><input id="albPub"></td>
    </tr>
    <tr>
        <td>描述</td>
        <td><input id="albBri"></td>
    </tr>
    <tr>
        <td colspan="2"><img width="220" height="140" id="albImg"></td>
    </tr>
</table>