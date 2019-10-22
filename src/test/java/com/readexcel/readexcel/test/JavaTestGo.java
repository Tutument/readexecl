package com.readexcel.readexcel.test;

import com.readexcel.readexcel.pojo.Dog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JavaTestGo {

    @Autowired
    Dog dog;
    @Test
    public void test(){
        String str = "";
        String substring = str.substring(1);
        System.out.println(substring);
    }

    //动态规划法，求最大子数组
    @Test
    public void sort() {
        int[] ints = new int[]{-1,2,3,-6,3,4};
        int max = 0;
        int sum = 0;
        for (int i=0;i<ints.length;i++){
            sum += ints[i];
            max = Math.max(sum,max);
            if(sum<0)
                sum = 0;
        }
        System.out.println(max);
    }

    //String的方法测试
    @Test
    public void testString(){
        String str = "abcde";
        boolean contains = str.contains("abc");
        System.out.println(contains);
    }
}
