package com.readexcel.readexcel.test;

import java.util.ArrayList;

public class NewTest {
    public static void main(String[] args) {
        fortest();
    }
    public static void fortest(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.forEach(e->{
            System.out.println(e);
        });
    }
}
