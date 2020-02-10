package com.baobao.stack;


/**
 * @author baobao
 * @create 2019-10-05 11:07
 * @description 利用逆波兰表达式(后缀表达式)计算算数表达式
 *
 * 逆波兰表达式(后缀表达式)：传统算数表达式为中缀表达式(33+4)*5-6，即算数运算符位于两个数字中间。
 * 而逆波兰表达式为后缀表达式，算数运算符位于两个参与运算的数字后面，如33 4 + 5 * 6 -
 * 逆波兰表达式非常利于计算机计算
 *
 * 将传统算数表达式(中缀表达式)转换为逆波兰表达式(后缀表达式)的步骤：
 * 1、创建2个栈：SuffixExpStack(保存逆波兰表达式的结果)和operStack(保存运算操作符)
 * 2、从左至右遍历中缀表达式：
 *    -遇到数字时，直接将其压入SuffixExpStack
 *    -遇到算数操作符时：
 *       --如果operStack为空，或operStack栈顶是左括号"("，直接压入operStack
 *       --如果当前操作符优先级比operStack栈顶操作符的优先级高，直接压入operStack
 *       --如果当前操作符优先级比operStack栈顶操作符的优先级低，则将operStack的栈顶符号pop出来，压入SuffixExpStack，然后
 *         再次将当前操作符与operStack新的栈顶元素按照同样规则比较，直至把当前操作符压入SuffixExpStack为止
 *    -遇到括号时：
 *       --如果是左括号"("，直接压入operStack
 *       --如果是右括号")"，依次pop出operStack的操作符压入SuffixExpStack，直至遇到左括号"("，最后将左右括号丢弃
 * 3、当遍历完成中缀表达式后，将operStack剩余的符号依次弹出，并压入SuffixExpStack
 * 4、最终将SuffixExpStack中的元素依次弹出并且进行倒序后形成的字符串即为逆波兰表达式
 *
 *
 * 计算逆波兰表达式结果的步骤：
 * 1、将逆波兰表达式用空格为分隔符分割为一个String数组
 * 2、创建一个数栈
 * 3、遍历String数组
 *    -如果当前String为数字，则直接压入数栈
 *    -如果当前String为算数运算符，则从数栈中pop出2个数，并根据当前算数运算符计算出结果并压入数栈
 * 4、遍历完成String数组后，数栈中剩下的那个数即为计算结果
 */
public class ReversePolandNotation {
    public static void main(String[] args) {
        //逆波兰表达式
        //String suffixExpression = "33 4 + 5 * 6 -";
        //String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";

        //常规中缀表达式
        String exp = "33 + 5 * ( 2 + 6 ) - 7";
        //将中缀表达式转换为逆波兰表达式
        String suffixExpression = convert2SuffixExp(exp);
        //将逆波兰表达式用空格为分隔符分割为一个String数组
        String[] strings = suffixExpression.split(" ");
        //创建一个数栈
        ArrayStack<Integer> numStack = new ArrayStack(10);
        //遍历String数组
        for (String s : strings){
            //如果当前String为数字，则直接压入数栈
            if (s.matches("\\d+")){
                numStack.push(Integer.valueOf(s));
            }else {//如果当前String为算数运算符
                //从数栈中pop出2个数
                int num2 = numStack.pop();
                int num1 = numStack.pop();
                //根据当前算数运算符计算出结果
                int result = 0;
                if (s.equals("+")){
                    result = num1 + num2;
                }else if (s.equals("-")){
                    result = num1 - num2;
                }else if (s.equals("*")){
                    result = num1 * num2;
                }else if (s.equals("/")){
                    result = num1 / num2;
                }
                //将计算结果压入数栈
                numStack.push(result);
            }
        }
        //遍历完成String数组后，数栈中剩下的那个数即为计算结果
        System.out.println("result=" + numStack.peek());
    }

    /**
     * 将中缀表达式转换为逆波兰表达式
     * @param exp 中缀表达式
     * @return 逆波兰表达式的字符串
     */
    public static String convert2SuffixExp(String exp){
        //将中缀表达式用空格分割出一个String数组
        String[] strings = exp.split(" ");
        //创建SuffixExpStack(保存逆波兰表达式的结果)和operStack(保存运算操作符)
        ArrayStack<String> SuffixExpStack = new ArrayStack(20);
        ArrayStack<Character> operStack = new ArrayStack(20);
        //遍历中缀表达式
        for (String s : strings){
            //如果是数字
            if (s.matches("\\d+")){
                SuffixExpStack.push(s);//直接压入SuffixExpStack
            }else if (Calculator.CalculatorUtils.isOperation(s.charAt(0))){ //如果是算数运算符
                //如果operStack为空或者栈顶是'('
                if (operStack.isEmpty() || operStack.peek() == '('){
                    operStack.push(s.charAt(0));//直接压入operStack
                }else if (Calculator.CalculatorUtils.getOperPriority(s.charAt(0)) //如果当前运算符优先级>operStack栈顶
                        > Calculator.CalculatorUtils.getOperPriority(operStack.peek())){
                    operStack.push(s.charAt(0));//直接压入operStack
                }else if (Calculator.CalculatorUtils.getOperPriority(s.charAt(0))
                        <= Calculator.CalculatorUtils.getOperPriority(operStack.peek())){//如果当前运算符优先级<=operStack栈顶
                    SuffixExpStack.push(String.valueOf(operStack.pop()));//将operStack栈顶元素压入SuffixExpStack
                    while (!operStack.isEmpty() && operStack.peek() != '(' && Calculator.CalculatorUtils.getOperPriority(s.charAt(0))
                            <= Calculator.CalculatorUtils.getOperPriority(operStack.peek())){//重复上述判断，直到operStack为空或栈顶为'('或当前运算符优先级>operStack栈顶
                        SuffixExpStack.push(String.valueOf(operStack.pop()));//将operStack栈顶元素压入SuffixExpStack
                    }
                    operStack.push(s.charAt(0));//将当前运算符压入operStack
                }
            }else if (s.charAt(0) == '('){//如果是'('
                operStack.push(s.charAt(0));//直接压入operStack
            }else if (s.charAt(0) == ')'){//如果是')'
                //将operStack栈顶压入SuffixExpStack，直到碰到operStack栈顶为'('
                while (operStack.peek() != '('){
                    SuffixExpStack.push(String.valueOf(operStack.pop()));
                }
                //丢弃operStack栈顶的'('
                operStack.pop();
            }
        }

        //遍历完中缀表达式后，将operStack剩余的运算符都按顺序压入SuffixExpStack
        while (!operStack.isEmpty()){
            SuffixExpStack.push(String.valueOf(operStack.pop()));
        }

        //创建StringBuilder，保存逆波兰表达式
        StringBuilder stringBuilder = new StringBuilder();
        //将SuffixExpStack中的元素按顺序弹出，并加入stringBuilder中，元素之间用空格分隔
        while (!SuffixExpStack.isEmpty()){
            if (SuffixExpStack.size() == 1){
                stringBuilder.append(SuffixExpStack.pop());//最后一个元素后面无需加空格
            }else {
                stringBuilder.append(SuffixExpStack.pop()).append(" ");
            }
        }
        //将StringBuilder逆序返回
        return stringBuilder.reverse().toString();
    }
}
