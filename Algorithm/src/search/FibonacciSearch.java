package search;

/**
 * @author baobao
 * @create 2019-10-13 23:18
 * @description 斐波那契查找
 *
 *
 * 利用斐波那契数列进行查找。斐波那契数列满足：F(n)=F(n-1)+F(n-2)（n>=2，n∈N*）
 * 可以推导得出：F(n) - 1 = [F(n-1) - 1] + [F(n-2) - 1] + 1
 * 那么假设待查找数组的长度为F(n) - 1，则它可以分割为F(n-1) - 1和F(n-2) - 1这2部分
 * 那么mid = left + F(n-1) - 1
 *
 * 步骤：
 * 1、如果待查找数组的长度不等于斐波那契数列中某一项的值，那么在斐波那契数列中找到比待查找数组长度大的最小数
 * 2、将待查找数组长度扩充至与斐波那契数列中的值相同，后面扩容的数据值用数组中最大值填充
 * 3、根据斐波那契数列分割待查找数组，循环查找
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1,3,5,666,888,888,1000,1005};
        //int[] arr = {1,1,1,1,1,1,1};
        System.out.println(fibonacciSearch(arr, 888));
    }

    /**
     * 斐波那契查找
     * @param arr 待查找的数组
     * @param value 要查找的值
     * @return 查找的值在数组中的索引，如果没找到返回-1
     */
    public static int fibonacciSearch(int[] arr,int value){
        int len = arr.length; //保存原数组的长度
        int n = 0;//原数组长度在斐波那契数列中对应的索引
        //寻找斐波那契数列中第一个大于len的值以及对应的索引n
        while (fibonacciValue(n) - 1 < len){
            n++;
        }
        //如果原数组长度len并不刚好等于fibonacciValue(n) - 1，则需要扩容原数组长度至fibonacciValue(n) - 1
        if (fibonacciValue(n) - 1 != len){
            //创建一个新数组，长度为fibonacciValue(n) - 1
            int[] fibonacciArr = new int[fibonacciValue(n) - 1];
            //将原数组的值拷贝至新数组
            for (int j = 0; j < fibonacciArr.length; j++) {
                //如果索引>=原数组长度，说明是扩容的部分，用原数组中的最后一个元素来填充
                if (j >= len){
                    fibonacciArr[j] = arr[len - 1];
                }else {
                    //拷贝原数组中的值到新数组
                    fibonacciArr[j] = arr[j];
                }
            }
            //将原数组的引用更新为新数组
            arr = fibonacciArr;
        }


        int left = 0;//左指针，初始化为0
        int right = arr.length - 1;//右指针，初始化为数组长度-1
        //当左指针没有和右指针碰撞时，循环查找
        while (left <= right){
            //计算中间指针位置
            int mid = left + fibonacciValue(n - 1) - 1;
            //如果找到了
            if (arr[mid] == value){
                //如果索引mid > 原数组长度 - 1,说明找到的索引位于原数组扩容的部分，这时返回原数组的最后一个索引即可；否则返回mid
                return mid > len - 1 ? len - 1 : mid;
            }
            //待查找value < arr[mid]
            if (arr[mid] < value){
                //向右查找，更新左指针
                left = mid + 1;
                //右边的数组长度为F(n-2) - 1,故需要更新n
                n = n - 2;
            }
            //待查找value > arr[mid]
            if (arr[mid] > value){
                //向右查找，更新右指针
                right = mid - 1;
                //左边的数组长度为F(n-1) - 1,故需要更新n
                n = n - 1;
            }
        }
        //循环结束没找到，返回-1
        return -1;

        /*int findIndex = search(arr,0,arr.length - 1,n,value);
        return findIndex > len - 1 ? len - 1 : findIndex;*/
    }

    /*private static int search(int[] arr, int left, int right,int n,int value) {
        if (left <= right){
            int mid = left + fibonacciValue(n - 1) - 1;
            if (arr[mid] == value){
                return mid;
            }
            if (arr[mid] > value){
                return search(arr, 0, mid - 1, n - 1, value);
            }else {
                return search(arr, mid + 1, right, n - 2, value);
            }
        }
        return -1;
    }*/

    /**
     * 得到斐波那契数列中某一项的值
     * @param n 斐波那契数列中的索引
     * @return 返回对应索引的值
     */
    public static int fibonacciValue(int n){
        if (n == 0 || n == 1){
            return 1;
        }
        return fibonacciValue(n - 1) + fibonacciValue(n - 2);
    }
}
