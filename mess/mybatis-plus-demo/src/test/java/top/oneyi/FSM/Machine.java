package top.oneyi.FSM;

import top.oneyi.FSM.action.ApprovePassAction;
import top.oneyi.FSM.action.ApproveRefusedAction;
import top.oneyi.FSM.action.RecheckPassAction;
import top.oneyi.FSM.action.RecheckRefusedAction;

import java.util.ArrayList;
import java.util.List;

import static top.oneyi.FSM.Event.*;
import static top.oneyi.FSM.State.*;

/**
 * 状态机
 */
public class Machine {

    /**
     * 状态转移表
     */
    private List<ActionMapping> mappings = new ArrayList<>();

    {
       // 待审核  ,  审批通过 ,  同意  ==> 对应的action 对应操作
        mappings.add(ActionMapping.ofMap(APPROVE, APPROVE_PASS, PASS, new ApprovePassAction()));

        // 待审核  ,  审批拒绝 ,  拒绝  ==> 对应的action 对应操作
        mappings.add(ActionMapping.ofMap(APPROVE, APPROVE_REFUSED, REFUSED, new ApproveRefusedAction()));

        // 拒绝  ,  复核通过 ,  同意  ==> 对应的action 对应操作
        mappings.add(ActionMapping.ofMap(REFUSED, RECHECK_PASS, PASS, new RecheckPassAction()));

        // 拒绝  ,  复核不通过 ,  拒绝  ==> 对应的action 对应操作
        mappings.add(ActionMapping.ofMap(REFUSED, RECHECK_REFUSED, REFUSED, new RecheckRefusedAction()));

    }

    /**
     * 核心方法
     * @param currentState 当前状态
     * @param event 事件 (通过与否)
     * @param context 审批人
     * @return
     */
    public boolean transform(State currentState, Event event, Context context) {
        ActionMapping actionMapping = getMapping(currentState, event);
        if (null == actionMapping) {
            throw new RuntimeException("未找到相应的映射");
        }
        // 获取对应操作 执行
        Action action = actionMapping.getAction();
        action.action(context);
        return true;
    }

    /**
     *  查找对应映射做对应的操作
     * @param currentState
     * @param event
     * @return
     */
    private ActionMapping getMapping(State currentState, Event event) {
        if (mappings.size() > 0) {
            for (ActionMapping n : mappings) {
                if (n.getCurrentState().equals(currentState) && n.getEvent().equals(event)) {
                    return n;
                }
            }
        }
        return null;
    }

}
