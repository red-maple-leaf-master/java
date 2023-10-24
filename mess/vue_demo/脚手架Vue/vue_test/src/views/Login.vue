<template>
    <div class="login_container">
        <div class="login_box">
            <div class="avatar_box">
                <img src="../assets/logo.png" alt="">
            </div>

            <el-form :model="loginForm" :rules="loginFormRules" ref="LoginFormRef" label-width="0px" class="login_form">
                <el-form-item prop="username" prefix-icon="iconfont icon-user">
                    <el-input v-model="loginForm.username" prefix-icon="iconfont icon-user"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" type="password"
                              prefix-icon="iconfont icon-3702mima"></el-input>
                </el-form-item>
                <!-- 按钮 -->
                <el-form-item class="btns">
                    <el-button type="primary" @click="login">登录</el-button>
                    <el-button type="info" @click="resetLoginForm">重置</el-button>
                </el-form-item>
            </el-form>


        </div>

    </div>

</template>
<script>
    export default {
        name: "Login",
        data() {
            return {
                //数据绑定
                loginForm: {
                    username: "admin",
                    password: "123456"
                },
                // 表单验证规则
                loginFormRules: {
                    username: [
                        {required: true, message: '请输入登录名', trigger: 'blur'},
                        {min: 3, max: 5, message: '长度在 3 到 10 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
                    ],
                },

            }
        },
        methods: {
             login() {
                this.$refs.LoginFormRef.validate(async valid => {
                    if(!valid){
                        return
                    }
                    const {data: res} = await this.$http.post('login', this.loginForm);
                    if(res.meta.status !== 200){
                        return this.$message.error('登录失败:'+res.meta.msg);
                    }
                    this.$message.success('登录成功')
                    window.sessionStorage.setItem('token',res.data.token);
                    this.$router.push('/home')
                });

            },
            // 添加表单重置方法
            resetLoginForm() {
                this.$refs.LoginFormRef.resetFields();
            }
        }

    }
</script>

<style scoped>
    .login_container {
        background-color: #6dd5ed;
        height: 100%;
    }

    .login_box {
        width: 450px;
        height: 300px;
        background-color: #fff;
        border-radius: 3px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
    }

    .login_box .avatar_box {
        width: 120px;
        height: 120px;
        border-radius: 50%;
        background-color: #fff;
        border: 1px solid #eee;
        padding: 10px;
        box-shadow: 0 0 10px #ddd;
        position: absolute;
        left: 50%;
        top: 0;
        transform: translate(-50%, -50%);
    }

    .login_box .avatar_box img {
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: #eee;
    }

    .login_form {
        width: 100%;
        position: absolute;
        bottom: 0;
        padding: 0 20px;
        box-sizing: border-box;
    }

    .btns {
        display: flex;
        justify-content: center;
    }
</style>
