package sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author baobao
 * @create 2019-10-07 23:07
 * @description 快速排序
 *
 * 思想：
 * 在待排序数组中选取一个基准数(通常是第一个数)，将其他数与这个基准比较，小的放在基准左边，大的放在基准右边，
 * 然后再对基准左边和右边用同样的基准法进行递归排序，最终得出结果
 *
 * 步骤：
 * (1)  我们从待排序的记录序列中选取一个记录(通常第一个)作为基准元素(称为key)key=arr[left]，然后设置两个变量，
 *      left指向数列的最左部，right指向数据的最右部
 * (2)  key首先与arr[right]进行比较，如果arr[right]<key，则arr[left]=arr[right]将这个比key小的数放到左边去，
 *      如果arr[right]>key则我们只需要将right--，right--之后，再拿arr[right]与key进行比较，直到arr[right]<key交换元素为止
 * (3) 如果右边存在arr[right]<key的情况，将arr[left]=arr[right]，接下来，将转向left端，拿arr[left ]与key进行比较，
 *     如果arr[left]>key,则将arr[right]=arr[left]，如果arr[left]<key，则只需要将left++,然后再进行arr[left]与key的比较
 * (4)  然后再移动right重复上述步骤
 * (5)  再对左子数列与右子数列进行同样的操作，最终得到一个有序的数列。
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {23,5,7,98,394,20};
        arr = Utils.createRandomArray(8000000);
        Instant start = Instant.now();
        quickSort(arr, 0, arr.length - 1);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        //Utils.printArr(arr);
    }

    /**
     * 快速排序
     * @param arr 待排序数组
     * @param left 数组的左指针
     * @param right 数组的右指针
     */
    public static void quickSort(int[] arr,int left,int right){
        if (left < right){//当左指针与右指针碰撞时，结束递归
            //分组，得到基准key的位置，保证分组后key左边的数都小于key，key右边的数都大于key
            int pos = partition(arr, left, right);
            //对key的左边进行递归快排
            quickSort(arr, left, pos - 1);
            //对key的右边进行递归快排
            quickSort(arr, pos + 1, right);
        }

    }

    /**
     * 选取一个基准key，将数组中比key小的都放在key的左边，比key大的都放在key的右边
     * @param arr 待排序数组
     * @param left 左指针
     * @param right 右指针
     * @return 分组后基准key的位置
     */
    private static int partition(int[] arr, int left, int right) {
        int key = arr[left];//选取第一个元素作为基准key
        //当left小于right时
        while (left < right){
            //先以right为起点，如果arr[right] >= key(注意一定要>=不能是>，否则会死循环)，right--前移，直到找到arr[right] <= key
            while (left < right && arr[right] >= key){
                right--;
            }
            //将找到的比key小的arr[right]保存到arr[left]
            arr[left] = arr[right];
            //再以left为起点，如果arr[left] <= key(注意一定要<=不能是<，否则会死循环)，left++后移，直到找到arr[left] >= key
            while (left < right && arr[left] <= key){
                left++;
            }
            //将找到的比key大的arr[left]保存到arr[right]
            arr[right] = arr[left];
        }
        //最终循环结束时，left与right碰撞，将key的位置找到
        arr[left] = key;
        //返回key在数组中的位置
        return left;
    }
}
