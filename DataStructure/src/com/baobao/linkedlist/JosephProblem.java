package com.baobao.linkedlist;

/**
 * @author baobao
 * @create 2019-10-04 20:35
 * @description 约瑟夫问题
 *
 * 问题描述：编号为1,2,3.....n的人围坐成一圈，约定编号为k的人(1<=k<=n)从1开始报数，报到m的那个人出列，
 * 然后从出列的人的下一位开始，继续从1开始报数，报到m的那个人继续出列。。。如此循环，直到所有人都出列为止，
 * 形成一个出列编号的序列
 *
 * 解决思路：
 * 可以用不带头节点的单向循环链表来实现，报数即为节点指针的移动，出列即为删除节点
 */
public class JosephProblem {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //添加5个节点
        circleSingleLinkedList.add(1);
        circleSingleLinkedList.add(2);
        circleSingleLinkedList.add(3);
        circleSingleLinkedList.add(4);
        circleSingleLinkedList.add(5);
        //从第2个节点开始数，每次数3下后出圈
        circleSingleLinkedList.count(2, 3);//4 2 1 3 5
    }
}


//单向循环链表(不带头节点)
class CircleSingleLinkedList{
    //内部类，表示链表的节点
    class Node{
        int data;
        Node next;
    }

    private Node first = null;//链表的第一个节点

    //添加节点
    public void add(int value){
        //创建新节点
        Node newNode = new Node();
        newNode.data = value;
        //如果当前链表为空，则将first赋值为新节点
        if (first == null){
            first = newNode;
            first.next = first;//链表中只有1个新节点，该节点next指向自己
        }else {//链表不为空
            //遍历链表，找到最后一个节点
            Node last = first;
            while (last.next != first){
                last = last.next;
            }
            //原最后一个节点的next指向新增节点
            last.next = newNode;
            //新增节点的next指向first节点，形成循环链表
            newNode.next = first;
        }
    }

    /**
     * 打印指定条件下约瑟夫环的出圈顺序
     * @param k 从第k个节点开始数数
     * @param m 每次数m下后出圈
     */
    public void count(int k,int m){
        if (first == null){
            System.out.println("当前约瑟夫环没有节点");
            return;
        }
        if (k < 1 || m < 1){
            System.out.println("k或m的值不合法");
            return;
        }
        Node cur = first; //记录当前节点，初始化为first
        Node pre = first; //记录前一个节点，初始化为最后一个节点
        while (pre.next != first){
            pre = pre.next;
        }
        //开始数数前先将cur和pre向后移动k-1次
        for (int i = 0; i < k - 1; i++) {
            cur = cur.next;
            pre = pre.next;
        }
        //循环数数，当pre和cur指向同一个节点时，说明链表中只剩一个节点，跳出循环
        while (cur != pre){
            //数m下，将cur和pre同时向后移动m - 1次
            for (int i = 0; i < m - 1; i++) {
                cur = cur.next;
                pre = pre.next;
            }
            //cur即为需要出圈的节点，打印cur.data
            System.out.print(cur.data + " ");
            //从链表中移除cur
            pre.next = cur.next;
            //更新cur为下一个节点，重新开始数数
            cur = cur.next;
        }
        //打印最后一个出圈的节点
        System.out.print(cur.data);
        System.out.println();
    }
}
