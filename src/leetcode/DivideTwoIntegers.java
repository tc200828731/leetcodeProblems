package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 *
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.*/
public class DivideTwoIntegers {
    /* abs  负数时还是 直接加负号  但是最小值（负 2的32次方）的补码加1 还是不变*/
/*    public static void main(String[] args) {
        System.out.println(Math.abs(Integer.MIN_VALUE));
    }*/

    /*solution1 great 效率低 但是利用了二进制除法*/
    public int divide1(int dividend, int divisor) {
        long res = intdivide((long)dividend,(long)divisor);
        if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
            return Integer.MIN_VALUE -1;
        }
        return (int)res;
    }
    private long intdivide(long a, long b) {
        boolean neg = (a > 0) ^ (b > 0);
        if(a < 0)
            a = -a;
        if(b < 0)
            b = -b;
        if(a < b)
            return 0;
        int msb = 0;
        for(msb = 0; msb < 32; msb++) {
            if((b << msb) >= a)
                break;
        }
        long q = 0;
        for(int i = msb; i >= 0; i--) {
            if((b << i) > a)
                continue;
            q |= ((long)1 << i);//这里 1 必须转为long  不然移位后溢出 为符号位
            a -= (b << i);
        }
        if(neg)
            return -q;
        return q;
    }

    /*soulution 2*/
    public int divide2(int dividend, int divisor) {

        if((dividend == Integer.MIN_VALUE ) && divisor == -1) return Integer.MAX_VALUE;
        if((dividend == Integer.MIN_VALUE || dividend == Integer.MAX_VALUE) && divisor == 1) return dividend;

        int result = 0;
        long dend = Math.abs((long)dividend);
        long sor = Math.abs((long)divisor);

        long differ = dend;
        long temp = sor;
        int count = 1;
        while( differ >= sor ){
            if(differ > temp){
                temp += temp;
                count += count;
            }
            if(differ < temp ){
                temp = sor;
                count = 1;
            }
            differ -= temp;
            result += count;
        }

        if((dividend < 0 && divisor > 0) ||(dividend >0 && divisor <0)){
            return (int)(0-result);
        }
        return (int)result;
    }



    /*附： 二进制加减乘除
    *   <1> 常用的等式：-n = ~(n-1) = ~n+1

        <2> 获取整数n的二进制中最后一个1：n&(-n) 或者 n&~(n-1)，如：n=010100，则-n=101100，n&(-n)=000100

        <3> 去掉整数n的二进制中最后一个1：n&(n-1)，如：n=010100，n-1=010011，n&(n-1)=010000*/
    private int intdivide(int a, int b) {
        boolean neg = (a > 0) ^ (b > 0);
        if(a < 0)
            a = -a;
        if(b < 0)
            b = -b;
        if(a < b)
            return 0;
        int msb = 0;
        for(msb = 0; msb < 32; msb++) {
            if((b << msb) >= a)
                break;
        }
        int q = 0;
        //类似于十进制的除法
        for(int i = msb; i >= 0; i--) {
            if((b << i) > a)
                continue;
            q |= (1 << i);
            a -= (b << i);

        }
        if(neg)
            return -q;
        return q;
    }

    int add(int a, int b) {
        int carry, add;
        do{
            add = a ^ b;
            carry = (a & b) << 1;
            a = add;
            b = carry;
        }while(carry != 0);
        return add;
    }

    int subtract(int a, int b) {
        return add(a, add(~b, 1));
    }

    int multiply(int a, int b) {
        boolean neg = (b < 0);
        if(b < 0)
            b = -b;
        int sum = 0;
        Map<Integer,Integer> bit_map = new HashMap<>();
        for(int i = 0; i < 32; i++)
            bit_map.put(1 << i, i);
        while(b > 0) {
            int last_bit = bit_map.get(b & ~(b - 1));
            sum += (a << last_bit);
            b &= b - 1;
        }
        if(neg)
            sum = -sum;
        return sum;
    }

    /* abs  负数时还是 直接加负号  但是最小值（负 2的32次方）的补码加1 还是不变*/
/*    public static void main(String[] args) {
        System.out.println(Math.abs(Integer.MIN_VALUE));
    }*/
}
