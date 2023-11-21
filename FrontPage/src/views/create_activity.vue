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
                    value-format="YYYY/MM/DD/hh:mm:ss"
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
            <el-button type="danger" @click="test">test{{ infos }}</el-button>
        </el-col>
        <el-col class="el-col" :span="4">
            <el-button type="primary" @click="submitActivity">提交</el-button>
        </el-col>
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple-light"/>
        </el-col>
    </el-row>

    <el-text>{{ TestLogs }}</el-text>   <!--调试用输出显示-->

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

import axios from "axios";

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
        // this.TestLogs = JSON.stringify(this.activity_info)
    },
    data() {

        return {
            activity_time_range: [Date, Date],
            selected_room: [],
            activity_name: "",
            TestLogs: "123",
            user_id: '',
            activity_info: {
                "activityId": 0,
                "organizerId": 1120209999,
                "type": "自主睡觉",
                "name": "玄学",
                "time": "2023/11/15/9:00",
                "endTime": "2023/11/17/12:00",
                "classroomId": 9,
                "approved": "已审批"
            }

        }
    },
    methods:
        {
            async submitActivity() {
                this.TestLogs = "用户ID：" + this.user_id + "活动名称：" + this.activity_name + " 选择教室：" + this.selected_room + " 活动时间：" + this.activity_time_range
                console.log(this.TestLogs)

                this.setInfo()

                // var axios = require('axios');
                // try {
                //     var config = {
                //         method: 'post',
                //         headers: {
                //             'Access-Control-Allow-Origin': 'http://localhost:5173'
                //         },
                //         url: 'http://10.63.110.16:8080/activity/add',
                //         data:JSON.stringify(this.activity_info)
                //     };
                //     const response = await axios(config);
                //     this.response = response.data;
                //     console.log(JSON.stringify(response.data));
                //     this.start += 1;
                //     this.nowTime = this.formattedTime();
                // } catch (error) {
                //     console.error('There was an error!', error);
                //     this.response = 'Error: ' + error.message;
                // }

                var data = JSON.stringify(this.activity_info)
                this.TestLogs = 'sending' + JSON.stringify(this.activity_info)
                let responseLog;
                let responseJSON;

                let responseData;
                var config = {
                    method: 'post',
                    url: 'http://10.63.110.16:8080/activity/add',
                    headers: {
                        'Access-Control-Allow-Origin': 'http://localhost:5173',
                        // 'User-Agent': 'Apifox/1.0.0 (https://apifox.com)',
                        'Content-Type': 'application/json',
                        'Accept': '*/*',
                        'Host': '10.63.110.16:8080',
                        'Connection': 'keep-alive'
                    },
                    data: data
                };

                axios(config)
                    .then(function (response) {
                        responseData = response.data
                        responseJSON = JSON.stringify(response.data)
                        console.log(JSON.stringify(response.data));
                        responseLog = JSON.parse(responseJSON);
                        // console.log('success:'+responseLog);
                        // this.TestLogs = 'success' + JSON.stringify(response.data);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                this.TestLogs = 'success!' + responseLog;
//TODO 服务器返回的JSON能在控制台log里看到，却不能显示出来？
            },

            test() {
                this.setInfo();
                this.TestLogs = this.activity_info;
            },

            setInfo() {
                this.activity_info.organizerId = this.user_id;
                this.activity_info.name = this.activity_name;
                this.activity_info.classroomId = 0;
                this.activity_info.time = this.activity_time_range[0];
                this.activity_info.endTime = this.activity_time_range[1];
            },


            cancelSubmit() {
                this.$router.go(-1)// 返回上一步
            },
            // testLogin() {
            //
            //     // this.TestLogs = "12321321"
            //     this.$router.push({path: '/login_page', query: {my_id: '12343234'}})
            // }
        },


}

</script>
