<template>
    <h2 class="head">
        课程出勤率
    </h2>
    <h3>ID= {{ user_id }}</h3>

    <el-table :data="tableData" style="width: 500px; margin: 20px 600px;width: 25%">
        <el-table-column prop="course" label="课程" width="180"></el-table-column>
        <el-table-column prop="rate" label="出勤率" width="180"></el-table-column>
    </el-table>
</template>

<script>
export default {
    data() {
        return {
            tableData: [{
                course: 'web开发',
                rate: '2%'
            }, {
                course: '数据库原理设计',
                rate: '5%'
            }, {
                course: '卓越工程综合训练',
                rate: '1%'
            }],
            user_id: '',

        }
    },
    created() {
        this.user_id = this.$route.query.user_id;
    },

}


</script>

<script setup>
</script>
