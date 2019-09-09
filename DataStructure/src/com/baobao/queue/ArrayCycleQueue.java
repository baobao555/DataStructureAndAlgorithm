package com.baobao.queue;

/**
 * @author baobao
 * @date 2019/8/14 0014 16:45
 * @description  循环队列实现
 */
public class ArrayCycleQueue {
    private int front = 0; //指向队列首元素
    private int rear = 0; //指向队列尾元素的下一个元素
    private int maxSize = 0; //队列的容量 + 1(预留一个位置不保存元素)
    private int realSize = 0; //队列中的元素个数
    private int[] data; //队列数据

    public ArrayCycleQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public void addElement(int value){
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        data[rear++] = value;
        realSize++;
    }

    public int getElement(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        realSize--;
        return data[front++];
    }

    public void listQueue(){

    }

    public static void main(String[] args) {
        ArrayCycleQueue arrayCycleQueue = new ArrayCycleQueue(6);
        arrayCycleQueue.addElement(1);
        arrayCycleQueue.addElement(2);
        arrayCycleQueue.addElement(3);
        arrayCycleQueue.addElement(4);
        arrayCycleQueue.addElement(5);
        arrayCycleQueue.addElement(6);
        System.out.println(arrayCycleQueue.getElement());
        arrayCycleQueue.addElement(6);
        System.out.println(arrayCycleQueue.getElement());
    }
}
