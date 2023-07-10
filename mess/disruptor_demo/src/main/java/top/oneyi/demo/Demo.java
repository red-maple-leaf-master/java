package top.oneyi.demo;


public class Demo {
    public static void main(String[] args) {
        new A().msg();
    }
}

class A {
    private String name;


    public void msg() {
        B b = new B();
        System.out.println(name);
    }

    private class B {

        public B() {
            name = "李四";
        }

    }
}
