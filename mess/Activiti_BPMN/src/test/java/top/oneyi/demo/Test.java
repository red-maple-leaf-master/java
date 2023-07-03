package top.oneyi.demo;


import top.oneyi.pojo.Person;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setName("张三");
        person.setAge(12);
        person.setId(1L);
        list.add(person);
        Person person2 = new Person();
        person2.setName("李四");
        person2.setAge(13);
        person2.setId(2L);
        list.add(person2);
        Person person3 = new Person();
        person3.setName("王五");
        person3.setAge(14);
        person3.setId(3L);
        list.add(person3);
        Person person4 = new Person();
        person4.setName("赵六");
        person4.setAge(15);
        person4.setId(4L);
        list.add(person4);
        Person person5 = new Person();
        person5.setName("田七");
        person5.setAge(16);
        person5.setId(5L);
        list.add(person5);
        Person person6 = new Person();
        person6.setName("张三");
        person6.setAge(12);
        person6.setId(1L);
        list.add(person6);
        Person person7 = new Person();
        person7.setName("张三");
        person7.setAge(12);
        person7.setId(1L);
        list.add(person7);
        for (Person person1 : list) {
            System.out.println("person1 = " + person1);
        }
        System.out.println("================");
        list = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(
                                Person::getId))), ArrayList::new));
        for (Person person1 : list) {
            System.out.println("person1 = " + person1);
        }
    }
}
