<template>
    <el-row class="warp">
        <el-col :span="24" class="warp-breadcrum">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
                <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            </el-breadcrumb>
        </el-col>

        <el-col :span="24" class="warp-main" v-loading="loading" element-loading-text="拼命加载中">
            <!--工具条-->
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form :inline="true" :model="filters">
                    <el-form-item>
                        <el-input v-model="filters.name" placeholder="用户名/用户编号" style="min-width: 240px;" @keyup.enter.native="handleSearch"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="handleSearch">查询</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="handleCreate">新建用户</el-button>
                    </el-form-item>
                </el-form>
            </el-col>

            <!--列表-->
            <el-table :data="users" highlight-current-row v-loading="loading" style="width: 100%;" @row-click="handleDetail">
                <<el-table-column prop="username" label="用户名" width="150" sortable>
                 </el-table-column>
                <el-table-column prop="name" label="姓名" width="150" sortable>
                </el-table-column>
                <el-table-column prop="mail" label="邮箱" min-width="150" sortable>
                </el-table-column>
                <el-table-column prop="phone" label="电话" min-width="120" sortable>
                </el-table-column>
                <el-table-column prop="create_date" label="创建时间" min-width="120" sortable>
                </el-table-column>
                <el-table-column prop="modify_date" label="修改时间" min-width="120"  sortable>
                </el-table-column>
            </el-table>

        </el-col>
    </el-row>
</template>

<script>
    import API from '../../api/api_user';

    export default {
        data() {
            return {
                filters: {
                    name: ''
                },
                loading: false,
                users: [],
                total: 0,
                page: 1,
                limit: 10,
                loading: false
            }
        },
        methods: {
            handleCurrentChange(val) {
                this.page = val;
                this.search();
            },
            handleSearch(){
                this.total = 0;
                this.page = 1;
                this.search();
            },
            handleCreate(){
                this.$router.push({path: '/user/createUser'});
            },
            //获取用户列表
            search: function () {
                let that = this;
                let params = {
                   username: that.filters.name, pwd:""
                };
                that.loading = true;
                API.findList(params).then(function (result) {
                    that.loading = false;
                    localStorage.setItem('users', JSON.stringify(result.data));
                    that.users = JSON.parse(localStorage.getItem('users'));
                }, function (err) {
                    that.loading = false;
                    that.$message.error({showClose: true, message: err.toString(), duration: 2000});
                }).catch(function (error) {
                    that.loading = false;
                    console.log(error);
                    that.$message.error({showClose: true, message: '请求出现异常', duration: 2000});
                });
            },

            handleDetail(row){
                let that = this;
                that.$router.push({
                    name: '用户信息',
                    params:{
                    userName:row.username
                }});
            },


        },
        mounted() {
            this.handleSearch()
        }
    }
</script>

<style scoped>
    .warp-breadcrum{
        height: 40px;
        margin-top: 5px;
        margin-left: 2px;
    }
</style>
