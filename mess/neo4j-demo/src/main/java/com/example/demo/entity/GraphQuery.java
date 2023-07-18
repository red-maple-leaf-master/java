package com.example.demo.entity;

import lombok.Data;

@Data
public class GraphQuery{	

	private int domainId;
	private Integer type;//0=手动创建，1=三元组导入，2=excel导入，3=er图构建
	private Integer commend;
	/**
	 * 图谱名称
	 */
	private String domain;
	/**
	 * 需要查询的节点
	 */
	private String nodeName;
	/**
	 *
	 */
	private String[] relation;
	/**
	 * 0 模糊查找  1 精确查找
	 */
	private int matchType;

    private int pageSize = 10;
    private int pageIndex = 1;
}
