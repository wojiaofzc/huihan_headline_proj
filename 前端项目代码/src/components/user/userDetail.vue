<template>
    <el-row class="warp">
        <el-col :span="24" class="warp-breadcrum" :loading="loading">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
                <el-breadcrumb-item>用户列表</el-breadcrumb-item>
                <el-breadcrumb-item>用户信息</el-breadcrumb-item>
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
                <el-form-item prop="create_date" label="创建时间">
                    <el-input v-model="form.create_date" disabled></el-input>
                </el-form-item>
                <el-form-item prop="modify_date" label="修改时间">
                    <el-input v-model="form.modify_date" disabled></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleSaveProfile">修改并保存</el-button>
                    <el-button type="primary" @click="handleDelete">删除用户</el-button>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<script>
    import API from '../../api/api_user';
    import {bus} from '../../bus.js';
    import moment from  'moment';

    export default {
        data() {
            return {
                loading: false,
                form: {
                    username: "",
                    password: "",
                    name: "",
                    phone: "",
                    mail: "",
                    create_date: "",
                    modify_date: ""
                },
                rules: {
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
            handleSaveProfile() {
                let that = this;
                that.$refs.form.validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        let args = {
                            username: that.form.username,
                            password: that.form.password,
                            name: that.form.name,
                            phone: that.form.phone,
                            mail: that.form.mail,
                            create_date: that.form.create_date,
                            modify_date: moment(new Date()).format('YYYY-MM-DD HH:mm:ss')
                        };
                        API.changeProfile(args).then(function (result) {
                            if (result.code == 0) {
                                that.$message.success({showClose: true, message: '修改成功', duration: 2000});
                            } else {
                                that.$message.error({showClose: true, message: result.errmsg, duration: 2000});
                            }
                        }, function (err) {
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                });
            },
            handleDelete() {
                let that = this;
                this.$confirm('确认删除吗?', '提示', {
                    confirmButtonClass: 'el-button--warning'
                }).then(() => {
                    that.loading = false;
                    let args = {
                        username: that.form.username,
                    };
                    API.delete(args).then(function (result) {
                        if (result.code == 0) {
                            that.$message.success({showClose: true, message: '删除用户成功', duration: 2000});
                            that.$router.push({path: '/user/List'});
                        } else {
                            that.$message.error({showClose: true, message: result.errmsg, duration: 2000});
                        }
                    }, function (err) {
                    }).catch(function (error) {
                        console.log(error);
                    });
                }).catch(() => {});
            },
        },
        mounted() {
            console.log(this.$route.params.userName);
            let that = this;
            let loginParams = {username: that.$route.params.userName, pwd:""};
            API.findUser(loginParams).then(function (result) {
                if (result.code == 0) {
                    that.form.username = result.data.username;
                    that.form.password = result.data.password;
                    that.form.name = result.data.name;
                    that.form.phone = result.data.phone;
                    that.form.mail = result.data.mail;
                    that.form.create_date = result.data.create_date;
                    that.form.modify_date = result.data.modify_date;
                }
            }, function (erwr) {
                console.log(err);
            }).catch(function (error) {
                console.log(error);
            });
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
