<template>
    <div class="welcome">
        <h3>
            {{  this.user_id }}
        </h3>
        <h3>
            Welcome
        </h3>
    </div>
</template>

<style>
.welcome {
    position: relative;
    top: 10%;
    text-align: center;
    font-size: xx-large;
}
</style>

<script>
    export default {
    setup() {
    },
    created() {
        this.user_id = this.$route.query.user_id;
        console.error("User_ID: ", this.user_id);
    },
    data() {
        return {
            user_id: this.user_id,
        }
    },
}
</script>