<template>
    <div>
        <h3>用户列表组件</h3>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            <el-breadcrumb-item>用户列表</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 卡片视图-->
        <el-card class="box-card">
            <!-- 搜索与添加区域 -->
            <el-row :gutter="20">
                <el-col :span="4">
                    <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getUserList">
                        <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" @click="dialogFormVisible = true">添加用户</el-button>
                </el-col>
            </el-row>
            <!--表格-->
            <el-table :data="userList" border stripe>
                <el-table-column type="index"></el-table-column>
                <el-table-column label="姓名" prop="username"></el-table-column>
                <el-table-column label="邮箱" prop="email"></el-table-column>
                <el-table-column label="电话" prop="mobile"></el-table-column>
                <el-table-column label="角色" prop="role_name"></el-table-column>
                <el-table-column label="状态">
                    <template slot-scope="scope">
                        <el-switch v-model="scope.row.mg_state" @change="userStateChanged(scope.row)"></el-switch>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <!-- 修改 -->
                        <el-button type="primary" icon="el-icon-edit" size='mini'></el-button>
                        <!-- 删除 -->
                        <el-button type="danger" icon="el-icon-delete" size='mini'></el-button>
                        <!-- 分配角色 -->
                        <el-tooltip effect="dark" content="分配角色" placement="top" :enterable="false">
                            <el-button type="warning" icon="el-icon-setting" size='mini'></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <!--分页区域-->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="queryInfo.pagenum"
                :page-sizes="[2, 4, 6, 8]"
                :page-size="queryInfo.pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>

        <!--对话框组件-->
        <!--添加用户-->
        <el-dialog title="添加用户" :visible.sync="dialogFormVisible" width="50%" @close="addDialogClosed">
            <!-- 对话框主体区域 -->
            <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="addForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="addForm.password"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="addForm.email"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="mobile">
                    <el-input v-model="addForm.mobile"></el-input>
                </el-form-item>
            </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="addUser">确 定</el-button>
                </div>

        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "Users",
        data() {
            var checkEmail = (rule ,value, cb)=>{
                const regEmail = /^\w+@\w+(\.\w+)+$/;
                if (regEmail.test(value)) {
                    return cb()
                }
                //返回一个错误提示
                cb(new Error('请输入合法的邮箱'))
            };
            //验证手机号码的规则
            var checkMobile = (rule, value, cb) => {
                const regMobile = /^1[34578]\d{9}$/
                if (regMobile.test(value)) {
                    return cb()
                }
                //返回一个错误提示
                cb(new Error('请输入合法的手机号码'))
            };
            return {
                queryInfo: {
                    query: '',
                    pagenum: 1,
                    pagesize: 2
                },
                userList: [],
                total: 0,
                dialogFormVisible: false,
                addForm: {
                    username: '',
                    password: '',
                    email: '',
                    mobile: ''
                },
                formLabelWidth: '120px',
                addFormRules: {
                    username: [
                        {required: true, message: '请输入用户名称', trigger: 'blur'},
                        {
                            min: 3,
                            max: 10,
                            message: '用户名在3~10个字符之间',
                            trigger: 'blur'
                        }
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {
                            min: 6,
                            max: 15,
                            message: '用户名在6~15个字符之间',
                            trigger: 'blur'
                        }
                    ],
                    email: [
                        {required: true, message: '请输入邮箱', trigger: 'blur'},
                        {validator: checkEmail, message: '邮箱格式不正确，请重新输入', trigger: 'blur'}
                    ],
                    mobile: [
                        {required: true, message: '请输入手机号码', trigger: 'blur'},
                        {validator: checkMobile, message: '手机号码不正确，请重新输入', trigger: 'blur'}
                    ]
                }

            }
        },
        created() {
            this.getUserList();
        },
        methods: {
            async getUserList() {
                const {data: res} = await this.$http.get('users', {
                    params: this.queryInfo
                });
                if (res.meta.status !== 200) return this.$message.error('获取用户列表失败');
                this.userList = res.data.users;
                this.total = res.data.total;
            },
            handleEdit(index, row) {
                console.log(index, row);
            },
            handleDelete(index, row) {
                console.log(index, row);
            },
            handleSizeChange(val) {
                this.queryInfo.pagesize = val;
                this.getUserList();
            },
            handleCurrentChange(val) {
                this.queryInfo.pagenum = val;
                this.getUserList();
            },
            async userStateChanged(row) {
                // 发送请求
                const {data: res} = await this.$http.put(`users/${row.id}/state/${row.mg_state}`);
                if (res.meta.status !== 200) return this.$message.error('修改状态失败');
                this.$message.success('更新状态成功');

            },
            addDialogClosed(){
                //对话框关闭之后，重置表达
                this.$refs.addFormRef.resetFields();
            },
            addUser(){
                this.$refs.addFormRef.validate(async valid =>{
                    if(!valid) return this.$message.error('请填写完整用户信息');
                    const {data : res } = await this.$http.post('users',this.addForm);
                    if(res.meta.status !== 201) return this.$message.error('添加用户失败');
                    this.$message.success('添加用户成功');
                    this.dialogFormVisible=false;
                    //重新请求最新的数据
                    this.getUserList()
                })
            }
        }
    }
</script>

<style scoped>
    .el-table {
        margin-top: 15px;
    }

    .box-card {
        margin: 15px 0;
        width: 98%;
    }
</style>
