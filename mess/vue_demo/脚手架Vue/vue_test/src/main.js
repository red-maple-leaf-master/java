import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'

// 引入ui库
Vue.use(ElementUI);
// 设置弹窗对象
Vue.prototype.$message = ElementUI.Message;

Vue.config.productionTip = false;
// 配置请求的跟路径
axios.defaults.baseURL = 'http://116.62.155.98:8888/api/private/v1/';
Vue.prototype.$http = axios;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
