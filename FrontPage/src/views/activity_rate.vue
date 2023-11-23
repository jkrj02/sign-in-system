<template>
  <!-- <h2 class='head'>
    准时率：{{this.attendencerate}}%
    迟到率：{{this.laterate}}%
    缺席率：{{this.absencerate}}%
  </h2> -->
  <div class="chart-container">
      <div ref="chart" style="width: 400px; height: 400px;"></div>
  </div>

</template>

<style>
.head {
  text-align-last: center;
}

.chart-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 60vh; /* 使用视口高度，调整位置 */
}

</style>

<script>
import axios from 'axios';
import global from './global.vue'
import * as echarts from 'echarts'

export default {
  name: 'PieChart',
  data() {
    return {
      response: null,
      attendencerate: 0,
      laterate: 0,
      absencerate: 0
    };
  },
  methods: {
    async checkAttendance() {
      try {
        var config = {
          method: 'get',
          headers: {'Access-Control-Allow-Origin': 'http://localhost:5173'},
          url: global.httpUrl + 'myattendence/1120209999',
        };

        const response = await axios(config);
        this.response = response.data;
        this.attendencerate = (this.response.attendencerate *100).toFixed(2);
        this.laterate = (this.response.laterate * 100).toFixed(2);
        this.absencerate = (this.response.absencerate *100).toFixed(2);
        console.log(JSON.stringify(response.data));
        // 指定图表的配置项和数据
        const myChart = echarts.init(this.$refs.chart);
        const data1 = this.attendencerate;
        const data2 = this.laterate;
        const data3 = this.absencerate;
        const option = {
          title: {
            text: '活动签到率',
            left: 'center',
            textStyle: {
              fontSize: 30
            }
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left'
          },
          series: [
            {
              name: '签到类型',
              type: 'pie',
              radius: '50%',
              data: [
                {value: data1, name: '准时率'},
                {value: data2, name: '迟到率'},
                {value: data3, name: '缺席率'}
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      }
      catch (error) {
        console.error('There was an error!', error);
        this.response = 'Error: ' + error.message;
      }
    },
    // returnRate() {
    //   this.$router.go(-1); // 返回上一步
    // },
  },
  mounted() {
    this.checkAttendance(); // 组件挂载后立即调用
  }
}
</script>