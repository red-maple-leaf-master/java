package com.example.demo.repository;

import com.example.demo.entity.GraphQuery;
import com.example.demo.utils.Ne04jUtils;
import com.example.demo.utils.Neo4jUtil;
import com.example.demo.utils.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class KgRepository implements KGGraphRepository {

    /**
     * 在ne4oj中创建一个主节点用唯一标识创建
     *
     * @param label 唯一标识
     */
    public void createDomain(String label) {
        try {
            String cypherSql = String.format(
                    "create (n:`%s`{entityType:0,name:''}) return id(n)", label);
            Neo4jUtil.runCypherSql(cypherSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询图谱节点和关系
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public HashMap<String, Object> queryGraphResult(GraphQuery query) {
        HashMap<String, Object> nr = new HashMap<String, Object>();
        try {
            // 获取图谱名称
            String domain = query.getDomain();
            // MATCH (n:`症状`) -[r]-(m:症状) where r.name='治疗' or r.name='危险因素' return n,m
            if (!StringUtil.isBlank(domain)) {
                String cqr = "";
                List<String> lis = new ArrayList<>();
                // relation 保存的是 节点name的值
                if (query.getRelation() != null && query.getRelation().length > 0) {
                    for (String r : query.getRelation()) {
                        String it = String.format("r.name='%s'", r);
                        lis.add(it);
                    }
                    // 组装条件
                    cqr = String.join(" or ", lis);
                }

                String cqWhere = "";

                if (!StringUtil.isBlank(query.getNodeName()) || !StringUtil.isBlank(cqr)) {
                    if (!StringUtil.isBlank(query.getNodeName())) {
                        // 精确查找
                        if (query.getMatchType() == 1) {
                            cqWhere = String.format("where n.name ='%s' ", query.getNodeName());
                        } else {
                            // 模糊查找
                            cqWhere = String.format("where n.name contains('%s')", query.getNodeName());
                        }
                    }
                    String nodeOnly = cqWhere;
                    // 如果条件为空则不拼接
                    if (!StringUtil.isBlank(cqr)) {
                        // 拼接查询条件
                        if (StringUtil.isBlank(cqWhere)) {
                            // cqWhere 为null 说明 还没有拼接where
                            cqWhere = String.format(" where ( %s )", cqr);
                        } else {
                            cqWhere += String.format(" and ( %s )", cqr);
                        }

                    }
                    // 下边的查询查不到单个没有关系的节点,考虑要不要左箭头
                    String nodeSql = String.format("MATCH (n:`%s`) <-[r]->(m) %s return * limit %s", domain, cqWhere,
                            query.getPageSize());

                    // nodeSql 拼接好的 cql 语句
                    HashMap<String, Object> graphNode = Neo4jUtil.getGraphNodeAndShip(nodeSql);
                    Object node = graphNode.get("node");
                    // 没有关系显示则显示节点
                    if (node != null) {
                        nr.put("node", graphNode.get("node"));
                        nr.put("relationship", graphNode.get("relationship"));
                    } else {
                        // 查询到的节点为null  说明需要查询的节点为单节点 没有关系 需要单独查询
                        String nodecql = String.format("MATCH (n:`%s`) %s RETURN distinct(n) limit %s", domain,
                                nodeOnly, query.getPageSize());

                        List<Map<String, Object>> nodeItem = Ne04jUtils.getGraphNode(nodecql);

                        nr.put("node", nodeItem);
                        nr.put("relationship", new ArrayList<HashMap<String, Object>>());
                    }
                } else {
                    //  没有节点名称就返回全部的 节点和关系
                    String nodeSql = String.format("MATCH (n:`%s`) %s RETURN distinct(n) limit %s", domain, cqWhere,
                            query.getPageSize());
                    List<Map<String, Object>> graphNode = Ne04jUtils.getGraphNode(nodeSql);
                    nr.put("node", graphNode);
                    String domainSql = String.format("MATCH (n:`%s`)<-[r]-> (m) %s RETURN distinct(r) limit %s", domain,
                            cqWhere, query.getPageSize());// m是否加领域
                    List<HashMap<String, Object>> graphRelation = Neo4jUtil.getGraphRelationShip(domainSql);
                    nr.put("relationship", graphRelation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nr;
    }
}
