package leetcode;

import java.util.*;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
* */
public class FourSum {

    /* 61ms mysolution */
    /* 两个数分别从排序后数组的左右两端向中间移动，中间的剩余两个数通过hashmap twosum 计算获得*/
    private Set<List<Integer>> set = new HashSet<>();
    private int[][] rec;
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        int i = 0;
        int j = length - 1;
        rec = new int[length][length];
        rlSum(i,j,nums,target);
        return new ArrayList<>(set);
    }

    private void rlSum(int i,int j,int[] nums,int target){
        if(i>=j-2){
            return;
        }
        int value0 = nums[i];
        int value1 = nums[j];
        int needValue = target - value0 - value1;
        if(nums[i+1] + nums[i+2] > needValue){
            while(i>=j-2 && nums[j]==nums[j-1]) j--;
            if(rec[i][j-1]==0){
                rlSum(i,j-1,nums,target);
                rec[i][j-1] = 1;
            }
        }else if(nums[j-1] + nums[j-2] < needValue){
            while(i>=j-2 && nums[i]==nums[i+1]) i++;
            if(rec[i+1][j]==0){
                rlSum(i+1,j,nums,target);
                rec[i+1][j] = 1;
            }
        }else{
            findTwoSum(value0,value1,i+1,j-1,nums,needValue);
            while(i>=j-2 && nums[j]==nums[j-1]) j--;
            while(i>=j-2 && nums[i]==nums[i+1]) i++;
            if(rec[i][j-1]==0){
                rlSum(i,j-1,nums,target);
                rec[i][j-1] = 1;
            }
            if(rec[i+1][j]==0){
                rlSum(i+1,j,nums,target);
                rec[i+1][j] = 1;
            }
        }
    }

    private void findTwoSum(int value0,int value1,int i,int j,int[] nums,int needValue){
        if(i>=j){
            return;
        }
        System.out.println(i+","+j);
        Map<Integer,Integer> map = new HashMap<>();
        for(int k = i;k <= j; k++){

            if(map.containsKey(nums[k])){
                int value2 = nums[k];
                int value3 = map.get(nums[k]);
                set.add(Arrays.asList(value0,value3,value2,value1));
            }else{
                map.put(needValue - nums[k],nums[k]);
            }
        }
    }


    /* 14ms  others
    * 其实 每两个数字都可以用左右索引缩小的方式做
    * 然后看作一个整体进行break continue 提升效率*/
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int k,l,sum;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length < 4)
            return res;
        for(int i = 0; i < nums.length-3; i++){
            if(i>0 && nums[i] == nums[i-1])
                continue;
            if(nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if(nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) continue;
            for(int j = i+1; j < nums.length-2; j++){
                if(j>(i+1) && nums[j] == nums[j-1])
                    continue;
                if(nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if(nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1] < target) continue;
                k = j+1;
                l = nums.length-1;
                while(k<l){
                    sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if(sum < target)
                        k++;
                    if(sum > target)
                        l--;
                    if(sum == target){
                        List<Integer> part = new ArrayList<Integer>();
                        part.add(nums[i]);
                        part.add(nums[j]);
                        part.add(nums[k]);
                        part.add(nums[l]);
                        res.add(part);
                        while(k < l && nums[k] == nums[k+1])
                            k++;
                        k++;
                        while(k<l && nums[l] == nums[l-1])
                            l--;
                        l--;
                    }
                }
            }
        }
        return res;
    }
}
