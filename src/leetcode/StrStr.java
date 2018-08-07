package leetcode;

/**
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().*/
public class StrStr {

    /*my solution*/
    public int strStr(String haystack, String needle) {
        if(needle ==null || needle.isEmpty()){
            return 0;
        }
        int len = haystack.length();
        for(int i = 0; i<len; i++){
            if(haystack.charAt(i) == needle.charAt(0)){
                if(compare(haystack,needle,i)){
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean compare(String haystack, String needle,int index){
        int len = needle.length();
        if(haystack.length() <len + index){
            return false;
        }
        for(int i=0;i<len;i++ ){
            if(haystack.charAt(i+index) != needle.charAt(i)){
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
}
