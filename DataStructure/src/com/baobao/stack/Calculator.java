package com.baobao.stack;

/**
 * @author baobao
 * @create 2019-10-04 23:53
 * @description 利用栈计算算数表达式
 *
 * 需求：输入一个算数表达式字符串，计算结果
 *
 * 实现步骤：
 * 1、创建2个栈：数栈和符号栈
 * 2、将字符串表达式转换为char数组
 * 3、遍历char数组中的每个字符：
 *   -如果当前字符为数字，则直接压入数栈
 *   -如果当前字符为算数操作符：
 *     --如果符号栈为空，则将操作符直接压入符号栈
 *     --如果符号栈非空，则需要对当前操作符与符号栈顶部操作符进行优先级的比较
 *        ---如果当前操作符的优先级<=符号栈顶部操作符，要从数栈中pop出2个数，再从符号栈中pop出栈顶操作符，进行1次运算，
 *           并将运算结果压入数栈，然后将当前操作符压入符号栈
 *        ---如果当前操作符的优先级>符号栈顶部操作符，则直接将当前操作符压入符号栈
 * 4、当遍历完char数组后，将数栈和符号栈中剩余的数据按照顺序pop出来计算，结果压入数栈，如此循环。。
 * 5、当数栈中只剩下1个数时，这个数就是算数表达式的计算结果
 */
public class Calculator {
    public static void main(String[] args) {
        String exp = "5+2*3*6-7+8/2";//算数表达式
        ArrayStack<Integer> numStack = new ArrayStack(10);//数栈
        ArrayStack<Character> operStack = new ArrayStack(10);//符号栈
        //将算数表达式字符串转换为字符数组
        char[] chars = exp.toCharArray();
        //遍历字符数组
        for (char c : chars){
            //如果当前字符为数字，则直接压入数栈
            if (!CalculatorUtils.isOperation(c)){
                numStack.push(c - 48);//char类型存储的是asc码，转换为数字实际值需要-48
            }
            else {//如果当前字符为算数运算符
                //如果符号栈为空，直接压入符号栈
                if (operStack.isEmpty()){
                    operStack.push(c);
                }else {//如果符号栈非空
                    //如果当前符号优先级<=符号栈顶的符号
                    if (CalculatorUtils.getOperPriority(c) <= CalculatorUtils.getOperPriority(operStack.peek())){
                        //数栈中pop出2个数字
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        //符号栈中pop出栈顶符号
                        int oper = operStack.pop();
                        //用上面弹出的2个数字和1个符号进行数学运算，得到结果(注意：num2是先入栈的，故num2是表达式左边的数字)
                        int result = CalculatorUtils.cal(num2, num1, (char) oper);
                        //将结果压入数栈
                        numStack.push(result);
                        //将当前算数操作符压入符号栈
                        operStack.push(c);
                    }else {//如果当前符号优先级>符号栈顶的符号
                        //直接将当前符号压入符号栈
                        operStack.push(c);
                    }
                }

            }
        }

        //遍历表达式char数组完毕，开始对数栈和符号栈中剩余的数据按照顺序pop出来计算，结果压入数栈，如此循环。。
        //当数栈中只剩下1个数时，这个数就是算数表达式的计算结果
        //故当数栈中的元素个数>1时，需要循环取数计算
       while (numStack.size() > 1){
           //数栈中pop出2个数
           int num1 = numStack.pop();
           int num2 = numStack.pop();
           //符号栈中pop出1个运算符
           int oper = operStack.pop();
           //计算结果
           int result = CalculatorUtils.cal(num2, num1, (char) oper);
           //结果压入数栈
           numStack.push(result);
       }
       //打印最后的计算结果
        System.out.println("result=" + numStack.peek());
    }

    //算数工具类
    static class CalculatorUtils{
        /**
         * 判断字符是否为算数运算符
         * @param ch 需要判断的字符
         * @return 如果是算数运算符，返回true；否则返回false
         */
        public static boolean isOperation(char ch){
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                return true;
            }
            return false;
        }

        /**
         * 得到算数运算符优先级
         * @param oper 需要判断优先级的算数操作符
         * @return 算数运算符优先级：*和/为1，+和-为0
         */
        public static int getOperPriority(char oper){
            if (oper == '*' || oper == '/'){
                return 1;
            }
            return 0;
        }

        /**
         * 计算二元算数表达式的结果
         * @param num1 表达式左边的数字
         * @param num2 表达式右边的数字
         * @param oper 用于计算的算数操作符
         * @return 计算结果
         */
        public static int cal(int num1,int num2,char oper){
            int result = 0;
            switch (oper){
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            return result;
        }
    }
}
