<script>
import {useRouter, useRoute} from "vue-router";
import { ElMessageBox } from 'element-plus'

const router = useRouter();
const route = useRoute();

// var user_id;
export default {

    setup() {
        // user_id = route.query.

        return {
            // user_id : '21321321321',
        }
    },

    // created() {
    //     this.user_id = this.$route.query.my_id;
    // },

    data() {
        return {
            user_id: '',
            input_id: '1120209999',
        }
    },
    methods: {
        setid() {
            this.user_id = this.input_id
        },
        jump() {
            if(this.user_id === '') {
                ElMessageBox.alert('请点击set id进行登录', 'Error', {
                // if you want to disable its autofocus
                // autofocus: false,
                confirmButtonText: 'OK'
                })
            }
            else {
                this.$router.push({path: '/welcome', query: {user_id: this.user_id}})
            }
            // this.$router.push({path:'/create_activity', query:{user_id:this.user_id}})
        }
    }
}


</script>

<template>
    <el-row class="el-row">
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple"/>
        </el-col>
        <el-col class="el-col" :span="2">
            <el-text class="line_head"> 登录：</el-text>
        </el-col>
        <el-col class="el-col" :span="14">
            <el-input
                    v-model="input_id"
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
            <el-button @click="setid">set id</el-button>
        </el-col>
        <el-col class="el-col" :span="8">
            <el-text> 当前用户： {{ user_id }}</el-text>
        </el-col>
        <el-col class="el-col" :span="4">
            <el-button @click="jump">确定</el-button>
        </el-col>
        <el-col class="el-col" :span="4">
            <div class="grid-content ep-bg-purple"/>
        </el-col>
    </el-row>


</template>

<style scoped>

</style>