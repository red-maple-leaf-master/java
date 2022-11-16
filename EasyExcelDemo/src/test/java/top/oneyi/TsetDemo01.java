package top.oneyi;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TsetDemo01 {

    @Test
    public void test01(){
        List<String> list = new ArrayList<>();
        list.add("张老三");
        list.add("张小三");
        list.add("李四");
        list.add("赵五");
        list.add("张六");

        list.stream().forEach(s -> {
          if(!s.isEmpty()){
            if(s.contains("张")){
                System.out.println(s);
              }



          }
       });



    }

}
