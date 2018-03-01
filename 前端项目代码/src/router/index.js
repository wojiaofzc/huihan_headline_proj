import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home.vue'
import UserList from '../components/user/List.vue'
import UserDetail from '../components/user/userDetail.vue'
import NewUser from  "../components/user/createUser.vue"
import UserProfile from  '../components/user/profile.vue'
import UserChangePwd from  '../components/user/changepwd.vue'


// 懒加载方式，当路由被访问的时候才加载对应组件
const Login = resolve => require(['../components/Login.vue'], resolve)

Vue.use(Router)

let router;


router = new Router({

    routes: [

        {
            path: '/',
            name: '登录',
            component: Login
        },
        {
            path: '/Home',
            component: Home,
            name: '用户管理',
            menuShow: true,
            children: [
                {path: '/user/List', component: UserList, name: '用户列表', menuShow: true},
                {path: '/user/userDetail', component: UserDetail, name: '用户信息', menuShow: false},
                {path: '/user/createUser', component: NewUser, name: '新建用户', menuShow: true}
            ]
        },
        {
            path: '/Home',
            component: Home,
            name: '设置',
            menuShow: false,
            iconCls: 'iconfont icon-setting1',
            children: [
                {path: '/user/profile', component: UserProfile, name: '个人信息', menuShow: false},
                {path: '/user/changepwd', component: UserChangePwd, name: '修改密码', menuShow: false}
            ]
        }
    ]

});

router.beforeEach((to,from,next)=>{
    var userInfo= JSON.parse(sessionStorage.getItem('access-user'));//获取浏览器缓存的用户信息
    if(userInfo){//如果有就直接到首页咯
        next('/Home');
    }else {
        if (to.path == '/') {//如果是登录页面路径，就直接next()
            next();
        } else {//不然就跳转到登录；
            next('/');
        }
    }
});

export default router
