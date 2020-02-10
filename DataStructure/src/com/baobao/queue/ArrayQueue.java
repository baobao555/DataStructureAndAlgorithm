package com.baobao.queue;

/**
 * @author baobao
 * @date 2019/8/7 0007 17:08
 * @description 队列的数组实现
 */
public class ArrayQueue {
    private int front = 0;//指向队列首元素
    private int rear = 0;//指向队列尾元素的下一个元素
    private int maxSize = 0;//队列容量
    private int[] data;//队列的数据

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public boolean isFull(){
        return rear == maxSize;
    }

    public void addElement(int value){
        if (isFull()){
            System.out.println("队列已满，无法再入队");
            return;
        }
        data[rear++] = value;
    }

    public int getElement(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无法取出数据");
        }
        return data[front++];
    }

    public void listQueue(){
        if (isEmpty()){
            System.out.println("队列为空，无法遍历");
            return;
        }
        for (int i = front;i <= rear - 1;i++){
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        queue.addElement(1);
        queue.addElement(3);
        queue.addElement(5);

        queue.listQueue();
        System.out.println(queue.getElement());
        queue.addElement(7);
    }
}
