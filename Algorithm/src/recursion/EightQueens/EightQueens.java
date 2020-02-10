package recursion.EightQueens;

/**
 * @author baobao
 * @create 2019-10-05 23:03
 * @description 八皇后问题
 */
public class EightQueens {
    //皇后数量=8
    private static int queenNum = 8;
    //用一维数组模拟皇后摆放的位置，数组索引代表第几个皇后(棋盘的第几行)，对应的值代表皇后在棋盘对应行的摆放位置(第几列)
    private static int[] queenPos = new int[queenNum];
    //解法数量统计
    private static int count = 0;
    public static void main(String[] args) {
        //从第1个皇后开始放
        putQueen(0);
        //输出解法数量
        System.out.println("共有解法" + count + "种");
    }

    /**
     * 放置第n个皇后
     * @param n 第几个皇后
     */
    public static void putQueen(int n){
        //如果已经放置完最后一个皇后，说明已经找到了一种解法
        if (n == queenNum){
            count++;//解法数量++
            print();//打印解法
            return;//返回
        }
        //将这个皇后依次尝试放置在棋盘的每一列
        for (int i = 0; i < queenNum; i++) {
            queenPos[n] = i;//保存当前放置的列的索引
            //判断当前皇后放置的位置是否与之前已经在棋盘上的皇后位置冲突
            if (judge(n)){
                //如果不冲突，继续摆放下一个皇后
                putQueen(n + 1);
            }
        }
    }

    /**
     * 判断第n个皇后当前摆放的位置是否与已经在棋盘上的其他皇后的位置冲突
     * @param n 当前摆放的是第几个皇后
     * @return  如果不冲突返回true，否则返回false
     */
    private static boolean judge(int n) {
        //当前皇后(n)与之前摆放的所有皇后逐个进行是否冲突的判断
        for (int i = 0; i < n; i++) {
            //如果在同一列，则冲突
            if (queenPos[i] == queenPos[n]){
                return false;
            }
            //如果在同一对角线，则冲突
            if (Math.abs(queenPos[i] - queenPos[n]) == (n - i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 打印解法
     */
    public static void print(){
        //输出解法的序号
        System.out.println("解法" + count);
        //将解法中表示皇后位置的一维数组映射到棋盘的二维数组中，方便观看
        int[][] chessBoard = new int[queenNum][queenNum];
        //棋盘上女皇摆放位置为1，其余位置为0
        for (int i = 0; i < queenNum; i++) {
            chessBoard[i][queenPos[i]] = 1;
        }
        //打印整个棋盘
        for (int i = 0; i < queenNum; i++) {
            for (int j = 0; j < queenNum; j++) {
                System.out.print(chessBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
}
