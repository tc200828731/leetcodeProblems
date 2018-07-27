package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * 卡特兰数 https://blog.csdn.net/u011080472/article/details/51162768
 * 卡特兰数非常经典，很多现实的问题都是卡特兰数，如合法的入栈出栈序列有多少种就是卡特兰数，为什么呢？我们可以把0看成入栈操作，1看成出栈操作，即0的累计个数不小于1的排列有多少种。还有很多其他的问题都是卡特兰数，如二叉树的个数，有序树的个数，多边形分成三角形的个数等。
 * */
public class GenerateParentheses {

    /*my solution 26%*/
    private List<String> list = new ArrayList<>();
    public List<String> generateParenthesis(int n) {

        rec(0,n,0,"","");
        return list;
    }

    private void rec(int i,int n,int sign,String str,String stack){
        if(i == n && stack.length() == 0){
            list.add(str);
            return;
        }
        if(i<n &&sign == 0){
            i++;
            stack+="(";
            str+="(";
            if(i<n){
                rec(i,n,0,str,stack);
            }
            rec(i,n,1,str,stack);
        }else if(stack.length() != 0){
            stack = stack.substring(0,stack.length()-1);
            str+=")";
            if(i<n){
                rec(i,n,0,str,stack);
            }
            rec(i,n,1,str,stack);
        }
    }


    /* Backtracking 类似于我的方法 但简化了许多 */
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }

    /*  the recursive expression of Catalan number */
    public List<String> generateParenthesis3(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
}
