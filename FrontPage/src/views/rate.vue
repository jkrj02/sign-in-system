<template>
    <h2 class="head">
        出勤率查询
    </h2>

    <router-link to='/course_rate'>
        <el-button type="success" class="button">课程出勤率</el-button>
    </router-link>

    <router-link to='/activity_rate'>
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

<script setup>
    
</script>