package com.example.demo.service;

import com.example.demo.entity.Node;
import com.example.demo.repository.NodeRepository;
import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NodeServiceImpl implements NodeService {

    @Resource
    private NodeRepository nodeRepository;

    @Override
    public Node save(Node node) {
        return nodeRepository.save(node);
    }

    @Override
    public List<Node> getAll() {
        List<Node> nodes = nodeRepository.selectAll();
        nodes.forEach(System.out::println);
        return nodes;
    }
}
