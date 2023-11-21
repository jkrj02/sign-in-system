<template>
    <div class="welcome" v-if="start">
        <h2>
            Welcome!
        </h2>
        <div class="text">
            {{ '学号：' + response.studentId }}
        </div>
        <div class="text">
            {{ '姓名：' + response.name }}
        </div>
        <div class="text">
            {{ '年级：' + response.grade }}
        </div>
        <div class="text">
            {{ '学院：' + response.college }}
        </div>
        <div class="text">
            {{ '专业：' + response.major }}
        </div>
    </div>
</template>

<style>
.welcome {
    position: relative;
    top: 5%;
    text-align: center;
    font-size: larger;
}

.text {
    font-size: large;
    text-align: center;
    margin-top: 0.5%;
}
</style>

<script>
    import axios from 'axios'
    import global from './global.vue'

    export default {
    setup() {
    },
    created() {
        this.user_id = this.$route.query.user_id;
        console.log("User_ID: ", this.user_id);
        this.checkLogin();
    },
    data() {
        return {
            user_id: this.user_id,
            start: 0,
        }
    },
    methods: {
      async checkLogin() {
        try {
          var config = {
            method: 'get',
            headers: {
                'Access-Control-Allow-Origin': 'http://localhost:5173'},
            url: global.httpUrl + 'login/' + this.user_id,
          };
          const response = await axios(config);
          this.response = response.data;
          console.log(JSON.stringify(response.data));
          this.start += 1;
        }
        catch (error) {
          console.error('There was an error!', error);
          this.response = 'Error: ' + error.message;
        }
      },
    },
}
</script>