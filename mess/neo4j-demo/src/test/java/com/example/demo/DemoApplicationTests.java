package com.example.demo;

import com.example.demo.entity.Node;
import com.example.demo.repository.NodeRepository;
import com.example.demo.service.NodeService;
import com.example.demo.utils.Ne04jUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private NodeService nodeService;
    @Autowired
    private NodeRepository nodeRepository;

    /**
     * 添加节点
     */
    @Test
    void contextLoads() {
        Node  node = new Node();
        node.setName("钢铁侠");
        node.setTitle("凡人之躯堪比神明");
        nodeService.save(node);
    }

    @Test
    void selectAll(){
//        List<Node> all = nodeService.getAll();
        Ne04jUtils.getGraphNode("MATCH (n:`地图_y_Oy3G`)  RETURN distinct(n) limit 500");

    }

}
