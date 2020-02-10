package sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author baobao
 * @create 2019-10-06 23:27
 * @description 选择排序
 *
 * 假设数组arr有n个数：
 * 第一轮从arr[0]到arr[n-1]中找出最小的数，并与arr[0]交换，这样arr[0]就是n个数中最小的数
 * 第二轮从arr[1]到arr[n-1]中找出最小的数，并与arr[1]交换，这样arr[1]就是n个数中第二小的数
 * 经过n-1轮，排序完成
 *
 * 时间复杂度：n^2
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {23,5,7,98,394,20};
        arr = Utils.createRandomArray(80000);
        Instant start = Instant.now();
        selectSort(arr);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        //Utils.printArr(arr);
    }


    /**
     * 选择排序
     * @param arr 待排序的数组
     */
    public static void selectSort(int[] arr){
        //需要n-1轮
        for (int i = 0; i < arr.length - 1; i++) {
            //minIndex保存每轮排序中最小元素的索引
            int minIndex = i;
            //从当前元素开始，与后面所有数进行比较，找出最小的
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]){
                    //发现当前的数小于之前记录的最小数，立即更新minIndex为当前数的索引
                    minIndex = j;
                }
            }
            //将本轮找到的最小数与arr[i]交换位置
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}
