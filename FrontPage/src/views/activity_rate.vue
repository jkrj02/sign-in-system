<template>
  <h2 class="head">
    活动出勤率: {{ this.response }} %
  </h2>

  <div class="button-container">
    <el-button @click="returnRate" type="success">返回</el-button>
  </div>
</template>

<style>
.head {
  text-align-last: center;
}

.button {
  margin: 20px auto;
  width: 160px;
  margin-left: 440px;
  background-color: lightgreen;
}

.button-container {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-bottom: 20px;
}

</style>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      response: null
    };
  },
  methods: {
    async checkAttendance() {
      try {
        var config = {
          method: 'get',
          headers: {'Access-Control-Allow-Origin': 'http://localhost:5173'},
          url: 'http://10.63.110.16:8080/myattendence/1120209999',
        };

        const response = await axios(config);
        this.response = (response.data* 100).toFixed(2);
        console.log(JSON.stringify(response.data));
      }
      catch (error) {
        console.error('There was an error!', error);
        this.response = 'Error: ' + error.message;
      }
    },
    returnRate() {
      this.$router.go(-1); // 返回上一步
    },
  },
  mounted() {
    this.checkAttendance(); // 组件挂载后立即调用
  }
}
</script>