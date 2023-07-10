package top.oneyi.demo.builder;

/**
 * Java链式调用
 *
 * @author oneyi
 * @date 2022/12/23
 */

public class MyFactory {

    private final String name;

    private final Integer age;

    private MyFactory(Builer builer) {
        this.name = builer.name;
        this.age = builer.age;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }


    public static class Builer {
        private String name;

        private Integer age;

        public Builer name(String name) {
            this.name = name;
            return this;
        }

        public Builer age(Integer age) {
            this.age = age;
            return this;
        }

        //        非线程安全 做权限校验
        public MyFactory build() {
            MyFactory myFactory = new MyFactory(this);
            if (myFactory.age < 0 || myFactory.age > 255) {
                throw new IllegalStateException("age out of range " + myFactory.age);
            }
            return myFactory;
        }

    }

    public static void main(String[] args) {
        MyFactory sn = new MyFactory.Builer().name("颤三").age(23).build();
        System.out.println("sn = " + sn);
    }
    /**
     *  需要被建造者模式建造的构造方法是私有的,属性是final类型的,
     *  使用其内部类build进行链式传值建造对象
     * 对于必须传递的参数也可以用final进行修饰
     */
}
