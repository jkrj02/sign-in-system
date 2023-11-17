<template>
  <h2 class="head">
      课程出勤率查询
  </h2>
  
  <el-input v-model="input1" style="width: 500px; margin: 20px 160px;" placeholder="请输入查询起始日期（格式：年-月-日，如2023/11/11/9:00）"></el-input>
  <el-input v-model="input2" style="width: 500px; margin: 20px 40px;" placeholder="请输入查询截止日期（格式：年-月-日，如2023/11/11/13:00）"></el-input>
  <div v-if="response">
    <h3>课程出勤率:</h3>
    <pre>{{ response }}</pre>
  </div>
  <el-button @click="checkAttendance" type="success" class="button" style="width: 100px; margin: 20px 700px;">查询</el-button>
</template>
<script>
import axios from 'axios'

export default {
data() {
  return {
    input1: '',
    input2: '',
    response: null
  }
},

methods: {
  async checkAttendance() {
    try {
      var config = {
        method: 'get',
        headers: {
            'Access-Control-Allow-Origin': 'http://localhost:5173'},
        url: 'http://10.62.57.169:8080/checkatt?teacherId=100&start_time='+this.input1+'&end_time='+this.input2,
      };

      const response = await axios(config);
      this.response = response.data;
      console.log(JSON.stringify(response.data));
    }
    catch (error) {
      console.error('There was an error!', error);
      this.response = 'Error: ' + error.message;
    }
  }
}
};
</script>

<script setup>
</script>