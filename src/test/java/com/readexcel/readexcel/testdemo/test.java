package com.readexcel.readexcel.testdemo;

public class test {
    public static void main(String[] args) {
        //测试构造函数中动态绑定的问题
        //new Zi(5);
        String property = System.getProperty("user.dir");
        String[] split = property.split("\\\\");
        System.out.println(split.length);
        System.out.println(property);

    }
}
