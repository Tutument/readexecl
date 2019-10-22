package com.readexcel.readexcel.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Demo1 {

    public static void main(String args[]) {
//        ***************************桶排序***************************
//        Random random = new Random();
//        int a[] = new int[1001];
//        int b[] = new int[6];
//
//        System.out.print("请输入要排序的数的个数：");
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        for (int j = 0; j < n; j++){
//            int temp = random.nextInt(1000) + 1;
//            for (int i = 0; i < 1001; i++){
//                    if (temp == i){
//                        a[i]++;
//                    }
//            }
//        }
//        for (int i = 1000; i >= 0; i--){
//            for (int j = 0; j < a[i]; j++){
//                System.out.print( i +" ");
//            }
//        }

//        ***************************冒泡排序***************************
//        class Student {
//
//            private String name;
//
//            private Integer score;
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public Integer getScore() {
//                return score;
//            }
//
//            public void setScore(Integer score) {
//                this.score = score;
//            }
//        }
//
//        List<Student> studentList = new ArrayList<>(10);
//
//        Random random = new Random();
//
//        Scanner scanner = new Scanner(System.in);
//
//        for (int i = 0; i < 5; i++){
//            Student student = new Student();
//            System.out.print("输入姓名：");
//            student.setName(scanner.next());
//            student.setScore(random.nextInt(10) + 1);
//            studentList.add(student);
//        }
//        for (int i = 0; i < (5-1); i++){
//            for (int j = 0; j < (5-1)-i; j++){
//                if (studentList.get(j).getScore() < studentList.get(j+1).getScore()){
//                    int b = studentList.get(j).getScore();
//                    studentList.get(j).setScore(studentList.get(j+1).getScore());
//                    studentList.get(j+1).setScore(b);
//                }
//            }
//        }
//        for (int i = 0; i < 5; i++){
//            System.out.println(studentList.get(i).getName() + "的分数：" + studentList.get(i).getScore());
//        }

//        ***************************快速排序（递增）***************************

//        int[] a = new int[10];
//        for (int i = 0; i < a.length; i++){
//            Scanner scanner = new Scanner(System.in);
//            int temp = scanner.nextInt();
//            a[i] = temp;
//        }
//        randomizedQuickSort(a, 0, 9);
//        for (int e:a
//             ) {
//            System.out.print(e + " ");
//        }
//    }
//
//    //快速排序的递归
//    static public void randomizedQuickSort(int[] A, int p, int r){
//        if (p < r){
//            int q = randomizedPartition(A, p, r);
//            randomizedQuickSort(A, p, q-1);
//            randomizedQuickSort(A,q+1,r);
//        }
//    }
//
//    //数组元素划分方法
//    static public int partition(int[] A, int p, int r){
//        int i = p-1;
//        int rn = A[r];
//        int j;
//
//        //判断数组的所有元素是否都相等
//        for (int k = p; k < r; k++){
//            int count = 0;
//            if (A[k]== A[k+1]){
//                count++;
//            }else{
//                break;
//            }
//            if (count == r-1){
//                return (p+r)/2;
//            }
//        }
//
//        //数组的所有元素不都相等时
//        for (j = p; j < r; j++){
//            if (A[j] <= rn){
//                int temp = A[j];
//                i = i+1;
//                A[j] = A[i];
//                A[i] = temp;
//            }else{
//                continue;
//            }
//        }
//        if (j == r){
//            int temp2 = A[r];
//            A[r] = A[i+1];
//            A[i+1] = temp2;
//        }
//        return i+1;
//    }
//
//    //随机选取主元的划分
//    static public int randomizedPartition(int[] A, int p, int r){
//        Random random = new Random();
//        int i = r;
//        if (p == 0){
//            i = random.nextInt(r+1);
//        }
//        if (p != 0){
//            i = random.nextInt(r-p+1) + p;
//        }
//        int temp = A[r];
//        A[r] = A[i];
//        A[i] = temp;
//        return partition(A, p ,r);
//    }

//        ***************************小哼买书***************************
//        方法一：桶排序
//        Scanner scanner = new Scanner(System.in);
//        int[] a = new int[1001];
//        int count = 0;
//        System.out.print("在此输入买书的人数：");
//        int n = scanner.nextInt();
//
//        System.out.print("在此输入各书编号：");
//        for (int j = 0; j < n; j++){
//            int num = scanner.nextInt();
//            a[num]++;
//        }
//
//        for (int i = 0; i <= 1000; i++){
//            if (a[i] != 0){
//                count++;
//            }
//        }
//        System.out.println("买的书的个数：" + count);
//
//        System.out.print("书的编号：");
//        for (int i = 0;i <= 1000; i++){
//            if (a[i] != 0){
//                System.out.print(i + " ");
//            }
//        }

//        方法二：冒泡排序
//        int i,j;
//        int count = 0;
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("在此输入买书的人数：");
//        int n = scanner.nextInt();
//        int[] a = new int[n];
//
//        System.out.print("在此输入各书编号：");
//        for (i = 0; i < n; i++){
//            int num = scanner.nextInt();
//            a[i] = num;
//        }
//        for (j = 0; j < n-1; j++){
//            for (int k = 0; k < n-1-j; k++){
//                if (a[k] >= a[k+1]){
//                    int temp = a[k];
//                    a[k] = a[k+1];
//                    a[k+1] = temp;
//                }
//            }
//        }
//
//        for (i = 0; i < n-1; i++){
//            if (a[i] != a[i+1]){
//                count++;
//            }
//        }
//        System.out.println("买的书的个数：" + (count + 1));
//
//        System.out.print("书的编号：");
//        for (i = 0; i < n-1; i++){
//            if (a[i] != a[i+1]){
//                System.out.print(a[i] + " ");
//            }
//        }
//        System.out.println(a[n-1]);

//        方法三：快速排序
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入买书的人数：");
        int  num = scanner.nextInt();
        int count1 = 0;
        int[] a = new int[num];
        System.out.println("输入书的编号：");
        for (int i = 0; i < num; i++){
            a[i] = scanner.nextInt();
        }
        quickSort(a, 0, num-1);
        for (int k = 0; k < num-1; k++){
            if (a[k] != a[k+1]){
                count1++;
            }
        }
        System.out.println("能借到的书的个数：" + (count1+1));
        System.out.print("买到的书的编号：");
        for (int k = 0; k < num-1; k++){
            if (a[k] != a[k+1]){
                System.out.print(a[k] + " ");
            }
        }
        System.out.println(a[num-1]);

    }

    static public void quickSort(int A[], int p, int r){
        if (p < r){
            int q = partition(A, p, r);
            quickSort(A, p, q-1);
            quickSort(A, q+1, r);
        }

    }

    static public int partition(int A[], int p, int r){

        //判断数组的元素是否全都相等
        int count = 0;
        for (int i = p; i < r; i++){
            if (A[i] == A[i+1]){
                count++;
            }else{
                break;
            }
            if (count == r-1){
                return (p + r)/2;
            }
        }

        //当数组的元素不全都相等的时候
        Random random = new Random();
        int n = r;
            if (p == 0){
                n = random.nextInt(r+1);
            }else if (p != 0){
                n = random.nextInt(r-p+1) + p;
            }
            int temp = A[r];
            A[r] = A[n];
            A[n] = temp;

            int i = p - 1;
            int j;
            for (j = p; j < r; j++){
                if (A[j] <= A[r]){
                    int temp2 = A[j];
                    i = i+1;
                    A[j] = A[i];
                    A[i] = temp2;
                }
            }
            int temp3 = A[r];
            A[r] = A[i+1];
            A[i+1]= temp3;
            return i+1;
        }
    }

