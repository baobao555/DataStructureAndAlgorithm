package sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author baobao
 * @create 2019-10-06 22:17
 * @description 冒泡排序
 *
 *
 * 假设数组arr有n个数：
 * 第一轮比较：arr[0]和arr[1]比，如果arr[0]大，就交换；然后arr[1]和arr[2]比。。。直到最后arr[n-2]和arr[n-1]比完，
 * arr[n-1]已经成为整个数组最大的数，就像气泡一样逐渐浮上来
 * 接下来第二轮比较，由于arr[n-1]已经是最大的，故不参与比较，剩下n-1个数参与比较，最终arr[n-2]会成为第二大的数。
 * 最终经过n-1轮比较，排序完成
 *
 * 时间复杂度：n^2
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {23,5,7,98,394,20};
        arr = Utils.createRandomArray(80000);
        Instant start = Instant.now();
        bubbleSort(arr);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        //Utils.printArr(arr);
    }

    /**
     * 冒泡排序
     * @param arr 待排序的数组
     */
    public static void bubbleSort(int[] arr){
        //需要进行n-1轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            //优化：定义一个标志，表示在一轮比较中是否交换过
            boolean flag = false;
            //每轮需要进行n-i-1次比较将最大的数浮上来
            for (int j = 0; j < arr.length - i - 1; j++) {
                //如果当前数大于后面的数
                if (arr[j] > arr[j + 1]){
                    //交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    //有发生交换，就将标志位置为true
                    flag = true;
                }
            }
            //如果一轮比较结束后，没有发生任何位置交换，说明当前数组已经有序，直接跳出循环，结束排序
            if (!flag){
                break;
            }
        }
    }
}
