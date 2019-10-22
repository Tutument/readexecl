package com.readexcel.readexcel.test;

public class MethodReferences {
    static void hello(String name){
        System.out.println("hello "+name);
    }
    static class Description{
        String about;
        Description(String str){
            about = str;
        }

        void help(String msg){
            System.out.println(msg+" "+about);
        }
    }
    static class Helper{
        static void assist(String msg){
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Callable c;

        Describe d = new Describe();
        c= d::show;
        c.call("call()");

        c= MethodReferences::hello;
        c.call("girl");

        c=new Description("bob")::help;
        c.call("hi");

        //c =Describe::show;

        c=Helper::assist;
        c.call("help!");
    }
}
interface Callable { // [1]
    void call(String s);
}

class Describe {
    void show(String msg) { // [2]
        System.out.println(msg);
    }
}


