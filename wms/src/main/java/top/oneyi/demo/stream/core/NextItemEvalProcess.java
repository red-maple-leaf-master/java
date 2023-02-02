package top.oneyi.demo.stream.core;


import top.oneyi.demo.stream.api.EvalFunction;

/**
 * 下一个元素求值过程
 */
public class NextItemEvalProcess<T> {

    /**
     * 求值方法
     * */
    private EvalFunction<T> evalFunction;

    public NextItemEvalProcess(EvalFunction<T> evalFunction) {
        this.evalFunction = evalFunction;
    }

    MyStream<T> eval(){
        return evalFunction.apply();
    }
}