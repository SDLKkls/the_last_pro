<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript">
    $(function () {
        var tb = [{
            text: "添加",
            iconCls: 'icon-add',
            handler: function () {
                $("#add-dialog").window("open");
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                $("#banner-table").edatagrid("editRow");
            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
                $("#banner-table").edatagrid("destroyRow");
            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-save',
            handler: function () {
                $("#banner-table").edatagrid("saveRow");
                $("#banner-table").datagrid("reload");
            }
        }];
        $("#banner-table").edatagrid({
            fitColumns: true,
            url: "${pageContext.request.contextPath}/banner/getAll",
            columns: [[
                {field: "id", width: 1, title: "ID"},
                {field: "name", width: 1, title: "NAME"},
                {
                    field: "status", width: 1, title: "STATUS", editor: {
                        type: "combobox",
                        options: {
                            required: true,
                            editable: false,
                            valueField: "id",
                            textField: "text",
                            url: "../center/banner_json.jsp",
                        }
                    }, formatter: function (value, rows, index) {
                        if (value == 0) return "不可用";
                        if (value == 1) return "可用";
                    }
                },
                {field: "pubDate", width: 1, title: "PUBLIC_DATE"}
            ]],
            fit: true,
            loadMsg: "正在加载，请稍等...",
            pagination: true,
            pageSize: 2,
            pageList: [1, 2, 3, 4, 5],
            pageNumber: 1,
            toolbar: tb,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="../center/img' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p></p>' +
                    '</td>' +
                    '</tr></table>';
            },
            autoSave: true,
            destroyUrl: "${pageContext.request.contextPath}/banner/deleteBanner",
            updateUrl: "${pageContext.request.contextPath}/banner/updateBanner",
            destroyMsg: {
                norecord: {    // 在没有记录选择的时候执行
                    title: '错误',
                    msg: '请选中所要删除的行后再点击本按钮！'
                },
                confirm: {       // 在选择一行的时候执行
                    title: '确认提示',
                    msg: '确认删除此信息？'
                }

            }
        });
        $("#add-dialog").dialog({
            title: "添加新数据",
            width: 260,
            height: 180,
            closed: true,
            cache: false,
            modal: true,
            collapsible: true,
            maximizable: true,
            resizable: true,
            href: "${pageContext.request.contextPath}/center/addBanner.jsp"
        })
    });
</script>

<table id="banner-table"></table>
<div id="add-dialog"></div>