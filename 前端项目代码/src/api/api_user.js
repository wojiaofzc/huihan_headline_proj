
import * as API from './'

export default {
    //登录
    login: params => {
        return API.POST('/api/users/login', params)
    },

    //添加用户信息
    createUser: params => {
        return API.POST('/api/users/newUser', params)
    },

    //修改个人信息
    changeProfile: params => {
        return API.POST('/api/users/update', params)
    },

    //查询获取user列表(通过page分页)
    findList: params => {
        return API.POST('/api/users', params)
    },

    //查询某个user的详细信息
    findUser: params => {
        return API.POST('/api/users/search', params)
    },

    delete: params => {
        return API.POST('/api/users/delete',params)
    }
}