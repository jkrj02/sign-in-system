<template>
  <h2 class="head">
      课程出勤率查询
  </h2>
  <div class="button-container">
    <el-date-picker v-model="start_time" value-format="YYYY/MM/DD/hh:mm" type="datetime" placeholder="Start date" @change="getBirthDate"></el-date-picker>
  </div>
  <div class="button-container">
    <el-date-picker v-model="end_time" value-format="YYYY/MM/DD/hh:mm" type="datetime" placeholder="End date" @change="getBirthDate"></el-date-picker>
  </div>
  

  <div v-if="response">
    <h3>课程出勤率:</h3>
    <pre>{{ response }}</pre>
  </div>
  <div class="button-container">
    <el-button @click="checkAttendance" type="success">查询</el-button>
  </div>
  <div class="button-container">
    <el-button @click="returnRate" type="success">返回</el-button>
  </div>
</template>

<style>
/* style="width: 500px; margin: 20px 500px;" */
.head {
  text-align-last: center;
}

.button-container {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-bottom: 20px;
}

.data_container {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-bottom: 20px;
}

</style>

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
        url: 'http://10.63.24.243:8080/checkatt?teacherId=100&start_time='+this.start_time+'&end_time='+this.end_time,
      };

      const response = await axios(config);
      this.response = response.data;
      console.log(JSON.stringify(response.data));
      console.log(this.start_time)
    }
    catch (error) {
      console.error('There was an error!', error);
      this.response = 'Error: ' + error.message;
    }
  },
  returnRate() {
    this.$router.go(-1)// 返回上一步
  },
}
};
</script>

<script setup>
</script>
