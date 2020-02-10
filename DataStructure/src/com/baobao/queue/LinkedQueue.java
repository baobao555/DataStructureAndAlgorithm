package com.baobao.queue;

/**
 * @author baobao
 * @create 2019-10-02 22:23
 * @description 队列的链表实现
 */
public class LinkedQueue {
    private int maxSize;//队列的最大容量
    private Node front;//头节点：指向对列首元素的上一个节点

    class Node{ //链表队列的元素节点
        int data; //数据
        Node next; //节点的下一个节点
    }

    public LinkedQueue(int maxSize) {
        this.maxSize = maxSize;
        front = new Node();//初始化头节点
    }

    //队列是否为空
    public boolean isEmpty(){
        return front.next == null;
    }

    //队列是否已满
    public boolean isFull(){
        return getQueueSize() == maxSize;
    }

    //元素入队
    public void addElement(int value){
        if (isFull()){
            System.out.println("队列已满，无法再入队");
            return;
        }
        Node tail = front;//定义尾节点
        //遍历链表节点，寻找尾节点
        while (tail.next != null){
            tail = tail.next;
        }
        //创建需要入队的节点
        Node node = new Node();
        node.data = value;
        //将新建节点添到当前尾节点的后面
        tail.next = node;
    }

    //元素出队
    public int getElement(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无法出队");
        }
        //得到首元素
        Node firstEle = front.next;
        int v = firstEle.data;
        //移除首元素
        front.next = firstEle.next;
        return v;
    }

    //得到队列实际的元素个数
    public int getQueueSize(){
        Node node = front;//保存节点的临时变量
        int size = 0;//队列实际元素个数
        //遍历队列链表，直到尾部，每碰到一个有效元素，将size++
        while (node.next != null){
            size++;
            node = node.next;
        }
        return size;
    }

    //遍历打印队列
    public void listQueue(){
        if (isEmpty()){
            System.out.println("队列为空，无法打印");
            return;
        }

        Node node = front.next;
        while (node.next != null){
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println(node.data + " ");
    }

    public static void main(String[] args) {
        LinkedQueue linkedQueue = new LinkedQueue(5);
        /*System.out.println(linkedQueue.isEmpty());
        System.out.println(linkedQueue.isFull());
        System.out.println(linkedQueue.getQueueSize());
        linkedQueue.listQueue();*/

        linkedQueue.addElement(1);
        linkedQueue.addElement(2);
        linkedQueue.addElement(3);
        linkedQueue.addElement(4);
        linkedQueue.addElement(5);
        linkedQueue.addElement(6);
        linkedQueue.listQueue();

        System.out.println(linkedQueue.getElement());
        linkedQueue.listQueue();
        System.out.println(linkedQueue.getElement());
        linkedQueue.listQueue();
        linkedQueue.getElement();
        linkedQueue.getElement();
        linkedQueue.getElement();
        linkedQueue.listQueue();
        linkedQueue.getElement();
    }
}
