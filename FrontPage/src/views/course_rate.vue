<template>
  <h3 class="head">
      课程出勤率查询
  </h3>
  <div class="time-container">
    <el-date-picker v-model="start_time" value-format="YYYY/MM/DD/HH:mm" type="datetime" placeholder="Start date" @change="getBirthDate"></el-date-picker>
  </div>
  <div class="time-container">
    <el-date-picker v-model="end_time" value-format="YYYY/MM/DD/HH:mm" type="datetime" placeholder="End date" @change="getBirthDate"></el-date-picker>
  </div>
  
  <div v-if="response" class="table-container">
    <table>
      <thead>
        <tr>
          <th>课程</th>
          <th>出勤率(%)</th>
          <th>详情</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in response" :key="index">
          <td>{{ item.coursename }}</td>
          <td>{{ item.attendence *100 }}</td>
          <td><el-button>查看</el-button></td>
        </tr>
      </tbody>
    </table>
  </div>

  <div class="button-container">
    <el-button @click="checkAttendance" type="success">查询</el-button>
  </div>
</template>

<style>
.head {
  text-align-last: center;
  margin-bottom: 3%;
}

.button-container {
  margin-top: 3%;
  display: flex;
  justify-content: center;
  width: 100%;
  margin-bottom: 20px;
}

.time-container {
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
.table-container {
    width: 30%;
    margin: 0 auto; margin-bottom: 20px;
  }
table {
  width: 100%;
    border-collapse: collapse;
  }
  th, td {
    padding: 8px;
    text-align: center;
    border-bottom: 1px solid #ddd;
  }
  thead {
    background-color: #f2f2f2;
  }

</style>

<script>
import axios from 'axios'
import global from './global.vue'

export default {
data() {
  return {
    start_time: '',
    end_time: '',
    tableData: [],
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
        url: global.httpUrl + 'checkatt?teacherId=100&start_time=' + this.start_time + '&end_time=' + this.end_time,
      };

      const response = await axios(config);
      this.response = response.data;
      console.log(JSON.stringify(response.data));
    }
    catch (error) {
      console.error('There was an error!', error);
      this.response = 'Error: ' + error.message;
    }
  },
}
};
</script>

<script setup>
</script>
