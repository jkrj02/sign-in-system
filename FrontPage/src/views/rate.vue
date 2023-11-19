<template>
    <h2 class="head">
        出勤率查询
    </h2>
    <h3>ID= {{ user_id }}</h3>
    <router-link :to="{path:'/home',query:{user_id:this.user_id}}">
        <el-button type="success" class="button">返回主页</el-button>
    </router-link>

    <router-link :to="{path:'/course_rate',query:{user_id:this.user_id}}">
        <el-button type="success" class="button">课程出勤率</el-button>
    </router-link>

    <router-link :to="{path:'/activity_rate',query:{user_id:this.user_id}}">
        <el-button type="success" class="button">活动出勤率</el-button>
    </router-link>

</template>

<style>

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
    }
}

</script>