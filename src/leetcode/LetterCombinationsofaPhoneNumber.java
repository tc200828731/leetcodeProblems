package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].*/
public class LetterCombinationsofaPhoneNumber {
    private List<String> list = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        int len = digits.length();
        int i = 0;
        String str = "";
        if(digits.equals("")) return list;
        lettercom(digits,i,str);
        return list;
    }

    public void lettercom(String digits,int i,String str){
        if(i == digits.length()){
            list.add(str);
            return;
        }
        int num = digits.charAt(i) - '0';
        switch(num){
            case 2:
                lettercom(digits,1+i,str+"a");
                lettercom(digits,1+i,str+"b");
                lettercom(digits,1+i,str+"c");
                break;
            case 3:
                lettercom(digits,1+i,str+"d");
                lettercom(digits,1+i,str+"e");
                lettercom(digits,1+i,str+"f");
                break;

            case 4:
                lettercom(digits,1+i,str+"g");
                lettercom(digits,1+i,str+"h");
                lettercom(digits,1+i,str+"i");
                break;

            case 5:
                lettercom(digits,1+i,str+"j");
                lettercom(digits,1+i,str+"k");
                lettercom(digits,1+i,str+"l");
                break;

            case 6:
                lettercom(digits,1+i,str+"m");
                lettercom(digits,1+i,str+"n");
                lettercom(digits,1+i,str+"o");
                break;

            case 7:
                lettercom(digits,1+i,str+"p");
                lettercom(digits,1+i,str+"q");
                lettercom(digits,1+i,str+"r");
                lettercom(digits,1+i,str+"s");
                break;

            case 8:
                lettercom(digits,1+i,str+"t");
                lettercom(digits,1+i,str+"u");
                lettercom(digits,1+i,str+"v");
                break;

            case 9:
                lettercom(digits,1+i,str+"w");
                lettercom(digits,1+i,str+"x");
                lettercom(digits,1+i,str+"y");
                lettercom(digits,1+i,str+"z");
                break;
        }
    }
}
