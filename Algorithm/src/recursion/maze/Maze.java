package recursion.maze;

/**
 * @author baobao
 * @create 2019-10-05 22:08
 * @description 实现迷宫算法
 */
public class Maze {
    public static void main(String[] args) {
        //创建二维数组模拟迷宫，其中1表示迷宫的边界或挡板(走不通的地方),0表示可以走的地方。出口是在maze[6][6]位置
        int[][] maze = new int[8][8];
        //设置迷宫上、下边界
        for (int i = 0; i < 8; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        //设置迷宫左右边界
        for (int i = 0; i < 8; i++) {
            maze[i][0] = 1;
            maze[i][7] = 1;
        }
        //设置挡板
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[3][3] = 1;

        //输出迷宫
        printMaze(maze);

        //以(1,1)为起点，寻找出口路线
        findWay(maze, 1, 1);
        //输出路线
        printMaze(maze);

    }

    /**
     * 寻找迷宫的出口
     * 约定：
     * 1、当maze[i][j]为0，表示还没有走过
     * 2、当maze[i][j]为1，表示为迷宫边界或挡板
     * 3、当maze[i][j]为2，表示该点走过，可以走通
     * 4、当maze[i][j]为3，表示该点走过，但是走不通
     * 5、找出口的策略：下、右、上、左
     * @param maze 代表迷宫的二维数组
     * @param x  出发点的x坐标
     * @param y  出发点的y坐标
     */
    public static boolean findWay(int[][] maze,int x,int y){
        if (maze[6][6] == 2){ //已找到通往出口的路线，返回
            return true;
        }else {
            if (maze[x][y] == 0){ //该点还没有走过
                maze[x][y] = 2;//假设可以走通,防止递归回原来走过的格子
                //按照下、右、上、左的策略走
                if (findWay(maze, x+1, y)){
                    return true;
                }else if (findWay(maze, x, y + 1)){
                    return true;
                }else if (findWay(maze, x - 1, y)){
                    return true;
                }else if (findWay(maze, x, y - 1)){
                    return true;
                }else {
                    maze[x][y] = 3;
                    return false;
                }
            }else {//recursion.maze[x][y] == 1,2或3
                return false;
            }
        }

    }

    /**
     * 打印迷宫地图
     * @param maze
     */
    public static void printMaze(int[][] maze){
        System.out.println("迷宫地图：");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
}
