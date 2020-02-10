package sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author baobao
 * @create 2019-10-08 22:58
 * @description 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {23,5,7,98,394,20,21};
        arr = Utils.createRandomArray(8000000);
        int[] temp = new int[arr.length];
        Instant start = Instant.now();
        mergeSort(arr, 0, arr.length - 1,temp);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        //Utils.printArr(arr);
    }

    /**
     * 归并排序
     * @param arr 待排序数组
     * @param left 数组的左指针
     * @param right 数组的右指针
     * @param temp 存放归并排序中间结果的临时数组
     */
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        //递归结束条件：左指针与右指针碰撞
        if (left < right){
            //计算中间位置
            int mid = (left + right) / 2;
            //向左递归分解原数组
            mergeSort(arr, left, mid,temp);
            //向右递归分解原数组
            mergeSort(arr, mid + 1, right,temp);
            //分解完后进行归并排序
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     *
     * @param arr 待排序数组
     * @param left 左指针
     * @param mid  中间位置
     * @param right 右指针
     * @param temp 存放归并排序中间结果的临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int l = left;//归并时左边有序集合的指针
        int r = mid + 1;//归并时右边有序集合的指针
        int t = 0;//temp的索引变量
        //逐个比较左边有序集合与右边有序集合，将比较结果有序放入到temp中，直到l或r到达尽头
        while (l <= mid && r <= right){
            if (arr[l] < arr[r]){//如果左边元素小，则将它放入temp中，并且l向后移动
                temp[t++] = arr[l++];
            }else {//如果左边元素小，则将它放入temp中，并且r向后移动
                temp[t++] = arr[r++];
            }
        }
        //将左边有序集合的剩余元素拷贝至temp
        while (l <= mid){
            temp[t++] = arr[l++];
        }
        //将右边有序集合的剩余元素拷贝至temp
        while (r <= right){
            temp[t++] = arr[r++];
        }
        //重置t
        t = 0;
        //将归并后的排序结果从temp拷贝至原数组
        for (int i = left; i <= right; i++) {
            arr[i] = temp[t];
            t++;
        }
    }
}
