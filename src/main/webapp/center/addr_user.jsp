<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="statistics_china" style="width: 100%;height: 100%;margin-top: 30px;margin-left: 30px">

</div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('statistics_china'));

    function randomData() {
        return Math.round(Math.random() * 1000);
    }

    option = {
        title: {
            text: '持名法州APP用户分布图',
            subtext: '2017年6月15日 最新假数据',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },

        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        // 工具箱
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '用户',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: []
            }
        ]
    };
    myChart.setOption(option);

    $(function () {
        $.post("${pageContext.request.contextPath}/user/getAddressCount", function (data) {
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '用户',
                    data: data
                }]
            });
        }, "json");
    });
</script>
















