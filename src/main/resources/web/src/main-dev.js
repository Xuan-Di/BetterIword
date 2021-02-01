import Vue from 'vue'
import App from './App.vue'
import './plugins/vantui.js'
import router from './router'
import store from './store'
import axios from 'axios'
import echarts from 'echarts'
axios.defaults.withCredentials = true
axios.defaults.baseURL = 'http://106.14.44.65:8889'
// axios.defaults.baseURL = 'http://wzxlq.xyz:8889'
// axios.defaults.baseURL = 'http://192.168.2.180:8889'
axios.interceptors.request.use(config => {
  config.headers.token = window.sessionStorage.getItem('token')
  return config
})
Vue.prototype.$http = axios
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
