import Vue from 'vue/dist/vue.js'
import router from './router/index'
import Router from 'vue-router'
import App from  './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(Router)

new Vue({
    el: '#app',
    router,
    template: '<App/>',
    components: {App}
})