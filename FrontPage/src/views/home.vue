<template>
  <div style="height: 100%;">
    <el-container class="contain">
      <el-header class="title">
        <h2>签到系统</h2>
      </el-header>
      <el-container>
        <el-aside width="20%" class="el-aside">
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
            <el-sub-menu>
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
        <el-main class="el-main">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style> 
.title {
  position: relative;
  width: 100%;
  height: auto;
  left: 0px;
}

.el-aside {
  display: block;
  position: absolute;
  left: 0;
  top: 80px;
  bottom: 0;
}

.el-main {
  position: absolute;
  width: 80%;
  left: 20%;
  top: 60px;
  bottom: 0;
  overflow-y: scroll;
}

.head {
  margin-top: 40px;
  text-align-last: center;
}

.button-row {
  display: flex;
  justify-content: center; /* 使按钮在容器中居中 */
  gap: 1px; /* 按钮之间的间隔 */
}

.button-container {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-bottom: 20px;
}

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
.button{
  margin: 20px 0; /* 垂直边距保留，水平边距移除 */
  flex-grow: 1; /* 使按钮在水平方向上伸缩 */
  max-width: 100px; /* 可以根据需要设置最大宽度 */
}

.title {
  font-size: larger;
  font-weight: bolder;
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
          this.user_id = this.$route.query.user_id
          this.$router.push({path: path, query: {user_id: this.user_id}});
      },
      handleOpen(path) {
          this.user_id = this.$route.query.user_id
          this.$router.push({path: path, query: {user_id: this.user_id}});
      },
    }
    
}
</script>

