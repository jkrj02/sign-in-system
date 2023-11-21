<template>
    <h3 class="head">
        我的课程 
    </h3>
    <!-- <h3 class="head">我的ID： {{ user_id }}</h3>
    <router-link :to="{path:'/login_page'}">
        <el-button type="success" class="button1">登录</el-button>
    </router-link> -->
    <h3 class="head">{{ formattedTime }}</h3>

    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span class="title">课程1</span>
            </div>
        </template>
        <div class="text item">{{ '开始时间：8:00 ' }}</div>
        <div class="text item">{{ '结束时间：10:00 ' }}</div>
        <div class="text item">{{ '教室：中教101 ' }}</div>
    </el-card>

    <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span class="title">课程2</span>
      </div>
    </template>
    <div class="text item">{{ '开始时间：14:00 ' }}</div>
    <div class="text item">{{ '结束时间：16:00 ' }}</div>
    <div class="text item">{{ '教室：中教301 ' }}</div>
  </el-card>

  <h3 class="head">
        我的活动 
    </h3>
    <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span class="title">活动1</span>
      </div>
    </template>
    <div class="text item">{{ '开始时间：18:00 ' }}</div>
    <div class="text item">{{ '结束时间：20:00 ' }}</div>
    <div class="text item">{{ '教室：3号楼301 ' }}</div>
  </el-card>

  <div v-if="response">
    <el-card v-for="(item, index) in response" :key="index" class="box-card">
    <template #header>
      <div class="card-header">
        <span class="title">{{item.type}}</span>
      </div>
    </template>
    <div class="text item">{{ '开始时间： '+item.time }}</div>
    <div class="text item">{{ '结束时间： '+item.endTime }}</div>
    <div class="text item">{{ '教室ID： '+item.classroomId }}</div>
    <div class="text item">{{ '状态： '+item.approved }}</div>
  </el-card>
  </div>

  <div class="button-container">
    <el-button @click="checkAttendance" type="success">查看更多</el-button>
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
export default {
    setup() {

    },
    created() {
        this.user_id = this.$route.query.user_id;
        console.error("User_ID: ", this.user_id);
    },
    data () {
        return {
          user_id: '',
          currentTime: new Date(),
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
            url: 'http://10.63.24.243:8080/activity/get/1120209999'
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
    },
    computed: {
          formattedTime () {
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
