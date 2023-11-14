import axios from 'axios'
import store from './../store'
const ConfigBaseURL = 'http://localhost:9999';


// 让ajax携带cookie(自动携带本地所有cookie)s
axios.defaults.withCredentials=true;
// 使用create方法创建axios实例
const Service = axios.create({
    baseURL: ConfigBaseURL, //1. 设置默认地址
    timeout: 7000 // 2. 请求超时时间
})


//3. 给POST请求添加请求头设置（不同项目，值不一样）
Service.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

//4.1 添加请求拦截器
Service.interceptors.request.use(config => {
    return config
})

//4.2 添加响应拦截器
Service.interceptors.response.use(response => {
    console.log("axios 的响应拦截")
    return response
}, error => {

    return Promise.reject(error)
})

export default Service; 