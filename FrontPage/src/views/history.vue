<template>
  <router-link to='/schedule'>
    <el-button type="success" :icon="ArrowLeft" class="return">返回</el-button>
  </router-link>
  <h3 class="head">
    历史日程
  </h3>
    <!-- <h3>ID= {{ user_id }}</h3> -->
  <div class="demo-collapse" v-if="this.start">
    <el-collapse v-for="(item, index) in response" :key="index">
      <el-collapse-item v-if="item.endTime < nowTime">
        <template #title>
          <div class="card-header">
            <span class="title">{{ item.name }}</span>
            <el-icon class="icon" color="#41A8A8" :size="20" v-if="item.signin === '已签到'"><Finished /></el-icon>
            <el-icon class="icon" color="#F0A900" :size="20" v-if="item.signin === '迟到'"><Check /></el-icon>
            <el-icon class="icon" color="#D43306" :size="20" v-if="item.signin === '未签到'"><Close /></el-icon>
          </div>
        </template>
        <div class="text">{{  '开始时间：' + item.time  }}</div>
        <div class="text">{{  '结束时间：' + item.endTime }}</div>
        <div class="text" v-if="item.classroomId === 0">{{  '地点：3号教学楼301' }}</div>
        <div class="text" v-if="item.classroomId === 2">{{  '地点：8号教学楼1003' }}</div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<style>
.return {
  margin-left: 25%;
}
.demo-collapse {
  margin-left: 25%;
  margin-top: 5%;
  text-align: center;
  width: 50%;
  /* margin-left: 400px;
  margin-right: 400px;
  margin-top: 40px; */
}
.icon {
  margin-left: 20px;
}
.head {
  margin-top: 10%;
  text-align-last: center;
}
.title {
  margin-left: 20px;
  font-size: 18px;
}
.text {
    margin-left: 20px;
    font-size: 14px;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>


<script>

import {Finished, ArrowLeft, Check, Close} from "@element-plus/icons-vue";
import axios from 'axios'
import global from './global.vue'

export default {
    computed: {
        ArrowLeft() {
            return ArrowLeft
        }
    },
    components: {Close, Check, Finished},
    setup() {
    },
    beforeCreate() {
    },
    data() {
      return {
        start: 0,
        nowTime: 0,
        user_id: ''
      }
    },
    created() {
      this.user_id = this.$route.query.user_id;
      console.log("User_ID: ", this.user_id);
      this.checkHistory();
    },
    mounted() {
      
    },
    methods: {
      async checkHistory() {
        try {
          var config = {
            method: 'get',
            headers: {
                'Access-Control-Allow-Origin': 'http://localhost:5173'},
            url: global.httpUrl + 'activity/get/1120209999',
          };
          const response = await axios(config);
          this.response = response.data;
          console.log(JSON.stringify(response.data));
          this.start += 1;
          this.nowTime = this.formattedTime();
        }
        catch (error) {
          console.error('There was an error!', error);
          this.response = 'Error: ' + error.message;
        }
      },
      formattedTime () {
        const date = new Date();
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();
        if (hour < 10) {
          hour = "0" + hour;
        }
        if (minute < 10) {
          minute = "0" + minute;
        }
        console.log(`${year}/${month}/${day}/${hour}:${minute}`);
        return `${year}/${month}/${day}/${hour}:${minute}`;
      }
    },
}

</script>
