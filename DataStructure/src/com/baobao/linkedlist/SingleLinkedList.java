package com.baobao.linkedlist;

import java.util.Stack;

/**
 * @author baobao
 * @create 2019-10-03 16:08
 * @description  单向链表的实现
 */
public class SingleLinkedList {
    //内部类，表示链表的节点
    class Node{
        int data;
        Node next;
    }

    private Node head = new Node();//头节点
    private int size = 0;//链表当前有效节点总数

    //将数据插入到链表尾部
    public void add(int value){
        //创建需要插入的节点
        Node node = new Node();
        node.data = value;
        //找到链表的尾节点
        Node tail = head;
        while (tail.next != null){
            tail = tail.next;
        }
        //将新节点追加到尾节点的后面
        tail.next = node;
        size++;
    }

    /**
     * 指定位置插入节点
     * @param value 需插入节点的值
     * @param position  插入的位置
     */
    public void add(int value,int position){
        //创建需要插入的节点
        Node newNode = new Node();
        newNode.data = value;
        //判断不合理的position
        if (position < 0){
            System.out.println("插入的位置不能为负数");
            return;
        }
        if (size < position + 1){
            System.out.println("当前指定的位置已超出链表的最大位置");
            return;
        }

        //找到待插入位置的前一个节点
        Node temp = head;
        for (int i=0;i<position;i++){
            temp = temp.next;
        }
        //新插入节点的下一个节点赋值为待插入位置的前一个节点的下一个节点
        newNode.next = temp.next;
        //待插入位置的前一个节点的下个节点赋值为新插入节点
        temp.next = newNode;
        //链表大小+1
        size++;
    }

    /**
     * 更新节点
     * @param oldValue  需要更新的节点的值
     * @param newValue  更新后节点的值
     */
    public void update(int oldValue,int newValue){
        if (size == 0){
            System.out.println("当前链表还没有元素，无法修改");
            return;
        }
        //遍历链表，找到对应的节点并修改其数据
        Node temp = head;
        boolean isFound = false;
        while (temp.next != null){
            temp = temp.next;
            if (temp.data == oldValue){
                temp.data = newValue;
                isFound = true;
                break;
            }
        }

        if (!isFound){
            System.out.println("没有找到值为" + oldValue + "的元素,故修改失败");
        }
    }

    /**
     * 删除节点
     * @param value 需删除节点的值
     */
    public void delete(int value){
        if (size == 0){
            System.out.println("当前链表还没有元素，无法删除");
            return;
        }
        Node temp = head;
        boolean isFound = false;
        //遍历链表，找到被删除节点的前一个节点
        while (temp.next != null){
            if (temp.next.data == value){
                isFound = true;
                break;
            }
            temp = temp.next;
        }
        //如果链表中有待删除节点，则执行删除
        if (isFound){
            temp.next = temp.next.next;
        }else {//否则提示错误
            System.out.println("没有找到值为" + value + "的元素,故删除失败");
        }
    }

    //遍历打印链表
    public void print(){
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
            System.out.print(temp.data + " ");
        }
        System.out.println();
    }

    /**
     * 得到链表中有效节点的个数
     * @return 有效节点的个数
     */
    public int getSize(){
        int size = 0;
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
            size++;
        }
        return size;
    }

    /**
     * 查找并返回单链表中倒数第n个节点
     * @param n 倒数第n个节点
     * @return  找到的节点对象
     */
    public Node getReverseNode(int n){
        if (n <= 0){
            System.out.println("倒数第n个节点，n必须大于0");
            return null;
        }
        //将倒数第n个节点转换为正数第几个节点
        int size = getSize();
        int m = size - n;
        if (m < 0){
            System.out.println("当前指定的倒数位置已超出链表的最大位置");
            return null;
        }
        Node temp = head.next;
        for (int i = 0; i < m; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //反转链表：顺序遍历所有节点，每次都将得到的节点插入头节点的后面
    public void reverseList(){
        if (head.next == null){
            System.out.println("链表为空，无法反转");
            return;
        }
        if (head.next.next == null){
            System.out.println("链表中只有1个节点，无需反转");
            return;
        }
        Node cur = head.next;//当前节点，从第1个节点开始遍历
        Node next = null;//保存当前节点的下一个节点
        head.next = null;//清空头指针
        while (cur != null){ //遍历原链表
            next = cur.next;//保存当前节点的下一个节点
            //将当前节点插入到链表的头部
            cur.next = head.next;//当前节点的下一个节点赋值为原先头节点的下一个节点
            head.next = cur;//头节点的下一个节点赋值为当前节点
            cur = next;//当前节点后移，得到下一个节点
        }
    }

    //逆序打印原链表
    public void reversePrint(){
        if (head.next == null){
            System.out.println("链表中没有元素，无法逆序打印");
            return;
        }
        //创建一个栈
        Stack<Integer> stack = new Stack<>();
        //顺序遍历链表，将每个元素压入栈
        Node temp = head.next;
        while (temp != null){
            stack.push(temp.data);
            temp = temp.next;
        }
        /*int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.print(stack.pop() + " ");
        }*/
        //依次弹出栈顶元素打印
        while (stack.size() > 0){
            System.out.print(stack.pop() + " ");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.print();
        linkedList.reversePrint();
        /*linkedList.reverseList();
        linkedList.print();*/
        /*linkedList.add(6,0);
        linkedList.print();
        linkedList.add(7,6);
        linkedList.print();

        linkedList.update(4, 0);
        linkedList.update(10, 0);
        linkedList.print();
        linkedList.delete(2);
        linkedList.delete(10);
        linkedList.print();
        System.out.println(linkedList.getSize());

        System.out.println(linkedList.getReverseNode(1).data);
        System.out.println(linkedList.getReverseNode(2).data);
        System.out.println(linkedList.getReverseNode(3).data);
        System.out.println(linkedList.getReverseNode(4).data);
        System.out.println(linkedList.getReverseNode(5).data);
        System.out.println(linkedList.getReverseNode(6).data);*/
    }


}
