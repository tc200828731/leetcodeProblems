package leetcode;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Input: ["dog","racecar","car"]
 * Output: ""
 * */
public class LongestCommonPrefix {
    public String mySolution (String[] strs) {
        String res = "";
        int minLen = Integer.MAX_VALUE;
        int i;
        for(i = 0; i<strs.length; i++){
            minLen = Math.min(minLen,strs[i].length());
        }
        for(i = 0; i<minLen; i++){
            int j = 0;
            while(j+1<strs.length && strs[j].charAt(i) == strs[j+1].charAt(i)){
                j++;
            }
            if(j+1!=strs.length){
                return res;
            }else{
                res += strs[0].charAt(i);
            }
        }
        return res;
    }

    /* solution 2 */
    public String horizontalScanning(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }
    /* solution 3*/
    public String binarySearch(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }

    /*solution 4*/
    public String divideAndConquer(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0 , strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        }
        else {
            int mid = (l + r)/2;
            String lcpLeft =   longestCommonPrefix(strs, l , mid);
            String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    private String commonPrefix(String left,String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if ( left.charAt(i) != right.charAt(i) )
                return left.substring(0, i);
        }
        return left.substring(0, min);
    }
}
