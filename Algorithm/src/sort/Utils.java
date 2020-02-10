package sort;

/**
 * @author baobao
 * @create 2019-10-06 23:55
 * @description  方便测试排序的工具类
 */
public class Utils {
    public static int[] createRandomArray(int length){
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        return arr;
    }

    public static void printArr(int[] arr){
        for (int i : arr){
            System.out.print(i + " ");
        }
    }
}
