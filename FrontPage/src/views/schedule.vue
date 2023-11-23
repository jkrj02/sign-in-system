<template>
    <!-- <h3 class="head">我的ID： {{ user_id }}</h3>
    <router-link :to="{path:'/login_page'}">
        <el-button type="success" class="button1">登录</el-button>
    </router-link> -->
    <h3 class="head">{{ formattedTime1 }}</h3>

  <h3 class="head">
        我的活动 
    </h3>

  <div  v-if="start">
    <div v-for="(item, index) in response" :key="index" >
      <el-card v-if="item.endTime >= nowTime && item.approved === '已通过'" class="box-card">
        <template #header>
        <div class="card-header">
          <span class="title">{{item.name}}</span>
        </div>
      </template>
      <div class="text item">{{ '开始时间： '+item.time }}</div>
      <div class="text item">{{ '结束时间： '+item.endTime }}</div>
      <div class="text item" v-if="item.classroomId === 0">{{  '地点：3号教学楼301' }}</div>
      <div class="text item" v-if="item.classroomId === 2">{{  '地点：8号教学楼1003' }}</div>
    
      </el-card>
    
    </div>
  </div>

    <router-link :to="{path:'/history',query:{user_id:this.user_id}}">
        <div class="button2">
            <el-button type="success">历史日程</el-button>
        </div>
    </router-link>

</template>

<style> 
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.head {
  margin-top: 15px;
  text-align-last: center;
}

.title {
  font-size: 15px;
}

.text {
  font-size: 15px;
}

.item {
  margin-bottom: 14px;
}

.box-card {
  margin: 20px auto;
  width: 430px;
}

.button2 {
  margin: 15px;
  text-align: center;
}

</style>

<script>
import axios from 'axios'
import global from './global.vue'
export default {
    setup() {

    },
    created() {
        this.user_id = this.$route.query.user_id;
        console.error("User_ID: ", this.user_id);
        this.checkAttendance();
    },
    data () {
        return {
          user_id: '',
          currentTime: new Date(),
          response: null,
          nowTime: 0,
          start: 0
        }
    }, 

    methods: {
      async checkAttendance() {
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
          console.log(this.start_time);
          this.nowTime = this.formattedTime();
          this.start += 1;
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
    computed: {
          formattedTime1 () {
              const date = new Date(this.currentTime)
              const year = date.getFullYear()
              const month = date.getMonth() + 1
              const day = date.getDate()
              const hour = date.getHours()
              const minute = date.getMinutes()
              const second = date.getSeconds()
              return `${year}-${month}-${day}`
          }
      }
}




</script>
