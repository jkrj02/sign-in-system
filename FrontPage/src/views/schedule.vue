<template>
    <h2 class="head">
        我的课程 
    </h2>
    <!-- <h3 class="head">我的ID： {{ user_id }}</h3> -->
    <!-- <router-link :to="{path:'/login_page'}">
        <el-button type="success" class="button1">登录</el-button>
    </router-link> -->
    <h3 class="head">{{ formattedTime }}</h3>

    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span class="title">课程1</span>
            </div>
        </template>
        <div class="text item">{{ '时间：8:00 ' }}</div>
        <div class="text item">{{ '教室：中教101 ' }}</div>
    </el-card>

    <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span class="title">课程2</span>
      </div>
    </template>
    <div class="text item">{{ '时间：2:00 ' }}</div>
    <div class="text item">{{ '教室：中教301 ' }}</div>
  </el-card>

  <h2 class="head">
        我的活动 
    </h2>
    <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span class="title">活动1</span>
      </div>
    </template>
    <div class="text item">{{ '时间：18:00 ' }}</div>
    <div class="text item">{{ '教室：3号楼301 ' }}</div>
  </el-card>

    <router-link :to="{path:'/home',query:{user_id:this.user_id}}">
        <div class="button2">
            <el-button type="success">返回主页</el-button>
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
  margin-top: 20px;
  text-align-last: center;
}

.title {
  font-size: 18px;
}

.text {
  font-size: 18px;
}

.item {
  margin-bottom: 14px;
}

.box-card {
  margin: 20px auto;
  width: 480px;
}

.button2 {
  margin: 20px;
  text-align: center;
}

</style>

<script>
export default {
    setup() {

    },
    created() {
        this.user_id = this.$route.query.user_id;
    },
    data() {
        return {
            user_id: '',
        }
    },
    data () {
        return {
        currentTime: new Date()
        }
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
