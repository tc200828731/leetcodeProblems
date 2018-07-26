package leetcode;

import java.util.Stack;
/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.*/
public class ValidParentheses {

    /* 类似于栈 如果有操作数 可以用双栈*/
    public boolean isValid(String s) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            switch(c){
                case '(':
                case '[':
                case '{':
                    str.append(c);
                    break;
                case ')':
                    if(str.length()>0 && str.charAt(str.length()-1) == '('){
                        str.deleteCharAt(str.length()-1);
                    }else{
                        return false;
                    }
                    break;
                case ']':
                    if(str.length()>0 && str.charAt(str.length()-1) == '['){
                        str.deleteCharAt(str.length()-1);
                    }else{
                        return false;
                    }
                    break;
                case '}':
                    if(str.length()>0 && str.charAt(str.length()-1) == '{'){
                        str.deleteCharAt(str.length()-1);
                    }else{
                        return false;
                    }
                    break;
            }
        }
        return str.length() == 0;
    }

    /* 使用栈 来解决 反而慢了 建栈的开销*/
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i<s.length();i++){
            char c = s.charAt(i);
            switch(c){
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if(stack.isEmpty() || stack.pop() != '('){
                        return false;
                    }
                    break;
                case ']':
                    if(stack.isEmpty() || stack.pop() != '['){
                        return false;
                    }
                    break;
                case '}':
                    if(stack.isEmpty() || stack.pop() != '{'){
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

    /* 模拟栈 效率高*/
    public boolean isValid3(String s) {
        char[] stack = new char[s.length()];
        int top = 0;
        for (char c: s.toCharArray()) {
            switch (c) {
                case '(': stack[top++] = ')';break;
                case '[': stack[top++] = ']';break;
                case '{': stack[top++] = '}';break;
                default: if (top == 0 || c != stack[--top]) return false;
            }
        }
        return top == 0;
    }
}
