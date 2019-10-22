package com.readexcel.readexcel.testdemo;

public class Fu {

    void f(){
        System.out.println("Fu.f()");
    }
    Fu(){
        System.out.println("before f()");
        f();
        System.out.println("after f()");
    }
}
