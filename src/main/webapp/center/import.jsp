<script type="text/javascript">
    $(function () {
        $("#import_file").filebox({
            required: true
        });
        $("#import_sub").linkbutton({
            iconCls: "icon-save",
            onClick: function () {
                $("#import_form").form("submit", {
                    url: "${pageContext.request.contextPath}/user/import",
                    onSubmit: function () {
                        $("#import_form").form("validate");
                    },
                    success: function () {
                        $("#import_dialog").window("close");
                    }
                })
            }
        })
    })
</script>


<form id="import_form" enctype="multipart/form-data" method="post">
    <input id="import_file" name="impFile"><br>
    <a id="import_sub">保存</a>
</form>