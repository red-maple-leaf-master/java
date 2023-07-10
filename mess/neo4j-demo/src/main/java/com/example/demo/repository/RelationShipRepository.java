package com.example.demo.repository;

import com.example.demo.entity.RelationShip;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationShipRepository extends Neo4jRepository<RelationShip, Long> {

}

