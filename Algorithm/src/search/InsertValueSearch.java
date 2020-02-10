package search;



/**
 * @author baobao
 * @create 2019-10-11 23:32
 * @description 插值查找
 *
 * 思路：
 * 插值查找是二分查找的延伸，二分查找mid = (left + right) / 2，直接采用中间值，这样有个缺陷，就是当需要
 * 查找的数很小或很大的时候，会非常接近数组起点或终点，这时查找次数会增多，效率较低。
 * 插值查找改变了计算mid的方法，mid = left + (value - arr[left]) / (arr[right] - arr[left]) * (right - left)，
 * 相当于算mid的时候，考虑了value在arr中的线性比例位置。
 * 在数据量大且增长比较均匀的情况下，插值查找效率较高。
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1,3,5,666,888,888,1000,1005};
        //int[] arr = {1,1,1,1,1,1,1};
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 1005));
    }

    /**
     * 插值查找
     * @param arr 待查找的数组
     * @param left 查找范围的起点索引
     * @param right 查找范围的重点索引
     * @param value 要查找的值
     * @return 要查找值在数组中的位置索引，-1代表没找到
     */
    public static int insertValueSearch(int[] arr,int left,int right,int value){
        //递归结束条件：1、arr[left] != arr[right]，否则会出现除0异常
        //2、value >= arr[left] && value <= arr[right]，当要查找的数超出范围，说明不存在，无需再查找
        if (arr[left] != arr[right] && value >= arr[left] && value <= arr[right]){
            //插值法计算中点
            int mid = left + (value - arr[left]) / (arr[right] - arr[left]) * (right - left);

            //找到了，直接返回
            if (value == arr[mid]){
                return mid;
            }
            //value大于arr[mid]，向右递归查找
            if (value > arr[mid]){
                return insertValueSearch(arr, mid + 1, right, value);
            }else {//value小于等于arr[mid]，向左递归查找
                return insertValueSearch(arr, left, mid - 1, value);
            }
        }
        //如果数组中从left到right的值都相等，说明出现多个重复值，这时如果待查找的数和这些值相等，返回最左边的索引
        if (arr[left] == value){
            return left;
        }
        //没找到值，返回-1
        return -1;
    }
}
