<template>
  <div style="height: 100%;">
    <el-container>
      <el-header class="title">签到系统</el-header>
      <el-container>
        <el-aside width="20%">
          <el-menu
            default-active="login_page"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @select="changeSide"
          >
            <el-menu-item index="login_page">
              <span>登录</span>
            </el-menu-item>
            <el-menu-item index="schedule">
              <span>查看日程</span>
            </el-menu-item>
            <el-menu-item index="create_activity">
              <span>活动创建</span>
            </el-menu-item>
            <el-sub-menu index="rate">
              <template #title>
                <span>签到率分析</span>
              </template>
              <el-menu-item index="course_rate">
                <span>课程签到率</span>
              </el-menu-item>
              <el-menu-item index="activity_rate">
                <span>活动签到率</span>
              </el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
      

    <!-- <h2 class="head">
        我的日程 11月14号
    </h2> -->

    <!-- <h3>ID= {{ user_id }}</h3>
    <router-link :to="{path:'/login_page'}">
        <el-button type="success" class="button1">登录</el-button>
    </router-link> -->

    <!-- <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span class="title">日程1</span>
            </div>
        </template>
        <div class="text item">{{ '时间：8:00 ' }}</div>
        <div class="text item">{{ '教室：中教101 ' }}</div>
    </el-card>

    <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span class="title">日程2</span>
      </div>
    </template>
    <div class="text item">{{ '时间：2:00 ' }}</div>
    <div class="text item">{{ '教室：中教301 ' }}</div>
  </el-card>

    <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span class="title">日程3</span>
      </div>
    </template>
    <div class="text item">{{ '时间：18:00 ' }}</div>
    <div class="text item">{{ '教室：3号楼301 ' }}</div>
  </el-card> -->

    <!-- <router-link :to="{path:'/create_activity',query:{user_id:this.user_id}}">
        <el-button type="success" class="button1">活动申请</el-button>
    </router-link>

    <router-link :to="{path:'/rate',query:{user_id:this.user_id}}">
        <el-button type="success" class="button1">出勤率查询</el-button>
    </router-link>

    <router-link :to="{path:'/history',query:{user_id:this.user_id}}">
        <el-button type="success" class="button1">历史日程</el-button>
    </router-link>
    <router-link :to="{path:'/schedule',query:{user_id:this.user_id}}">
        <el-button type="success" class="button1">日程查询</el-button>
    </router-link> -->

</template>

<style> 
/* .card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
} */

/* .head {
  margin-top: 40px;
  text-align-last: center;
} */

/* .title {
  font-size: 18px;
} */

/* .text {
  font-size: 18px;
} */

/* .item {
  margin-bottom: 14px;
} */

/* .box-card {
  margin: 20px auto;
  width: 480px;
} */
.mb-2 {
  font-size: large;
}

.button1 {
  margin: 20px;
  margin-left: 280px;
}

.title {
  font-size: large;
  text-align: left;
}

</style>

<script>
export default {
    created() {
        this.user_id = this.$route.query.user_id;
    },
    data() {
        return {
            user_id: '',
        }
    },
    methods: {
      changeSide(path) {
        this.$router.push(path);
      },
      handleOpen(path) {
        this.$router.push(path);
      },
    }
    
}
</script>

