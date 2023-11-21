<template>
    <div class="container">
        <h2 class="head">
            出勤率查询
        </h2>

        <div class="button-container">
            <router-link :to="{path:'/course_rate', query: {user_id: user_id}}">
                <el-button type="success">课程出勤率</el-button>
            </router-link>
        </div>
        <div class="button-container">
            <router-link :to="{path:'/activity_rate', query: {user_id: user_id}}">
                <el-button type="success">活动出勤率</el-button>
            </router-link>
        </div>
        <div class="button-container">
            <el-button @click="returnRate" type="success">返回主页</el-button>
        </div>

    </div>
</template>


<style>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.head {
  text-align: center;
}

.button-container {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-bottom: 20px;
}

.button {
  margin: 20px auto;
  width: 160px;
  margin-left: 440px;
  background-color: lightgreen;
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
methods: {
    returnRate() {
        this.$router.go(-1)// 返回上一步
    },
}
}
</script>