<template>
  <router-link to='/'>
    <el-button type="success" :icon="ArrowLeft" class="return">返回</el-button>
  </router-link>
  <h2 class="head">
    历史日程
  </h2>
  
  <div class="demo-collapse">
    <el-collapse>
      <el-collapse-item name="1">
        <template #title>
          <div class="card-header">
            <span class="title">日程1</span>
            <el-icon class="icon" color="#41A8A8" :size="20"><Finished /></el-icon>
          </div>
        </template>
        <div class="text">{{  '开始时间：2023年11月13日8:00'  }}</div>
        <div class="text">{{  '结束时间：2023年11月13日10:00' }}</div>
        <div class="text">{{  '打卡时间：2023年11月13日7:55'  }}</div>
        <div class="text">{{  '地点：3号教学楼301' }}</div>
      </el-collapse-item>

      <el-collapse-item name="2">
        <template #title>
          <div class="card-header">
            <span class="title">日程2</span>
            <el-icon class="icon" color="#F0A900" :size="20"><Check /></el-icon>
          </div>
        </template>
        <div class="text">{{  '开始时间：2023年11月12日18:00'  }}</div>
        <div class="text">{{  '结束时间：2023年11月12日20:00' }}</div>
        <div class="text">{{  '打卡时间：2023年11月12日18:05'  }}</div>
        <div class="text">{{  '地点：中心教学楼101' }}</div>
      </el-collapse-item>

      <el-collapse-item name="3">
        <template #title>
          <div class="card-header">
            <span class="title">日程3</span>
            <el-icon class="icon" color="#D43306" :size="20"><Close /></el-icon>
          </div>
        </template>
        <div class="text">{{  '开始时间：2023年11月12日14:00'  }}</div>
        <div class="text">{{  '结束时间：2023年11月12日16:00' }}</div>
        <div class="text">{{  '打卡时间：未打卡'  }}</div>
        <div class="text">{{  '地点：8号教学楼1003' }}</div>
      </el-collapse-item>
      
    </el-collapse>
  </div>
</template>

<style>
.return {
  margin-left: 400px;
}
.demo-collapse {
  margin-left: 400px;
  margin-right: 400px;
  margin-top: 60px;
}
.icon {
  margin-left: 20px;
}
.head {
  text-align-last: center;
}
.title {
  margin-left: 20px;
  font-size: 18px;
}
.text {
  margin-left: 20px;
  font-size: 14px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>

<script lang="ts" setup>
import {
  ArrowLeft
} from '@element-plus/icons-vue'
</script>
  