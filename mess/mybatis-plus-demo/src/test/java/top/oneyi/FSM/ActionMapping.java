package top.oneyi.FSM;

import lombok.Getter;
import lombok.Setter;

/**
 * 状态机的映射
 */
@Setter
@Getter
public class ActionMapping {

    /**
     * 当前状态
     */
    private State currentState;

    /**
     * 次态
     */
    private State nextState;

    /**
     * 动作
     */
    private Action action;

    /**
     * 事件
     */
    private Event event;


    /**
     * 暴漏创建对象的方法
     * @param currentState 当前状态
     * @param event 事件
     * @param nextState 下一个状态
     * @param action 动作
     * @return
     */
    public static ActionMapping ofMap(State currentState, Event event, State nextState, Action action) {
        return new ActionMapping(currentState, event, nextState, action);
    }

    /**
     * 私有实例化方法
     * @param currentState
     * @param event
     * @param nextState
     * @param action
     */
    private ActionMapping(State currentState, Event event, State nextState, Action action) {
        this.currentState = currentState;
        this.nextState = nextState;
        this.action = action;
        this.event = event;
    }
}
