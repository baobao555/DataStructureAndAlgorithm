package sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author baobao
 * @create 2019-10-07 0:06
 * @description 插入排序
 *
 *
 * 把n个待排序元素看做1个有序表和1个无序表，开始时有序表只有1个元素，无序表包含剩下n-1个元素。
 * 排序时每次从无序表中取出1个元素，插入到有序表中合适的位置，成为新的有序表。直到无序表为空，排序完成
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {23,5,7,98,394,20};
        arr = Utils.createRandomArray(80000);
        Instant start = Instant.now();
        insertSort(arr);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        //Utils.printArr(arr);
    }

    /**
     * 直接插入排序
     * @param arr 待排序的数组
     */
    public static void insertSort(int[] arr){
        //初始时，将第1个元素arr[0]作为有序表，剩下的元素作为无序表；遍历无序表中的元素
        for (int i = 1; i < arr.length; i++) {
            //得到待插入有序表的元素
            int insertVal = arr[i];
            //插入的位置，初始化为-1
            int insertIndex = -1;
            //寻找待插入的位置
            for (int j = i - 1; j >= 0; j--) {//从有序表的最后一个元素开始，逐个与待插入元素比较大小
                if (arr[j] < insertVal){//当碰到有序表中元素<待插入元素，即找到了待插入位置
                    insertIndex = j + 1;//待插入位置为当前有序表元素的后1个位置
                    break;//跳出循环
                }else { //如果有序表中的元素>=待插入元素
                    arr[j + 1] = arr[j];//将该元素后移一位
                }
            }
            if (insertIndex == -1){ //如果循环结束后insertIndex仍为初始值，说明有序表中所有元素均>=待插入元素，那么待插入位置为0
                insertIndex = 0;
            }
            //将待插入元素插入到有序表的对应位置
            arr[insertIndex] = insertVal;
        }
    }
}
