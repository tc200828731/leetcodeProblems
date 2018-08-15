package leetcode;

/**
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class StrStr {

    /*my solution*/
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        int len = haystack.length();
        for (int i = 0; i < len; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (compare(haystack, needle, i)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean compare(String haystack, String needle, int index) {
        int len = needle.length();
        if (haystack.length() < len + index) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (haystack.charAt(i + index) != needle.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /*有趣的做法*/
    public int strStr2(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    //关于 KMP
    private static int[] getNext(char[] p) {
        int len = p.length;
        int[] next = new int[len];
        next[0] = -1;//next[0]初始化为-1，-1表示不存在相同的最大前缀和最大后缀
        int k = -1;//k初始化为-1
        for (int q = 1; q <= len - 1; q++) {
            while (k > -1 && p[k + 1] != p[q])//如果下一个不同，那么k就变成next[k]，注意next[k]是小于k的，无论k取任何值。
            {
                k = next[k];//往前回溯
            }
            if (p[k + 1] == p[q])//如果相同，k++
            {
                k = k + 1;
            }
            next[q] = k;//这个是把算的k的值（就是相同的最大前缀和最大后缀长）赋给next[q]
        }
        return next;
    }

    public static int KMP(String ts, String ps) {
        char[] str = ts.toCharArray();
        char[] ptr = ps.toCharArray();
        int plen = ptr.length;
        int slen = str.length;
        if (plen == 0) {
            return 0;
        }
        int k = -1;
        int[] next = getNext(ptr);
        for (int i = 0; i < slen; i++) {
            while (k > -1 && ptr[k + 1] != str[i])//ptr和str不匹配，且k>-1（表示ptr和str有部分匹配）
                k = next[k];//往前回溯
            if (ptr[k + 1] == str[i])
                k = k + 1;
            if (k == plen - 1)//说明k移动到ptr的最末端
            {
                //cout << "在位置" << i-plen+1<< endl;
                //k = -1;//重新初始化，寻找下一个
                //i = i - plen + 1;//i定位到该位置，外层for循环i++可以继续找下一个（这里默认存在两个匹配字符串可以部分重叠）
                return i - plen + 1;//返回相应的位置
            }
        }
        return -1;
    }

    //普通的蛮力
    public static int search(String txt, String pat){
        int j,M = pat.length();
        int i,N = txt.length();
        if(M == 0) return 0;
        for(i=0,j=0;i<N&&j<M;i++){
            if(txt.charAt(i) == pat.charAt(j)) j++;
            else{ i -= j;j=0;}
        }
        if (j==M) return i-M;
        else return -1;
    }
    // sunday
    
    public static void main(String[] args) {
        System.out.println(KMP("45678787787878", "787878"));
        System.out.println(search("45678787787878", "787878"));
    }

}
