package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KGGraphMapper {

    public void createDomain(String label);
}
