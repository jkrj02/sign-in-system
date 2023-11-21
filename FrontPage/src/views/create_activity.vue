<template>
    <h2 class="head">
        创建临时活动
    </h2>
    <h3>ID= {{ user_id }}</h3>

<!--    起止时间选择器-->
    <el-row class="el-row">
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple"/>
        </el-col>
        <el-col class="el-col" :span="2">
            <el-text class="line_head"> 起止时间：</el-text>
        </el-col>
        <el-col class="el-col" :span="14">
            <div class="block">
                <el-date-picker
                        v-model="activity_time_range"
                        type="datetimerange"
                        range-separator="To"
                        start-placeholder="Start date"
                        end-placeholder="End date"
                />
            </div>
        </el-col>
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple-light"/>
        </el-col>
    </el-row>


<!--教室选择-->
    <el-row class="el-row">
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple"/>
        </el-col>
        <el-col class="el-col" :span="2">
            <el-text class="line_head"> 活动教室：</el-text>
        </el-col>
        <el-col class="el-col" :span="14">
            <el-cascader
                    v-model="selected_room"
                    :options="room_list"
                    :props="room_selector_props"

            />
        </el-col>
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple-light"/>
        </el-col>
    </el-row>
<!--活动名称-->
    <el-row class="el-row">
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple"/>
        </el-col>
        <el-col class="el-col" :span="2">
            <el-text class="line_head"> 活动名称：</el-text>
        </el-col>
        <el-col class="el-col" :span="14">
            <el-input
                    v-model="activity_name"
            />
        </el-col>
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple-light"/>
        </el-col>
    </el-row>
    <el-row class="el-row">
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple"/>
        </el-col>
        <el-col class="el-col" :span="4">
            <el-button type="danger" @click="cancelSubmit">取消</el-button>
        </el-col>
        <el-col class="el-col" :span="8">
            <div class="grid-content ep-bg-purple"/>
<!--            <el-button type="danger" @click="testLogin">test{{ infos }}</el-button>-->
        </el-col>
        <el-col class="el-col" :span="4">
            <el-button type="primary" @click="submitActivity">提交</el-button>
        </el-col>
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple-light"/>
        </el-col>
    </el-row>

    <!--    <el-text>{{ TestLogs }}</el-text>   &lt;!&ndash;调试用输出显示&ndash;&gt;-->

</template>

<style>

.head {
    margin-top: 40px;
    text-align-last: center;
}

.line_head {
    font-size: 24px;
}

.el-row {
    margin-bottom: 20px;
}

.el-row:last-child {
    margin-bottom: 0;
}

.el-col {
    border-radius: 4px;
}

.grid-content {
    border-radius: 4px;
    min-height: 36px;
}

</style>

<script lang="ts">

export default {

    setup() {
        // var room_list = [];         //TODO 获取教室列表 tips：需要获取教室可用状态么？
        const room_list = [
            {
                value: 'Zhongguancun',
                label: '中关村校区',
                children: [
                    {
                        value: 'Building8',
                        label: '8号教学楼',
                        children: [
                            {
                                value: 'Floor4',
                                label: '4楼',
                                children: [
                                    {
                                        value: 'room401',
                                        label: '401',
                                    },
                                    {
                                        value: 'room402',
                                        label: '402',
                                    },
                                ]
                            },
                            {
                                value: 'Floor5',
                                label: '5楼',
                                children: [
                                    {
                                        value: 'room501',
                                        label: '501',
                                    },
                                    {
                                        value: 'room502',
                                        label: '502',
                                    },
                                ]
                            },
                        ]
                    },
                    {
                        value: 'Building9',
                        label: '9号教学楼',
                        children: [
                            {
                                value: 'Floor4',
                                label: '4楼',
                                children: [
                                    {
                                        value: 'room401',
                                        label: '401',
                                    },
                                    {
                                        value: 'room402',
                                        label: '402',
                                    },
                                ]
                            },
                            {
                                value: 'Floor5',
                                label: '5楼',
                                children: [
                                    {
                                        value: 'room501',
                                        label: '501',
                                    },
                                    {
                                        value: 'room502',
                                        label: '502',
                                    },
                                ]
                            },
                        ]
                    },
                ]

            },
            {
                value: 'Liangxiang',
                label: '良乡校区',
                children: [
                    {
                        value: 'BuildingScience',
                        label: '理科教学楼',
                        children: [
                            {
                                value: 'Floor4',
                                label: '4楼',
                                children: [
                                    {
                                        value: 'room401',
                                        label: '401',
                                    },
                                    {
                                        value: 'room402',
                                        label: '402',
                                    },
                                ]
                            },
                            {
                                value: 'Floor',
                                label: '5楼',
                                children: [
                                    {
                                        value: 'room501',
                                        label: '501',
                                    },
                                    {
                                        value: 'room502',
                                        label: '502',
                                    },
                                ]
                            },
                        ]
                    },
                    {
                        value: 'BuildingComplex',
                        label: '综合教学楼',
                        children: [
                            {
                                value: 'Floor4',
                                label: '4楼',
                                children: [
                                    {
                                        value: 'room401',
                                        label: '401',
                                    },
                                    {
                                        value: 'room402',
                                        label: '402',
                                    },
                                ]
                            },
                            {
                                value: 'Floor5',
                                label: '5楼',
                                children: [
                                    {
                                        value: 'room501',
                                        label: '501',
                                    },
                                    {
                                        value: 'room502',
                                        label: '502',
                                    },
                                ]
                            },
                        ]
                    },
                ]

            }

        ]
        let infos;


        return {
            room_selector_props: {
                multiple: true,
            },
            room_list,
            infos,
        }
    },
    created() {
        this.user_id = this.$route.query.user_id;
    },
    data() {
        return {
            activity_time_range: [],
            selected_room: [],
            activity_name: "",
            TestLogs: "123",
            user_id: '',
        }
    },
    methods:
        {
            submitActivity() {
                this.TestLogs = "用户ID：" + this.user_id + "活动名称：" + this.activity_name + " 选择教室：" + this.selected_room + " 活动时间：" + this.activity_time_range
            },
            cancelSubmit() {
                this.$router.go(-1)// 返回上一步
            },
            testLogin() {

                // this.TestLogs = "12321321"
                this.$router.push({path: '/login_page', query: {my_id: '12343234'}})
            }
        },


}

</script>
