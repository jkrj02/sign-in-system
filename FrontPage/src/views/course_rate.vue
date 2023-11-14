<template>
    <h2 class="head">
        课程出勤率查询
    </h2>
    
    <el-input v-model="input1" style="width: 500px; margin: 20px 160px;" placeholder="请输入查询起始日期（格式：年-月-日，如2023/11/11/9:00）"></el-input>
    <el-input v-model="input2" style="width: 500px; margin: 20px 40px;" placeholder="请输入查询截止日期（格式：年-月-日，如2023/11/11/13:00）"></el-input>

    <router-link to='/show_course_rate'>
        <button class="button" style="width: 100px; margin: 20px 700px;">查询</button>
    </router-link>
</template>

<script>
export default {
  data() {
    return {
      input1: '',
      input2: '',
    }
  }
}
</script>

<script setup>
</script>