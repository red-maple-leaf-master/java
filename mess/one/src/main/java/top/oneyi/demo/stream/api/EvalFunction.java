package top.oneyi.demo.stream.api;

import top.oneyi.demo.stream.core.MyStream;

@FunctionalInterface
public interface EvalFunction<T> {

    /**
     * stream流的强制求值方法
     * @return 求值返回一个新的stream
     * */
    MyStream<T> apply();
}