package com.example.demo.utils;

import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.util.Pair;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ne04jUtils {

    private static final Logger log = LoggerFactory.getLogger(Neo4jUtil.class);

    @Resource
    private static SessionFactory sessionFactory;

    @Resource
    private static Session session;

    /**
     * 返回节点集合，此方法不保留关系
     *
     * @param cypherSql cypherSql
     */
    public static List<Map<String, Object>> getGraphNode(String cypherSql) {

        List<Map<String, Object>> ents = new ArrayList<>();
//        String sql = "MATCH (n:`地图_y_Oy3G`)  RETURN distinct(n) limit 500";
        Result result = session.query(cypherSql, new HashMap<>());
        for (Map<String, Object> next : result) {
            ents.add(next);
        }
        return ents;
    }
}
