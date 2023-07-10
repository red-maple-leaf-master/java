package com.example.demo.service;


import com.example.demo.entity.Node;

import java.util.List;

public interface NodeService {
    public Node save(Node node);

    public List<Node> getAll();
}
