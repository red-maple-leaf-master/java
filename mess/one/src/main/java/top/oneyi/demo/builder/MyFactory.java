package top.oneyi.demo.builder;

/**
 * Java链式调用
 * 需要被建造者模式建造的构造方法是私有的,属性是final类型的,
 * 使用其内部类Builder进行链式传值建造对象
 * 对于必须传递的参数也可以用final进行修饰
 */
public class MyFactory {

    private final String name;
    private final Integer age;

    private MyFactory(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public static class Builder {
        private  String name;
        private  Integer age;

        private Builder() {
            this.name = null;
            this.age = null;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        private MyFactory build() {
            if (age < 0 || age > 255) {
                throw new IllegalStateException("age out of range " + age);
            }
            return new MyFactory(this);
        }
    }

    public static void main(String[] args) {
        MyFactory sn = new Builder().name("颤三").age(23).build();
        System.out.printf("sn = %s %d%n", sn.getName(), sn.getAge());
    }

}
