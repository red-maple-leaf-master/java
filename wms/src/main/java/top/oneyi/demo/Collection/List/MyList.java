package top.oneyi.demo.Collection.List;

import top.oneyi.demo.Collection.MyCollection;

public interface MyList<T> extends MyCollection<T> {
    @Override
    int size();

    @Override
    boolean isEmpty();

    @Override
    boolean contains(Object o);

    @Override
    boolean add(T t);

    @Override
    boolean remove(T t);

    Object remove(int i);


    Object get(int i);

    Object set(int i,T t);
}
