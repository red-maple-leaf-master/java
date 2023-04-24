package top.oneyi;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson.JSON;
import netscape.javascript.JSObject;
import org.activiti.engine.impl.util.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.mapper.NormIndustryMapper;
import top.oneyi.pojo.NormIndustry;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class NormIndustryTest {


    @Resource
    private NormIndustryMapper normIndustryMapper;

    @Test
    public void getTreeData() {
        List<NormIndustry> father = normIndustryMapper.findByParentCode("0");
        for (NormIndustry firstNormIndustry : father) {
            List<NormIndustry> second = normIndustryMapper.findByParentCode(firstNormIndustry.getCode());
/*            for (NormIndustry secondNormIndustry : second) {
                List<NormIndustry> thirdParentCode = normIndustryMapper.findByParentCode(secondNormIndustry.getCode());
                secondNormIndustry.setNormIndustryList(thirdParentCode);
            }*/
            for (NormIndustry normIndustry : second) {
                normIndustry.setHasChild(0);
            }
            firstNormIndustry.setChildrenList(second);
        }

        NormIndustry normIndustry = father.get(0);
        System.out.println("normIndustry = " + normIndustry);

    }


    @Test
    public void getTreeData2() {
//        List<NormIndustry> father = normIndustryMapper.getAllFater();
        List<NormIndustry> all = normIndustryMapper.findAll();
        TreeNodeConfig config = new TreeNodeConfig();
        config.setIdKey("id");
        config.setNameKey("name");
        config.setDeep(3);
        config.setChildrenKey("childrenList");
        config.setParentIdKey("parentCode");
        config.setWeightKey("id");


        List<Tree<String>> treeList = TreeUtil.build(all, "0", config, ((object, treeNode) -> {
            treeNode.putExtra("id", object.getId().toString());
            treeNode.putExtra("code", object.getCode());
            treeNode.putExtra("name", object.getName());
            treeNode.putExtra("parentCode", object.getParentCode());
            treeNode.putExtra("hasChild", object.getHasChild());

        }));
        System.out.println("treeList = \n" + JSON.toJSONString(treeList));

    }


    @Test
    public void test01() {
// 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();

        nodeList.add(new TreeNode<>("1", "0", "系统管理", 5));
        nodeList.add(new TreeNode<>("11", "1", "用户管理", 222222));
        nodeList.add(new TreeNode<>("111", "11", "用户添加", 0));
        nodeList.add(new TreeNode<>("2", "0", "店铺管理", 1));
        nodeList.add(new TreeNode<>("21", "2", "商品管理", 44));
        nodeList.add(new TreeNode<>("221", "2", "商品管理2", 2));
        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
// 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("order");
        treeNodeConfig.setIdKey("rid");
// 最大递归深度
        treeNodeConfig.setDeep(3);

//转换器
        List<Tree<String>> treeNodes = TreeUtil.build(nodeList, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setWeight(treeNode.getWeight());
                    tree.setName(treeNode.getName());
                    // 扩展属性 ...
                    tree.putExtra("extraField", 666);
                    tree.putExtra("other", new Object());
                });
        System.out.println("treeNodes = " + treeNodes);
    }

    @Test
    public void getTreeData3() {
        // 获取所有的数据,进行树状转换
        List<NormIndustry> all = normIndustryMapper.findAll();
        List<NormIndustry> father = recursion(all);
        NormIndustry normIndustry = father.get(0);
        String s = JSON.toJSONString(normIndustry);
        System.out.println("s = " + s);
    }


    private List<NormIndustry> recursion(List<NormIndustry> all) {
        List<NormIndustry> father = all.stream().filter(s -> {
            // flag 为true 说明 是顶级节点
            boolean flag = s.getParentCode().equals("0");
            List<NormIndustry> children = all.stream().filter(f -> f.getParentCode().equals(s.getCode())).collect(Collectors.toList());
            // 遍历二级节点
            children.stream().filter(c -> {
                boolean equals = c.getHasChild().equals("1");
                List<NormIndustry> collect = children.stream().filter(v -> v.getParentCode().equals(c.getCode())).collect(Collectors.toList());
                c.setChildrenList(collect);
                return equals;
            });
            s.setChildrenList(children);
            return flag;
        }).collect(Collectors.toList());
        return father;
    }
}
