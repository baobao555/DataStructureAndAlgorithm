package com.baobao.stack;

/**
 * @author baobao
 * @create 2019-10-04 22:57
 * @description 数组实现栈
 */
public class ArrayStack<T> {
    private int maxSize;//栈的最大容量
    private Object[] data; //栈的数据
    private int top = -1;//栈顶在数组中的索引，初始化为-1

    /**
     * @param maxSize 栈的最大容量
     */
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        //创建容量为maxSize的数组
        data = new Object[maxSize];
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return top == -1;
    }

    //判断栈是否已满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //数据入栈
    public void push(T value){
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        data[++top] = value;
    }

    //数据出栈
    public T pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return (T) data[top--];
    }

    //得到栈中元素个数
    public int size(){
        return top + 1;
    }

    //得到栈顶元素，但不出栈
    public T peek(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return (T) data[top];
    }

    //打印栈中所有元素
    public void print(){
        if (isEmpty()){
            System.out.println("栈为空，无法打印");
            return;
        }
        for (int i = 0; i < top + 1; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(6);
        arrayStack.push(1);
        arrayStack.push(3);
        arrayStack.push(5);
        arrayStack.push(7);
        arrayStack.push(9);
        arrayStack.push(11);
        arrayStack.push(13);

        arrayStack.print();
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        arrayStack.print();
    }
}
