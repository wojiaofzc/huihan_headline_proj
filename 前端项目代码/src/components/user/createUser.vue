<template>
    <el-row class="warp">
        <el-col :span="24" class="warp-breadcrum" :loading="loading">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/User/List'}"><b>用户管理</b></el-breadcrumb-item>
                <el-breadcrumb-item>新建用户</el-breadcrumb-item>
            </el-breadcrumb>
        </el-col>

        <el-col :span="15" class="warp-main" >
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item prop="username" label="用户名">
                    <el-input v-model="form.username" ></el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码">
                    <el-input v-model="form.password" ></el-input>
                </el-form-item>
                <el-form-item prop="name" label="姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item prop="phone" label="电话">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item prop="mail" label="邮箱">
                    <el-input v-model="form.mail"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleCreateUser">新建</el-button>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<script>
    import API from '../../api/api_user';
    import moment from  'moment';

    export default {
        data() {
            return {
                loading: false,
                form:{
                    username: "",
                    password: "",
                    name:"",
                    phone:"",
                    mail:"",
                    create_date:"",
                    modify_date:""
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ],
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ],
                    phone: [
                        {required: true, message: '请输入手机号', trigger: 'blur'}
                    ],
                    mail: [
                        {required: true, message: '请输入邮箱', trigger: 'blur'},
                        {type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur,change'}
                    ]
                },
            }
        },
        methods: {
            handleCreateUser() {
                let that = this;
                that.$refs.form.validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        let args = {
                            username: that.form.username,
                            password : that.form.password,
                            name: that.form.name,
                            phone: that.form.phone,
                            mail: that.form.mail,
                            create_date: moment(new Date()).format('YYYY-MM-DD HH:mm:ss'),
                            modify_date: moment(new Date()).format('YYYY-MM-DD HH:mm:ss')
                        };
                        API.createUser(args).then(function (result) {
                            if (result.code == 0) {
                                that.$message.success({showClose: true, message: '添加用户成功', duration: 2000});
                                that.$router.push({path: '/user/List'});
                            } else {
                                that.$message.error({showClose: true, message: result.errmsg, duration: 2000});
                            }
                        }, function (err) {
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .warp-breadcrum{
        height: 40px;
        margin-top: 5px;
        margin-left: 2px;
    }
    .warp-main{
        margin-top: 40px;
        margin-left: 200px;
    }

</style>
