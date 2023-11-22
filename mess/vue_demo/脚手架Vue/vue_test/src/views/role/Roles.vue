<template>
  <div>
      <!-- 面包屑导航 -->
      <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>用户管理</el-breadcrumb-item>
          <el-breadcrumb-item>角色列表</el-breadcrumb-item>
      </el-breadcrumb>
      <!-- 卡片视图-->
      <el-card class="box-card">
          <!-- 添加角色按钮区域 -->
          <el-row>
              <el-col>
                  <el-button type="primary">添加角色</el-button>
              </el-col>
          </el-row>
          <!-- 角色列表区域 -->
          <!-- row-key="id" 是2019年3月提供的新特性，
          if there's nested data, rowKey is required.
          如果这是一个嵌套的数据，rowkey 是必须添加的属性 -->
          <el-table row-key="id" :data="roleList" border stripe>
              <!-- 添加展开列 -->
              <el-table-column type="expand">
                  <template slot-scope="scope">
                      <el-row :class="['bdbottom', i1 === 0 ? 'bdtop' : '', 'vcenter']" v-for="(item1, i1) in scope.row.children" :key="item1.id">
                          <!-- 渲染一级权限 -->
                          <el-col :span="5">
                              <el-tag closable @close="removeRightById(scope.row, item1.id)">{{item1.authName}}</el-tag>
                              <i class="el-icon-caret-right"></i>
                          </el-col>
                          <!-- 渲染二级和三级权限 -->
                          <el-col :span="19">
                              <!-- 通过 for 循环 嵌套渲染二级权限 -->
                              <el-row :class="[i2 === 0 ? '' : 'bdtop', 'vcenter']" v-for="(item2, i2) in item1.children" :key="item2.id">
                                  <el-col :span="6">
                                      <el-tag type="success" closable @close="removeRightById(scope.row, item2.id)">{{item2.authName}}</el-tag>
                                      <i class="el-icon-caret-right"></i>
                                  </el-col>
                                  <el-col :span="18">
                                      <el-tag type="warning" v-for="(item3, i3) in item2.children" :key="item3.id" closable @close="removeRightById(scope.row, item3.id)">{{item3.authName}}</el-tag>
                                  </el-col>
                              </el-row>
                          </el-col>
                      </el-row>

                      <!-- <pre>
                        {{scope.row}}
                      </pre> -->
                  </template>
              </el-table-column>
              <el-table-column type="index"></el-table-column>
              <el-table-column label="角色名称" prop="roleName"></el-table-column>
              <el-table-column label="角色描述" prop="roleDesc"></el-table-column>
              <el-table-column label="操作" width="300px">
                  <template slot-scope="scope">
                      <el-button size="mini" type="primary" icon="el-icon-edit">编辑</el-button>
                      <el-button size="mini" type="danger" icon="el-icon-delete">删除</el-button>
                      <el-button size="mini" type="warning" icon="el-icon-setting">分配权限</el-button>
                  </template>
              </el-table-column>
          </el-table>

      </el-card>
  </div>
</template>

<script>
    export default {
        name: "Roles",
        data(){
            return {
                roleList:[]
            }
        },created(){
            this.getRoleList();
        },methods:{
            async getRoleList(){
                const {data:res} = await this.$http.get('roles')
                //如果返回状态为异常状态则报错并返回
                // if (res.meta.status !== 200)
                //     return this.$message.error('获取角色列表失败')
                // //如果返回状态正常，将请求的数据保存在data中
                // this.roleList = res.data
                console.log(res.data);
                this.roleList = res.data;
            }
        }
    }
</script>

<style scoped>
    .el-tag {
        margin: 7px;
    }

    .bdtop {
        border-top: 1px solid #eee;
    }

    .bdbottom {
        border-bottom: 1px solid #eee;
    }

    .vcenter {
        display: flex;
        align-items: center;
    }
</style>
