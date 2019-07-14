package com.baobao.sparsearray;

/**
 * @author baobao
 * @create 2019-07-13 18:53
 * @description 实现稀疏数组和普通数组的转换
 *
 * 如果一个二维数组大部分数据都是默认值0，只有少量非0的有效数据，那么可以转化为稀疏数组保存数据，达到节省空间的目的
 *
 * 稀疏数组的具体实现原理：
 * 1、稀疏数组也是一个二维数组，行数为原二维数组的有效数据个数+1，列数固定为3
 * 2、第1行的3列分别保存原数组的行数、列数、有效数据个数
 * 3、第2行开始到最后一行，每行对应1个有效数据，每行的3列分别保存有效数据在原数组的行索引、列索引、有效数据的值
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] arr = new int[5][11];
        arr[0][1] = 1;
        arr[2][4] = 2;
        arr[3][5] = 3;
        System.out.println("原数组：");
        printArr(arr);
        System.out.println("===========================================");
        int[][] sparseArr = arr2SparseArr(arr);
        System.out.println("原数组转换后的稀疏数组:");
        printArr(sparseArr);
        System.out.println("===========================================");
        System.out.println("稀疏数组还原后的原数组:");
        printArr(sparseArr2Arr(sparseArr));
    }


    /**
     * 将二维数组转化为稀疏数组
     * @param arr 原二维数组
     * @return 稀疏数组
     */
    private static int[][] arr2SparseArr(int[][] arr){
        //1、得到原二维数组的行数和列数
        int rowNum = arr.length;
        int colNum = arr[0].length;
        //2、得到原二维数组中有效数据的个数
        int dataNum = 0;
        for (int[] arr1 : arr){
            for (int data : arr1){
                if (data != 0){
                    dataNum++;
                }
            }
        }
        //3、创建稀疏数组
        int[][] sparseArr = new int[dataNum + 1][3];
        //4、填充稀疏数组
        //填充第1行
        sparseArr[0][0] = rowNum;//第1行第1列保存原数组行数
        sparseArr[0][1] = colNum;//第1行第2列保存原数组列数
        sparseArr[0][2] = dataNum;//第1行第3列保存原数组有效数据个数

        //填充第2~dataNum + 1行，每行保存1个有效数据
        int sparseArrRowNum = 1;
        for (int i=0;i<rowNum;i++){
            for (int j=0;j<colNum;j++){
                if (arr[i][j] != 0){ //找到了有效数据
                    sparseArr[sparseArrRowNum][0] = i;//第1列保存有效数据行索引
                    sparseArr[sparseArrRowNum][1] = j;//第2列保存有效数据列索引
                    sparseArr[sparseArrRowNum][2] = arr[i][j];//第3列保存有效数据的值
                    sparseArrRowNum++;
                }
            }
        }
        return sparseArr;
    }

    /**
     * 将稀疏数组还原为二维数组
     * @param sparseArr 稀疏数组
     * @return 还原后的二维数组
     */
    private static int[][] sparseArr2Arr(int[][] sparseArr){
        //1、解析稀疏数组第一行，得到原始数组的行数、列数、有效数据个数
        int rowNum = sparseArr[0][0];
        int colNum = sparseArr[0][1];
        int dataNum = sparseArr[0][2];

        //2、创建原始数组
        int[][] arr = new int[rowNum][colNum];

        //3、遍历稀疏数组的第2~dataNum + 1行，取出有效数据的信息，并在原始数组的相应位置赋值
        for (int i = 1;i <= dataNum;i++){
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return arr;
    }

    /**
     * 以表格形式打印二维数组
     * @param arr 需要打印的二维数组
     */
    private static void printArr(int[][] arr){
        for (int[] arr1 : arr){
            for (int data : arr1){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
