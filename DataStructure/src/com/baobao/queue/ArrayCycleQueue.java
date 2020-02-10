package com.baobao.queue;

/**
 * @author baobao
 * @date 2019/8/14 0014 16:45
 * @description  循环队列实现
 */

/*
* 循环队列实现要点：
* 1、约定好成员变量的含义：
*  头指针front：指向队列首元素
*  尾指针rear：指向队列尾元素的下一个元素
*  容量maxSize：队列的容量 + 1(预留一个位置不保存元素，为了区分队列满和队列空的条件，如果直接定义成队列的最大容量
*                             将会导致队列空和队列满的条件都为front == rear，无法区分)
*
* 2、确定队列各种状态的条件：
*  队列为空：front == rear
*  队列满：(rear + 1) % maxSize == front
*  队列中实际元素的个数：(rear - front + maxSize) % maxSize
*
* 3、实现队列元素入队、出队等方法时，在对front或rear自增时注意要考虑循环的情况，即自增后再模上maxSize
* */
public class ArrayCycleQueue {
    private int front = 0; //指向队列首元素
    private int rear = 0; //指向队列尾元素的下一个元素
    private int maxSize = 0; //队列的容量 + 1(预留一个位置不保存元素，为了区分队列满和队列空的条件)
    private int realSize = 0; //队列中的元素个数
    private int realSize1 = 0; //队列中的元素个数
    private int[] data; //队列数据

    public ArrayCycleQueue(int maxSize) {
        //创建指定长度的数组
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    public boolean isEmpty(){
        //判断队列是否为空
        return front == rear;
    }

    public boolean isFull(){
        //判断队列是否已满(正因为队列预留了一个位置不保存元素，才使得队列满的条件发生变化，不与队列空的条件相同)
        return (rear + 1) % maxSize == front;
    }

    //元素入队
    public void addElement(int value){
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        //添加数据
        data[rear] = value;
        //队列尾指针加1，考虑循环
        rear = (rear + 1) % maxSize;
        //队列中元素个数加1
        realSize1++;
    }

    //元素出队
    public int getElement(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        //队列中元素个数减1
        realSize1--;
        //获取队列头部元素
        int i = data[front];
        //队列头指针加1，考虑循环
        front = (front + 1) % maxSize;
        return i;
    }

    //遍历打印队列中的元素
    public void listQueue(){
        int queueSize = getQueueSize();
        for (int i = front;i < front + queueSize;i++){
            System.out.print(data[i % maxSize] + " ");
        }
        System.out.println();
    }

    //得到队列中实际元素的个数
    public int getQueueSize(){
        realSize = (rear - front + maxSize) % maxSize;
        System.out.println("realsize:" + realSize);
        System.out.println("realsize1:" + realSize1);
        return realSize;
    }

    public static void main(String[] args) {
        ArrayCycleQueue arrayCycleQueue = new ArrayCycleQueue(6);
        arrayCycleQueue.addElement(1);
        arrayCycleQueue.addElement(2);
        arrayCycleQueue.addElement(3);
        arrayCycleQueue.addElement(4);
        arrayCycleQueue.addElement(5);
        arrayCycleQueue.addElement(6);
        arrayCycleQueue.listQueue();
        System.out.println(arrayCycleQueue.getElement());
        arrayCycleQueue.listQueue();
        System.out.println(arrayCycleQueue.getElement());
        arrayCycleQueue.listQueue();
        arrayCycleQueue.addElement(11);
        arrayCycleQueue.addElement(12);
        arrayCycleQueue.addElement(13);
        arrayCycleQueue.listQueue();
        System.out.println(arrayCycleQueue.getElement());
        arrayCycleQueue.listQueue();
    }
}
