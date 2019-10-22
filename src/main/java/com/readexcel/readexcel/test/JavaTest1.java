package com.readexcel.readexcel.test;

import net.sf.json.JSONObject;

public class JavaTest1 {
    public static void main(String[] args) {
        //(1)方法引用与Lambda 表达式和传统方法比较
//        Hi[] his = new Hi[]{
//                new Hi() {
//                    @Override
//                    public String say(String msg) {
//                        return msg.toUpperCase();
//                    }
//                },//匿名内部类
//                msg1 -> msg1.substring(0,5),//lombda表达式,由箭头 -> 分隔开参数和函数体，箭头左边是参数，箭头右侧是从 Lambda 返回的表达式，即函数体。这实现了与定义类、匿名内部类相同的效果，但代码少得多
//                Unrelated ::twice //方法引用,在 :: 的左边是类或对象的名称，在 :: 的右边是方法的名称，但没有参数列表。
//        };
//        JavaTest1 javaTest1 = new JavaTest1("Hello tut");
//        javaTest1.show();
//        for (Hi hi :his){
//            javaTest1.change(hi);
//            javaTest1.show();
//        }

        //(2)JsonObject的optBoolean()返回的值
        //jsonTest();

        //(3)递归写Fibonacci写法
       /* for (int i=0;i<10;i++){
            System.out.println( showFib(i));
        }*/


    }

    /**
     * (1)方法引用与Lambda 表达式和传统方法比较
     */
    JavaTest1(String msg) {
        hi = new Hello();//传统new对象
        this.msg = msg;
    }

    public String msg;
    public Hi hi;

    public void show() {
        System.out.println(hi.say(msg));
        //System.out.println(msg);
    }

    public void change(Hi hi) {
        this.hi = hi;
    }

    /**
     *(2)JsonObject的optBoolean()返回的值
     */
    public static void jsonTest(){
        JSONObject jsonObject = new JSONObject();
        boolean fa = jsonObject.optBoolean("fa");
        System.out.println(fa);
    }

    /**
     *(3)Fibonacci写法
     */
    static InterCall fib;
    public static void fibonacci(){
        fib=  n->n==0? 0:n==1?1:fib.call(n-1)+fib.call(n-2);
    }
    public static int showFib(int n){
        fib=  m->m==0? 0:m==1?1:fib.call(m-1)+fib.call(m-2);
        return fib.call(n);
    }
}




interface Hi {
    public String say(String msg);
}

class Hello implements Hi {
    public int fig;
    @Override
    public String say(String msg) {
        return msg.toLowerCase() + "!";
    }
}

class Unrelated {
    static String twice(String msg/*,String msg1*/) {
        return msg + " " + msg;
    }
}

interface InterCall{
    int call(int arg);
}

