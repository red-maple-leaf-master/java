<template>
    <div>
        <el-container class="home-container">
            <!-- 头部区域 -->
            <el-header>
                <div>
                    <img src="../assets/logo.png" alt="">
                    <!-- 顶部标题 -->
                    <span>电商后台管理系统</span>
                </div>
                <el-button type="info" @click="logout"> 退出</el-button>
            </el-header>
            <!-- 页面主体区域 -->
            <el-container>
                <!-- 侧边栏 -->
                <el-aside :width="isCollapse ? '64px':'200px'">
                    <!-- 伸缩侧边栏按钮 -->
                    <div class="toggle-button" @click="toggleCollapse"> ||| </div>
                    <!-- 侧边栏菜单区域 -->
                    <el-menu background-color="#333744" text-color="#fff" active-text-color="#409EFF" unique-opened :collapse="isCollapse" :collapse-transition="false" router :default-active="activePath">
                        <!-- 一级菜单-->
                        <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
                            <template slot="title">
                                <i :class="iconsObj[item.id]"></i>
                                <span>{{item.authName}}</span>
                            </template>
                            <!-- 二级菜单-->
                            <el-menu-item :index="'/' + subItem.path" v-for="subItem in item.children" :key="subItem.id" @click="saveNavState('/' + subItem.path)">
                                <template slot="title">
                                    <!-- 图标 -->
                                    <i class="el-icon-menu"></i>
                                    <!-- 文本 -->
                                    <span>{{subItem.authName}}</span>
                                </template>
                            </el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <!-- 主体结构 -->
                <el-main>
                    <!-- 路由占位符 -->
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data() {
            return {
                menuList: null,
                iconsObj: {
                    '125': 'iconfont icon-user',
                    '103': 'iconfont icon-tijikongjian',
                    '101': 'iconfont icon-shangpin',
                    '102': 'iconfont icon-danju',
                    '145': 'iconfont icon-baobiao'
                },
                isCollapse: false,
                // 被激活的链接地址
                activePath: ''
            }
        },
        created() {
            this.getMenuList();
            this.activePath = window.sessionStorage.getItem('activePath')
        },
        methods: {
            logout() {

                window.sessionStorage.clear();
                this.$router.push('/login');
            },
            // 获取菜单数据
            async getMenuList() {
                const {data: res} = await this.$http.get('menus')
                if (res.meta.status !== 200) return this.$message.error('res.meta.msg');
                this.menuList = res.data;
                console.log(res.data);
            },
            toggleCollapse() {
                this.isCollapse = !this.isCollapse;
            },
            // 保存链接的激活状态
            saveNavState(activePath) {
                window.sessionStorage.setItem('activePath', activePath)
                this.activePath = activePath
            }
        },

    }
</script>

<style scoped>
    .home-container {
        height: 100vh;
    }

    img {
        width: 50px;
        border-radius: 50%;
        vertical-align: middle
    }

    .toggle-button {
        cursor: pointer;
        color: #fff;
        text-align: center;
        background-color: #373d41;
        font-size: 10px;
        line-height: 24px;
        letter-spacing: 0.2em;
    }

    .el-header {
        background-color: #373d41;
        display: flex;
        justify-content: space-between;
        padding-left: 0;
        align-items: center;
        color: #fff;
        font-size: 20px;
    }

    .el-header > div {
        display: flex;
        align-items: center;
    }

    .el-header > div span {
        margin-left: 15px;
    }

    .iconfont {
        margin-right: 10px;
    }

    .el-aside {
        background-color: #333744;
        transition: 0.3s; /* 0.5 秒过渡效果在侧边栏中滑动 */
    }

    .el-aside .el-menu {
        border-right: none;
    }


    .el-main {
        background-color: #eaedf1;
    }

</style>
