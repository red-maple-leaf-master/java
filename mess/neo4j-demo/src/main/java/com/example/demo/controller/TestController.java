package com.example.demo.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo.entity.Dept;
import com.example.demo.entity.RelationShip;
import com.example.demo.repository.DeptRepository;
import com.example.demo.repository.RelationShipRepository;

import com.example.demo.utils.JsonUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.beanutils.BeanMap;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class TestController {

    @Resource
    private DeptRepository deptRepository;
    @Resource
    private RelationShipRepository relationShipRepository;


    @Resource
    private Session session;

    /**
     * 查询图谱所有节点
     *
     * @return
     */
    @GetMapping("/test")
    public List<Map<String, Object>> test() {
        String sql = "MATCH (n:`地图_y_Oy3G`)  RETURN distinct(n) limit 500";
        Result result = session.query(sql, new HashMap<>());
        List<Map<String, Object>> list = new ArrayList<>();
        for (Map<String, Object> next : result) {
            list.add(next);
        }
        return list;
    }

    /**
     * 查询图谱所有关系
     *
     * @return
     */
    @GetMapping("/test01")
    public List<Object> test01() {
        String cypherSql = "MATCH (n:`地图_y_Oy3G`)<-[r]-> (m)  RETURN distinct(r) limit 500";
        List<Object> list = new ArrayList<>();
        Result result = session.query(cypherSql, new HashMap<>());
        for (Map<String, Object> next : result) {
            Object object = next.get("r");
            list.add(object);


            String s = JSON.toJSONString(object);
            JSONObject jsonObject = JSON.parseObject(s);
//            System.out.println("jsonObject.get(\"id\") = " + jsonObject.get("id"));
//            System.out.println("jsonObject.get(\"propertyList\") = " + jsonObject.get("propertyList"));
//            String propertyList = jsonObject.getString("propertyList");
//            JSONArray objects = JSON.parseArray(propertyList);
//            System.out.println("objects = " + objects.getString(0));
//            JSONObject js = JSON.parseObject(objects.getString(0));
//            System.out.println("js.getString(\"value\") = " + js.getString("value"));
            // 使用工具类 来获取json的值
            Object name = JsonUtils.getValueByKey(jsonObject, "value");
            System.out.println("name = " + name);
            System.out.println(JsonUtils.getValueByKey(jsonObject, "id"));
            System.out.println(JsonUtils.getValueByKey(jsonObject, "startNode"));
            System.out.println(JsonUtils.getValueByKey(jsonObject, "endNode"));


        }
        return list;
    }

    public List<Object> objToList(Object obj) {
        List<Object> list = new ArrayList<Object>();
        if (obj instanceof ArrayList<?>) {
            for (Object o : (List<?>) obj) {
                list.add(o);
            }
            return list;
        }
        return null;
    }

    /**
     * CEO
     * -设计部
     * - 设计1组
     * - 设计2组
     * -技术部
     * - 前端技术部
     * - 后端技术部
     * - 测试技术部
     */
    @GetMapping("create")
    public void create() {
        Dept CEO = Dept.builder().deptName("CEO").build();
        Dept dept1 = Dept.builder().deptName("设计部").build();
        Dept dept11 = Dept.builder().deptName("设计1组").build();
        Dept dept12 = Dept.builder().deptName("设计2组").build();

        Dept dept2 = Dept.builder().deptName("技术部").build();
        Dept dept21 = Dept.builder().deptName("前端技术部").build();
        Dept dept22 = Dept.builder().deptName("后端技术部").build();
        Dept dept23 = Dept.builder().deptName("测试技术部").build();
        List<Dept> depts = new ArrayList<>(Arrays.asList(CEO, dept1, dept11, dept12, dept2, dept21, dept22, dept23));
        deptRepository.saveAll(depts);

        RelationShip relationShip1 = RelationShip.builder().parent(CEO).child(dept1).build();
        RelationShip relationShip2 = RelationShip.builder().parent(CEO).child(dept2).build();
        RelationShip relationShip3 = RelationShip.builder().parent(dept1).child(dept11).build();
        RelationShip relationShip4 = RelationShip.builder().parent(dept1).child(dept12).build();
        RelationShip relationShip5 = RelationShip.builder().parent(dept2).child(dept21).build();
        RelationShip relationShip6 = RelationShip.builder().parent(dept2).child(dept22).build();
        RelationShip relationShip7 = RelationShip.builder().parent(dept2).child(dept23).build();

        List<RelationShip> relationShips = new ArrayList<>(
                Arrays.asList(
                        relationShip1,
                        relationShip2,
                        relationShip3,
                        relationShip4,
                        relationShip5,
                        relationShip6,
                        relationShip7));

        relationShipRepository.saveAll(relationShips);
    }

    /**
     * 根据id 查找节点的关系
     *
     * @param id
     * @return
     */
    @GetMapping("get")
    public RelationShip get(Long id) {
        Optional<RelationShip> byId = relationShipRepository.findById(id);
        return byId.orElse(null);
    }

    /**
     * 删除关系
     *
     * @param id
     */
    @GetMapping("deleteRelationShip")
    public void deleteRelationShip(Long id) {
        relationShipRepository.deleteById(id);
    }

    /**
     * 删除节点
     *
     * @param id
     */
    @GetMapping("deleteDept")
    public void deleteDept(Long id) {
        deptRepository.deleteById(id);
    }

    /**
     * 删除所有的节点
     */
    @GetMapping("deleteAll")
    public void deleteAll() {
        deptRepository.deleteAll();
        relationShipRepository.deleteAll();
    }
}
