package top.oneyi;


import java.util.function.Supplier;

/**
 * 汽车父类
 */
public class Vehicle {
    private String name;

    private Integer age;

    private boolean flag;

    public void driver() {
        System.out.println("我在开" + this.name + "兜风");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

// 奔驰类
class BenChi extends Vehicle {
    public BenChi() {

    }

    public BenChi(String name) {
        this.setName(name);
    }

    public BenChi(String name, Integer age) {
        this.setName(name);
        this.setAge(age);
    }

    // 复写父类方法
    public void driver() {
        System.out.println("尊贵的奔驰车主,欢迎使用我们的" + super.getName() + "兜风");
    }

    public void isfix(Integer age) {
        if (age > 6) {
            System.out.println(this.getName() + "需要修理了");
        } else {
            System.out.println(this.getName() + "不需要修理");
        }
    }
}

// 宝马类
class BaoMa extends Vehicle {
    public BaoMa() {

    }

    public BaoMa(String name, Integer age) {
        this.setName(name);
        this.setAge(age);
    }

    public BaoMa(String name) {
        this.setName(name);
    }

    // 复写父类方法
    public void driver() {
        System.out.println("尊贵的宝马车主,欢迎使用我们的" + super.getName() + "兜风");
    }

    public void see() {
        System.out.println("观看" + super.getName());
    }

    public void isfix(Integer age) {
        if (age > 5) {
            System.out.println("该车需要修理了");
        }
    }
}

class demo {
    /**
     * 使用Supplier 作为参数
     *
     * @param car
     * @return
     */
    public static Vehicle diver(Supplier<? extends Vehicle> car) {
        return car.get();
    }

    /**
     * 使用父类作为参数
     *
     * @param car
     * @return
     */
    public static void diver02(Vehicle car) {
        car.driver();

    }

    /**
     * 中间层需要 新加车辆是否需要修理方法
     *
     * @param car
     */
    public static void diver03(Vehicle car) {
        BenChi car1 = (BenChi) car;
        car1.isfix(car1.getAge());

    }
}

class test {
    public static void main(String[] args) {
        demo.diver(new Supplier<Vehicle>() {
            @Override
            public Vehicle get() {
                return new BenChi("奔驰600");
            }
        }).driver();
        demo.diver(() -> {
            return new BenChi("奔驰600");
        }).driver();

        demo.diver02(new BenChi("奔驰300"));
        // 使用Supplier接口可以使用lambda表达式,让代码看起来更加简洁
        System.out.println("==============================");
        demo.diver(() -> {
            // 使用Supplier接口可以重写get方法来对Vehicle对象进行属性变动
            BenChi benChi = new BenChi("奔驰600");
            if (benChi.getName().equals("奔驰600")) {
                benChi.setName("奔驰300");
            }
            return benChi;
        }).driver();
        System.out.println("==============================");
        // 看下面一个例子
        // 将设我们的车现在加了一个新的属性age,

        demo.diver(() -> {
            // 使用Supplier接口可以重写get方法来对Vehicle对象进行属性变动
            BenChi benChi = new BenChi("奔驰600");
            benChi.isfix(7);
            return benChi;
        });
        // 这时候发现,BenChi,自己特有的方法父类不能实现只能进行强制转换,可以在之前的方法进行修改 或者直接新写一个方法
        demo.diver03(new BenChi("奔驰300", 7));
        // 弊端出现了,如果Vehicle新加属性,并且子类对于该属性进行方法扩展,使用父类来当作参数的类的方法就需要重写,或者新加方法, 这样相当源代码就需要更改,风险很大,
        // 使用 Supplier 接口则是自己使用lambda表达式自己改变对应的方法,好处是中间层 demo不需要做成改变,把这个过程抽象成三层,第一层为 父类和子类  第二层是供应商类 第三层为客户类调用方
        // 当第一层做出改变,使用接口则第二层供应商类无需做出改变,第三层自己根据第一层的改变 自己改变自己的调用方式 或许这就是 Supplier 叫供给型接口的原因

    }


}