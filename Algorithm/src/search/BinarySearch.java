package search;

/**
 * @author baobao
 * @create 2019-10-10 23:50
 * @description 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,3,5,666,888,888,1000,1005};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 888));
    }

    /**
     * 二分查找
     * @param arr 待查找的数组(必须已经排好序)
     * @param left 查找范围的起始索引(左指针)
     * @param right 查找范围的结束索引(右指针)
     * @param value 要查找的数值
     * @return 要查找值在数组中的位置索引，-1代表没找到
     */
    public static int binarySearch(int[] arr,int left,int right,int value){
        //左指针大于右指针时递归结束
        if (/*left <= right*/ value >= arr[left] && value <= arr[right]){
            //计算中点
            int mid = (left + right) / 2;
            //中点的值等于value，找到了，返回
            if (arr[mid] == value){
                return mid;
            }
            //中点小于value，向右递归查找
            if (arr[mid] < value){
                return binarySearch(arr, mid + 1, right, value);
            }else {//中点大于value，向左递归查找
                return binarySearch(arr, left, mid - 1, value);
            }
        }
        //递归结束时还没找到，返回-1
        return -1;
    }
}
