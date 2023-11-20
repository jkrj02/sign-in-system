<template>
  <h2 class="head">
      活动出勤率: 100%
  </h2>
  <h3 class="head">
    ID = {{ user_id }}
  </h3>
  <div class="button-container">
    <el-button @click="returnRate" type="success">返回</el-button>
  </div>
</template>

<style>
.head {
  text-align-last: center;
}

.button {
  margin: 20px auto;
  width: 160px;
  margin-left: 440px;
  background-color: lightgreen;
}

.button-container {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-bottom: 20px;
}

</style>

<script>
export default {
methods: {
returnRate(){
  this.$router.go(-1)// 返回上一步
},
}
}
</script>