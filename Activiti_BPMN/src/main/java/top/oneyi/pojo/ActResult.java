package top.oneyi.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ActResult {


    /**
     * 驳回业务key集合集合
     */
    private List<String> reject;
    /**
     * 待审批业务key集合集合
     */
    private List<String> approvalPending;
    /**
     * 审批通过 业务key集合集合
     */
    private List<String> approve;
    /**
     * 存储指定的状态对应的集合
     * s1 驳回    4
     * s2 待审批    1
     * s3 审批通过   3
     */
    public Map<String,List<String>> build(String s1,String s2,String s3){
        Map<String,List<String>> maps = new HashMap<>();
        maps.put(s1,getReject());
        maps.put(s2,getApprovalPending());
        maps.put(s3,getApprove());
        return maps;
    }


}