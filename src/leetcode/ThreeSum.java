package leetcode;

import java.util.*;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]*/
public class ThreeSum {

    /* mySolution 1 效率低*/
    public List<List<Integer>> mySolution(int[] nums) {
        Set<List<Integer>> res= new HashSet<List<Integer>>();
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i<nums.length;i++){
            if(set.contains(nums[i])){
                continue;
            }
            int value0 = nums[i];
            set.add(value0);
            int needNum = 0 - value0;
            Map<Integer,Integer> map = new HashMap<Integer,Integer>();
            for(int j = 0;j <nums.length;j++){
                if(j!=i){
                    if(map.containsKey(nums[j])){
                        int value1 = map.get(nums[j]);
                        int value2 = nums[j];
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(value0);
                        list.add(value1);
                        list.add(value2);
                        Collections.sort(list);
                        res.add(list);
                    }
                    map.put(needNum - nums[j],nums[j]);
                }
            }
        }
        return new ArrayList<>(res);
    }
}
