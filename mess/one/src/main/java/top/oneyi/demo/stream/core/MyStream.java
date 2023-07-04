package top.oneyi.demo.stream.core;

import top.oneyi.demo.stream.api.*;

import java.util.stream.Collector;

public class MyStream<T> implements Stream<T> {
    /**
     * 流的头部
     */
    private T head;

    /**
     * 流的下一项求值函数
     */
    private NextItemEvalProcess  nextItemEvalProcess;

    /**
     * 是否是流的结尾
     */
    private boolean isEnd;

    public static class Builder<T>{
        private MyStream<T> target;

        public Builder(){
            this.target=new MyStream<>();
        }

        public Builder<T> head(T head){
            target.head=head;
            return this;
        }

        public Builder<T> isEnd(boolean isEnd){
            target.isEnd=isEnd;
            return this;
        }

        public Builder<T> nextItemEvalProcess(NextItemEvalProcess nextItemEvalProcess){
            target.nextItemEvalProcess=nextItemEvalProcess;
            return this;
        }

        public MyStream<T> build(){
            return target;
        }
    }

    /**
     * 当前流强制求值
     * @return
     */
    private MyStream eval(){
        return this.nextItemEvalProcess.eval();
    }

    /**
     * 当前流为空
     * @return
     */
    public boolean isEmptyStream(){
        return this.isEnd;
    }



    /**
     * 映射 lazy 惰性求值
     *
     * @param mapper
     * @return 一个新的流
     */
    @Override
    public <R> MyStream<R> map(Function<R, T> mapper) {
        NextItemEvalProcess lastNextItemEvalProcess = this.nextItemEvalProcess;
        this.nextItemEvalProcess = new NextItemEvalProcess(
                ()->{
                    MyStream myStream = lastNextItemEvalProcess.eval();
                    return map(mapper, myStream);
                }
        );

        // 求值链条 加入一个新的process map
        return new MyStream.Builder<R>()
                .nextItemEvalProcess(this.nextItemEvalProcess)
                .build();
    }

    /**
     * 递归函数 配合API.map
     * */
    private static <R,T> MyStream<R> map(Function<R, T> mapper, MyStream<T> myStream){
        if(myStream.isEmptyStream()){
            return Stream.makeEmptyStream();
        }

        R head = mapper.apply(myStream.head);

        return new MyStream.Builder<R>()
                .head(head)
                .nextItemEvalProcess(new NextItemEvalProcess(()->map(mapper, myStream.eval())))
                .build();
    }

    /**
     * 扁平化 映射 lazy 惰性求值
     *
     * @param mapper
     * @return
     */
    @Override
    public <R> MyStream<R> flatmap(Function<? extends MyStream<R>, T> mapper) {
        return null;
    }

    /**
     * 过滤lazy 惰性求值
     *
     * @param predicate 谓词判断
     * @return
     */
    @Override
    public MyStream<T> filter(Predicate<T> predicate) {
        return null;
    }

    /**
     * 截断 lazy 惰性求值
     *
     * @param n
     * @return
     */
    @Override
    public MyStream<T> limit(int n) {
        return null;
    }

    /**
     * 去重操作 lazy 惰性求值
     *
     * @return
     */
    @Override
    public MyStream<T> distinct() {
        return null;
    }

    /**
     * 窥视 lazy 惰性求值
     *
     * @param consumer
     * @return 同一个流，peek不改变流的任何行为
     */
    @Override
    public MyStream<T> peek(ForEach<T> consumer) {
        return null;
    }

    /**
     * 遍历 eval 强制求值
     *
     * @param consumer 遍历逻辑
     */
    @Override
    public void forEach(ForEach<T> consumer) {

    }

    /**
     * 浓缩 eval 强制求值
     *
     * @param initVal     浓缩时的初始值
     * @param accumulator 浓缩时的 累加逻辑
     * @return 浓缩之后的结果
     */
    @Override
    public <R> R reduce(R initVal, BiFunction<R, R, T> accumulator) {
        return null;
    }

    /**
     * 收集 eval 强制求值
     *
     * @param collector 传入所需的函数组合子，生成高阶函数
     * @return 收集之后的结果
     */
    @Override
    public <R, A> R collect(Collector<T, A, R> collector) {
        return null;
    }

    /**
     * 最大值 eval 强制求值
     *
     * @param comparator 大小比较逻辑
     * @return 流中的最大值
     */
    @Override
    public T max(Comparator<T> comparator) {
        return null;
    }

    /**
     * 最小值 eval 强制求值
     *
     * @param comparator 大小比较逻辑
     * @return 流中的最小值
     */
    @Override
    public T min(Comparator<T> comparator) {
        return null;
    }

    /**
     * 计数 eval 强制求值
     *
     * @return 当前流的个数
     */
    @Override
    public int count() {
        return 0;
    }

    /**
     * 流中是否存在满足predicate的项
     *
     * @param predicate
     * @return true 存在 匹配项
     * false 不存在 匹配项
     */
    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return false;
    }

    /**
     * 流中的元素是否全部满足predicate
     *
     * @param predicate
     * @return true 全部满足
     * false 不全部满足
     */
    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return false;
    }
}
