package com.example.demo.repository;

import com.example.demo.entity.Dept;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends Neo4jRepository<Dept,Long> {

}
