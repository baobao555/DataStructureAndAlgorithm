package com.baobao.linkedlist;

/**
 * @author baobao
 * @create 2019-10-03 23:48
 * @description 双向链表的实现
 */
public class DoubleLinkedList {
    //内部类，代表双向链表的元素节点
    class Node{
        int data;//节点的数据
        Node pre;//当前节点的前一个节点
        Node next;//当前节点的后一个节点
    }

    private Node head = new Node();//头节点，不代表实际元素

    //添加元素到链表尾部
    public void add(int value){
        //创建待添加的元素节点
        Node newNode = new Node();
        newNode.data = value;
        //遍历链表找到尾节点
        Node tail = head;
        while (tail.next != null){
            tail = tail.next;
        }

        //将新节点添加到尾节点后面
        tail.next = newNode;
        //将原尾节点作为新节点的上一个节点
        newNode.pre = tail;
    }

    //添加元素到指定位置
    public void add(int value,int position){
        if (position < 0){
            System.out.println("插入位置不能为负数");
            return;
        }
        if (position > getSize() - 1){
            System.out.println("插入的位置超过链表的最大位置");
            return;
        }

        //找到待插入位置的前一个节点
        Node temp = head;//待插入位置的前一个节点
        for (int i = 0; i < position; i++) {
            temp = temp.next;
        }
        //创建待插入的新节点
        Node newNode = new Node();
        newNode.data = value;
        //将新节点与原来待插入位置的前一个节点的下一个节点建立关系
        newNode.next = temp.next;
        temp.next.pre = newNode;
        //将新节点与待插入位置的前一个节点建立关系
        temp.next = newNode;
        newNode.pre = temp;
    }

    //得到链表中元素的个数
    public int getSize(){
        int size = 0;
        Node temp = head;
        while (temp.next != null){
            size++;
            temp = temp.next;
        }
        return size;
    }

    //更新节点
    public void update(int oldValue,int newValue){
        if (head.next == null){
            System.out.println("当前链表没有元素，无法更新");
            return;
        }
        boolean flag = false;//标识是否找到待更新的元素
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
            //遍历链表，如果找到待更新的元素，更新数据和标志位，并退出循环
            if (temp.data == oldValue){
                temp.data = newValue;
                flag = true;
                break;
            }
        }
        if (!flag){
            System.out.println("当前链表中没有找到要更新的那个元素");
        }
    }

    //删除节点
    public void delete(int value){
        if (head.next == null){
            System.out.println("当前链表没有元素，无法删除");
            return;
        }
        boolean flag = false;//标识是否找到要删除的节点
        Node temp = head;
        //遍历链表，寻找待删的节点
        while (temp.next != null){
            temp = temp.next;
            if (temp.data == value){
                //找到待删除的节点，删除自身(将自身前后节点进行连接)
                temp.pre.next = temp.next;
                if (temp.next != null){ //判断待删除节点是否是尾节点，避免空指针异常
                    temp.next.pre = temp.pre;
                }
                flag = true;
                break;
            }
        }
        if (!flag){
            System.out.println("当前链表中没有找到需要删除的元素");
        }
    }

    //顺序打印链表
    public void print(){
        if (head.next == null){
            System.out.println("链表中没有元素，无法打印");
            return;
        }
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
            System.out.print(temp.data + " ");
        }
        System.out.println();
    }

    //逆序打印链表
    public void reversePrint(){
        if (head.next == null){
            System.out.println("链表中没有元素，无法打印");
            return;
        }
        //找到链表尾节点
        Node tail = head;
        while (tail.next != null){
            tail = tail.next;
        }
        //从尾节点开始向前遍历打印
        while (tail != head){
            System.out.print(tail.data + " ");
            tail = tail.pre;
        }
        System.out.println();
    }

    //得到倒数第n个节点
    public Node getReverseNode(int n){
        if (head.next == null){
            System.out.println("链表中没有元素");
            return null;
        }
        if (n < 0 || n > getSize()){
            System.out.println("n值不合理");
            return null;
        }
        //找到尾节点
        Node tail = head;
        while (tail.next != null){
            tail = tail.next;
        }
        //从尾节点向前移动，找到倒数第n个节点并返回
        for (int i = 0; i < n - 1; i++) {
            tail = tail.pre;
        }
        return tail;
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
        Node cur = head.next; //当前节点，从第1个节点开始遍历
        Node next = null; //保存当前节点的下一个节点
        head.next = null; //清空头指针
        while (cur != null){ //遍历原链表
            next = cur.next; //保存当前节点的下一个节点
            if (head.next != null){//头一次插入时，head节点后面还没有节点，head.next为null，故避免空指针异常需要判断是否头一次插入
                head.next.pre = cur; //原先头节点的下一个节点的前一个节点赋值为当前节点
            }
            //将当前节点插入到链表的头部
            cur.next = head.next; //当前节点的下一个节点赋值为原先头节点的下一个节点
            head.next = cur; //原先头节点的下一个节点赋值为当前节点
            cur.pre = head; //当前节点的前一个节点赋值为头节点
            //当前节点后移，得到下一个节点
            cur = next;
        }
    }


    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(1);
        doubleLinkedList.add(2);
        doubleLinkedList.add(5);
        doubleLinkedList.add(6);
        doubleLinkedList.add(7);
        doubleLinkedList.print();
        doubleLinkedList.add(3, 2);
        doubleLinkedList.print();
        doubleLinkedList.reversePrint();

        doubleLinkedList.update(7, 8);
        doubleLinkedList.print();
        doubleLinkedList.delete(8);
        doubleLinkedList.print();
        System.out.println(doubleLinkedList.getReverseNode(1).data);
        System.out.println(doubleLinkedList.getReverseNode(3).data);
        System.out.println(doubleLinkedList.getReverseNode(5).data);
        doubleLinkedList.reverseList();
        doubleLinkedList.print();
        doubleLinkedList.reversePrint();

    }
}
