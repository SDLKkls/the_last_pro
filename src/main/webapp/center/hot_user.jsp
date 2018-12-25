<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="statistics_main" style="width: 600px;height: 400px;;margin-top: 30px;margin-left: 30px"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('statistics_main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '持名法州App活跃用户'
        },
        tooltip: {},
        legend: {
            data: ['用户数量']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '数量',
            type: 'bar',
            data: []
        }]
    };

    myChart.setOption(option);
    $.ajax({
        url: "${pageContext.request.contextPath}/user/getRegTime",
        dataType: "JSON",
        type: "post",
        success: function (result) {
            var arr1 = [];
            var arr2 = [];
            for (var i in result) {
                arr1.push(i);
                arr2.push(result[i]);
            }
            myChart.setOption({
                xAxis: {
                    data: arr1
                },
                series: [{
                    // 根据名字对应到相应的系列
                    name: '活跃用户',
                    data: arr2
                }]
            });
        }
    })

</script>
