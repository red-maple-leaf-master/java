package top.oneyi.demo.guice;

import com.google.inject.*;


import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class GuiceDemo {
    public static void main(String[] args) throws Exception {

        Injector injector = Guice.createInjector(new DemoModule());

        Greeter first = injector.getInstance(Greeter.class);
        Greeter second = injector.getInstance(Greeter.class);

        System.out.printf("first hashcode => %s\n", first.hashCode());
        first.sayHello();
        System.out.printf("second hashcode => %s\n", second.hashCode());
        second.sayHello();
    }

    @Retention(RUNTIME)
    public @interface Count {

    }

    @Retention(RUNTIME)
    public @interface Message {
        String value();
    }

    @Retention(RUNTIME)
    public @interface Name {
        String value();
    }

    @Singleton
    public static class Greeter {

        private final String message;

        private final Integer count;

        private final String name;

        @Inject
        public Greeter(@Message("message") String message,
                       @Count Integer count,
                       @Name("age") String name) {
            this.message = message;
            this.count = count;
            this.name = name;
        }

        public void sayHello() {
            for (int i = 1; i <= count; i++) {
                System.out.printf("%s,count => %d\n", message, i);
                System.out.printf("%s,count => %d\n", name, i);
            }
        }
    }

    public static class DemoModule extends AbstractModule {

        @Override
        public void configure() {

        }

        @Provides
        public static Integer count() {
            return 2;
        }

        @Provides
        @Message("message")
        public static String message() {
            return "vlts.cn";
        }

        @Provides
        @Name("age")
        public static String name() {
            return "lisi";
        }
    }

}
