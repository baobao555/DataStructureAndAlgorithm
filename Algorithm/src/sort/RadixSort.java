package sort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * @author baobao
 * @create 2019-10-09 22:55
 * @description 基数排序
 *
 *
 * 思路：先按照个位数排序，再按照十位数排序，再按照百位数排序。。。直到最高位，最后得出排序结果
 *
 * 步骤：
 * 1、定义10个桶(一位数组)，分别代表数字0~9
 * 2、对待排序数组中所有元素取个位数，如果是0，放入0号桶；1放入1号桶；2放入2号桶。。。依次类推
 * 3、从0~9号桶中依次取出元素拷贝至原数组，这样就按照个位数排序完成
 * 4、再重复上述过程对十位、百位。。。进行排序，直到最高位，最终即可得到排序结果
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {23,5,7,98,394,20,21};
        arr = Utils.createRandomArray(8000000);
        Instant start = Instant.now();
        radixSort(arr);
        //Arrays.sort(arr);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        //Utils.printArr(arr);
    }

    /**
     * 基数排序
     * @param arr 待排序数组
     */
    public static void radixSort(int[] arr){
        //找出数组中最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //求最大的数的位数
        int maxLength = (max + "").length();
        //创建10个桶(一维数组)，每个桶的长度为待排序数组的长度
        int[][] buckets = new int[10][arr.length];
        //创建含有10个元素的一维数组，保存每个桶存放了多少个有效数据
        int[] bucketElementNum = new int[10];
        //开始基数排序，依次排序个位、十位、百位。。。
        for (int i = 0; i < maxLength; i++) { //需要排序的次数为数组中最大元素的位数
            for (int j = 0; j < arr.length; j++) {//遍历待排序数组
                int value = arr[j] / (int)Math.pow(10, i) % 10;//求出每个元素对应位上的数值
                buckets[value][bucketElementNum[value]++] = arr[j];//将数组元素放到相对应的桶中，同时对应桶的有效元素计数器++
            }
            //按照顺序从每个桶中依次取出数据拷贝到原数组arr
            int t = 0;
            for (int j = 0; j < buckets.length; j++) {//遍历每个桶
                for (int k = 0; k < bucketElementNum[j]; k++) {//每个桶取值的次数为有效元素的个数
                    arr[t++] = buckets[j][k];//取出有效元素赋值给arr
                }
            }

            //每排完一次，将桶的有效数据计数器清0
            for (int j = 0; j < bucketElementNum.length; j++) {
                bucketElementNum[j] = 0;
            }
        }
    }
}
