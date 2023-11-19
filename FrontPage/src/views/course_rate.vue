<template>
  <h2 class="head">
      课程出勤率查询
  </h2>
  
  <el-date-picker v-model="start_time" type="datetime" placeholder="Start date" @change="dateChange" style="width: 500px; margin: 20px 500px;"></el-date-picker>
  <el-date-picker v-model="end_time" type="datetime" placeholder="End date" @change="dateChange" style="width: 500px; margin: 20px 500px;"></el-date-picker>

  <div v-if="response">
    <h3>课程出勤率:</h3>
    <pre>{{ response }}</pre>
  </div>
  <router-link :to="{path:'/rate',query:{user_id:this.user_id}}">
      <el-button type="success" class="button">返回</el-button>
  </router-link>
  <el-button @click="checkAttendance" type="success" class="button" style="width: 100px; margin: 20px 700px;">查询</el-button>
</template>

<script>
import axios from 'axios'

export default {
data() {
  return {
    start_time: '',
    end_time: '',
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
        url: 'http://10.62.57.169:8080/checkatt?teacherId=100&start_time='+this.start_time+'&end_time='+this.end_time,
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