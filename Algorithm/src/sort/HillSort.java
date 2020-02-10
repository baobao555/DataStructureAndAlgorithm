package sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author baobao
 * @create 2019-10-07 17:49
 * @description 希尔排序
 *
 * 考虑直接插入排序存在的问题，假设arr={2,3,4,5,6,1}，这样将最后一个1插入到前面的有序表时，原有序表所有元素
 * 都要后移一位，效率较低，希尔排序就是为了解决这种问题
 * 希尔排序也是一种插入排序，是直接插入排序的改良版本：
 * 希尔排序维护一个增量gap，初始化为数组长度/2，将数组分成gap个组，对每组内部进行直接插入排序，
 * 排序完成后，继续缩小gap(gap = gap / 2)，然后重复上述过程，将数组分成gap个组并对每组内部进行直接插入排序，
 * 直到gap=1，这时数组整体已经比较有序(不会出现最小的数还出现在最后这种情况)，再进行一次直接插入排序对元素位置
 * 进行微调即可完成排序
 *
 */
public class HillSort {
    public static void main(String[] args) {
        int[] arr = {23,5,7,98,394,20,21};
        arr = Utils.createRandomArray(8000000);
        Instant start = Instant.now();
        HillSort(arr);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        //Utils.printArr(arr);
    }

    /**
     * 希尔排序
     * @param arr 待排序的数组
     */
    public static void HillSort(int[] arr){
        //定义gap(组数)，初始化为数组长度的一半
        int gap = arr.length / 2;
        //循环进行分组当组数>=1时
        while (gap >= 1){
            //将数组分成gap个组，循环对0~gap-1组进行组内排序
            for (int i = 0; i < gap; i++) {
                //对第i组进行组内直接插入排序
                //组内元素分别为arr[i],arr[i+gap],arr[i+2gap],arr[i+3gap].....
                //故arr[i]为初始有序表，剩下元素组成初始无序表。遍历无序表插入到有序表中
                for (int j = i + gap; j < arr.length; j = j + gap) {
                    //保存待插入元素
                    int insertVal = arr[j];
                    //插入的位置，初始化为-1
                    int insertIndex = -1;
                    //寻找待插入的位置
                    for (int k = j - gap; k >= 0; k = k - gap) {//从有序表的最后一个元素开始，逆序遍历，逐个与待插入元素比较大小
                        if (arr[k] < insertVal){//当碰到有序表中元素<待插入元素，即找到了待插入位置
                            insertIndex = k + gap;//待插入位置为当前有序表元素的后1个位置(步长是gap)
                            break;//跳出循环
                        }else {//如果有序表中的元素>=待插入元素
                            arr[k + gap] = arr[k];//将该元素后移1个位置(步长是gap)
                        }
                    }
                    if (insertIndex == -1){//如果循环结束后insertIndex仍为初始值，说明有序表中所有元素均>=待插入元素，那么待插入位置为有序表首元素i
                        insertIndex = i;
                    }
                    //将待插入元素插入到有序表的对应位置
                    arr[insertIndex] = insertVal;
                }
            }
            //压缩gap为之前的一半，继续分组进行组内排序
            gap = gap / 2;
        }
    }
}
