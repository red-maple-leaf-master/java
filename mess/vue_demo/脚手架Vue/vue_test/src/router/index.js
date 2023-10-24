import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'


Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/Home.vue'),
    redirect: '/welcome',
    children: [
      { path: '/welcome', component: () => import('../views/Welcome.vue') },
      { path: '/users', component: () => import('../views/user/Users.vue') }
    ]
  },
];



const router = new VueRouter({
  routes
});

// 挂载路由守卫,to 表示将要访问的路径 from 表示从哪里来,next 是下一个要做的操作
router.beforeEach((to,from,next)=>{
  if(to.path ==='/login'){
    return next();
  }
  // 获取token
  const tokenStr = window.sessionStorage.getItem('token');

  if(!tokenStr){
    return next('/login');
  }

  next();
});

export default router
