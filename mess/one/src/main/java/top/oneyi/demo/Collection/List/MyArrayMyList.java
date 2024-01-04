package top.oneyi.demo.Collection.List;

import java.util.Arrays;

import static java.lang.Integer.max;

public class MyArrayMyList<T> implements MyList<T> {
    // 集合的默认长度
    private static final int DEFAULT_LEN = 10;
    // 集合的长度
    private int len = 0;
    // 集合的索引
    private int index;
    // 有参 创建指定大小数组
    private static Object[] elementData = {};
    // 默认无参创建数组 第一次添加数据的时候本体数据需要跟这个长度比较 来进行设置长度
    // 如果 element的长度和这个相同就是第一次创建,直接可以赋值为 DEFAULT_LEN (10)
    private static final Object[] elementDataEmpty = {};

    public MyArrayMyList(int initArraylen) {
        if (initArraylen > 0) {
            elementData = new Object[initArraylen];
        } else if (initArraylen == 0) {
            elementData = new Object[DEFAULT_LEN];
        } else {
            throw new IllegalArgumentException("集合长度错误 " +
                    initArraylen);
        }
    }

    public MyArrayMyList() {
        elementData = elementDataEmpty;
    }


    @Override
    public int size() {
        return this.len;
    }

    @Override
    public boolean isEmpty() {
        return this.len == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < len; i++) {
            if (o.equals(elementData[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(T t) {
        // 添加扩容 集合的方法
        ensureCapacityInternal(len + 1);
        elementData[len++] = t;
        return true;
    }

    private void ensureCapacityInternal(int minCapacity) {
        int i = calculateCapacity(elementData, minCapacity);
        ensureExplicitCapacity(i);
    }

    // 这里判断长度是否超过现在数组的长度
    private void ensureExplicitCapacity(int calculateCapacity) {
        int oldLen = elementData.length;
        int newLen = oldLen + (oldLen >> 1);
        if (newLen - calculateCapacity < 0) {
            // 新的长度小于添加完数据后的长度
            newLen = calculateCapacity;
        }
        elementData = Arrays.copyOf(elementData, newLen);

    }

    private int calculateCapacity(Object[] elementData, int minCapacity) {
        // 如果发现数据为空也就是第一次添加数据 将默认长度10与传进来的比较 谁大 返回谁
        if (elementData == elementDataEmpty) {
            return max(DEFAULT_LEN, minCapacity);
        }
        return minCapacity;
    }

    @Override
    public boolean remove(T t) {
        if (null == t) {
            for (int i = 0; i < len; i++) {
                if (elementData[i] == t) {
                    fastMove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < len; i++) {
                if (elementData[i] == t) {
                    fastMove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object remove(int i) {
        checkIndex(i);
        Object elementDatum = elementData[i];
        int moveNum = len - 1 - i;
        if (moveNum > 0)
            System.arraycopy(elementData, i + 1, elementData, i, moveNum);
        elementData[--len] = null;
        return elementDatum;
    }

    @Override
    public Object set(int i, T t) {
        checkIndex(i);
        Object elementDatum = elementData[i];
        elementData[i] = t;
        return elementDatum;
    }

    @Override
    public Object get(int i) {
        checkIndex(i);
        return elementData[i];
    }

    // 检查参数是否超过数组长度
    private void checkIndex(int i) {
        if (len < i)
            throw new IndexOutOfBoundsException("数组越界了 年轻人");
    }

    // 移动数组数据
    private void fastMove(int i) {
        // 需要移动的数组的长度
        int moveNum = len - 1 - i;
        if (moveNum > 0) {
            // 调用复制方法,将删除后面的数据往前挪移一位
            System.arraycopy(elementData, i + 1, elementData, i, moveNum);
        }
        elementData[--len] = null;
    }
}
