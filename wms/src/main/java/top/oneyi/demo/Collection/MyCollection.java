package top.oneyi.demo.Collection;

/**
 * 集合的顶层接口
 * @param <T>
 */
public interface MyCollection<T> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    boolean add(T t);

    boolean remove(T t);



}
