package com.readexcel.readexcel.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SortTest {
    //测试if else 是否同一个作用域 yes
    @Test
    public void setkey(){
        int i;
        if((i=1)==0){

        }else {
            System.out.println(i);//i=1
        }
    }

}
