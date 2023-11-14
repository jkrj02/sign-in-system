<template>
    <h2 class="head">
        我的日程  11月14号
    </h2>
    
    <el-card class="box-card">
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
  </el-card>

    <router-link to='/test'>
        <el-button type="success" class="button1">活动申请</el-button>
    </router-link>

    <router-link to='/rate'>
        <el-button type="success" class="button1">出勤率查询</el-button>
    </router-link>

    <router-link to='/history'>
        <el-button type="success" class="button1">历史日程</el-button>
    </router-link>

</template>

<style> 
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.head {
  margin-top: 40px;
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

.button1 {
  margin: 20px;
  margin-left: 280px;
}

</style>

<script setup>
    
</script>
