<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
    var aid;
    $(function () {
        var tool = [{
            text: "专辑详情",
            iconCls: 'icon-add',
            onClick: function () {
                var node = $("#album-table").treegrid("getSelected");
                if (isNaN(node.id)) {
                    aid = node.albumId;
                } else {
                    aid = node.id;
                }
                $("#album_msg-dialog").window("open");
            }
        }, '-', {
            text: "添加专辑",
            iconCls: 'icon-edit',
            onClick: function () {
                $("#album_add-dialog").window("open");
            }
        }, '-', {
            text: "添加章节",
            iconCls: 'icon-remove',
            handler: function () {
                $("#chapter_add-dialog").window("open");
            }
        }, '-', {
            text: "下载音频",
            iconCls: 'icon-save',
            handler: function () {
                var node = $("#album-table").treegrid("getSelected");
                if (isNaN(node.id)) {
                    window.location.href = "${pageContext.request.contextPath}/chapter/download?id=" + node.id;
                }
            }
        }, '-', {
            text: "导出专辑",
            iconCls: 'icon-save',
            handler: function () {
                window.location.href = "${pageContext.request.contextPath}/album/exportExcel";
            }
        }];
        $("#album-table").treegrid({
            fit: true,
            fitColumns: true,
            url: "${pageContext.request.contextPath}/album/getAll",
            idField: "id",
            treeField: "name",
            columns: [[
                {field: "name", title: "专辑/章节名", width: 1},
                {field: "audioAddr", title: "下载路径", width: 1},
                {
                    field: "as", title: "播放", width: 1, formatter: function (value, rows, index) {
                        if (isNaN(rows.id)) {
                            return "<audio controls='controls' src='${pageContext.request.contextPath}/center/mp3" + rows.audioAddr + "'></audio>";
                        }
                    }
                },
                {
                    field: "size", title: "章节大小", width: 1, formatter: function (value, rows, index) {
                        if (value != null)
                            return value + "M";
                    }
                },
                {
                    field: "duration", title: "章节时长", width: 1, formatter: function (value, rows, index) {
                        if (value != null)
                            return value + "分钟"
                    }
                },
            ]],
            loadMsg: "正在加载，请稍等...",
            pagination: true,
            pageSize: 2,
            pageList: [1, 2, 3, 4, 5],
            pageNumber: 1,
            toolbar: tool,
            onLoadSuccess: function () {
                $("#album-table").treegrid("collapseAll");
            }
        });
        $("#album_msg-dialog").dialog({
            title: "专辑详情",
            width: 260,
            height: 400,
            closed: true,
            cache: false,
            modal: true,
            collapsible: true,
            maximizable: true,
            resizable: true,
            href: "${pageContext.request.contextPath}/center/album_msg.jsp"
        });
        $("#album_add-dialog").dialog({
            title: "添加专辑",
            width: 400,
            height: 400,
            closed: true,
            cache: false,
            modal: true,
            collapsible: true,
            maximizable: true,
            resizable: true,
            href: "${pageContext.request.contextPath}/center/addAlbum.jsp"
        });
        $("#chapter_add-dialog").dialog({
            title: "添加章节",
            width: 400,
            height: 400,
            closed: true,
            cache: false,
            modal: true,
            collapsible: true,
            maximizable: true,
            resizable: true,
            href: "${pageContext.request.contextPath}/center/addChapter.jsp"
        });
    });
</script>

<table id="album-table"></table>
<div id="album_msg-dialog"></div>
<div id="album_add-dialog"></div>
<div id="chapter_add-dialog"></div>