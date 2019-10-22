package com.readexcel.readexcel.testdemo;

public class Zi extends Fu {
    int count = 1;
    void f(){
        System.out.println("Zi.f()"+count);
    }
    Zi(int i){
        count=i;
        System.out.println("Zi.f():controller"+count);
    }
}
