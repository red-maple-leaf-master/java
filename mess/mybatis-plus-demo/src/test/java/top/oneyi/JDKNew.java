package top.oneyi;

import org.junit.Test;

import java.util.Optional;

public class JDKNew {

    @Test
    public void test01(){
        String str = "123";

        Optional<String> optionalS = Optional.ofNullable(str);
        Optional<Integer> integer = optionalS.map(String::length);
        System.out.println("integer = " + integer.orElse(0));
    }

    @Test
    public void test02(){

    }
}
