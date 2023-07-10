package top.oneyi.demo.Collection;

import top.oneyi.demo.Collection.List.MyArrayMyList;
import top.oneyi.demo.Collection.List.MyList;

public class ListDemo {
    public static void main(String[] args) {
        MyList<String> myList = new MyArrayMyList<>();
        myList.add("我是第一个元素");
        myList.add("我是第二个元素");
        myList.add("我是第三个元素");
        myList.add("我是第四个元素");
        myList.remove(2);
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
    }
}
