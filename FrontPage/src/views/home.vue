<template>
  <h2 class="head">
    Hello World!
  </h2>
  <h3 class="head">
    ID = {{ user_id }}
  </h3>
  <div class="button-container">
    <router-link :to="{path:'/login_page', query: {user_id: user_id}}">
      <el-button type="success">登录</el-button>
    </router-link>
  </div>
  <div class="button-container">
    <router-link :to="{path:'/schedule', query: {user_id: user_id}}">
      <el-button type="success">我的日程</el-button>
    </router-link>
  </div>
  <div class="button-container">
    <router-link :to="{path:'/rate', query: {user_id: user_id}}">
      <el-button type="success">出勤率</el-button>
    </router-link>
  </div>
  <div class="button-container">
    <el-button @click="closeWindow" type="success">退出</el-button>
    <!-- 加个关闭页面的功能 -->
  </div>
</template>


<style> 
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

.button{
  margin: 20px 0; /* 垂直边距保留，水平边距移除 */
  flex-grow: 1; /* 使按钮在水平方向上伸缩 */
  max-width: 100px; /* 可以根据需要设置最大宽度 */
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
}

</script>