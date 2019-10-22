package com.readexcel.readexcel.test;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 1, 4, 7, 8, 3, 5, 2, 6, 8, 9, 1};
        quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /**
     * 递归排序
     *
     * @param array 待排序列
     * @param left  左边起始位置
     * @param right 右边结束位置
     */
    private static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            //根据基准点，找到分隔左右子序列的位置索引
            int position = position(array, left, right);
            //分别进行左右的递归
            quickSort(array, left, position - 1);
            quickSort(array, position + 1, right);
        }
    }

    /**
     * 找到中间
     *
     * @param array 待排序列
     * @param left  左边起始位置
     * @param right 右边结束位置
     * @return
     */
    private static int position(int[] array, int left, int right) {
        //找到基准点, 这里使用的是序列的第一个元素
        int base = array[left];
        while (left < right) {
            while (right > left && array[right] >= base) {
                right--;
            }
            //交互位置
            array[left] = array[right];
            while (left < right && array[left] <= base) {
                left++;
            }
            //交互位置
            array[right] = array[left];
        }
        //此时 left 与 right 是相等的
        array[left] = base;
        return left;
    }
}
