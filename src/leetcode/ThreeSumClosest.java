package leetcode;

import java.util.Arrays;
/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example:
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).*/
public class ThreeSumClosest {

    /* 效率较低 因为再缩小范围时每次都在比较 */
    public int mySolution(int[] nums, int target) {
        Arrays.sort(nums);
        int res = target;
        int subNum = Integer.MAX_VALUE;
        for(int i = 0;i < nums.length; i++){
            if(i == 0 || nums[i] != nums[i-1]){
                int needSum = target - nums[i];
                int lo = i+1;
                int hi = nums.length - 1;
                while(lo < hi){
                    int sum = nums[i] + nums[lo] + nums[hi];
                    if(Math.abs(target - sum) < subNum){
                        subNum = Math.abs(target - sum);
                        res = sum;
                    }
                    if(nums[lo] + nums[hi] < needSum){
                        while(lo<hi && nums[lo] == nums[lo+1]) lo++;
                        lo++;
                    }else{
                        while(lo<hi && nums[hi] == nums[hi-1]) hi--;
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    /*跳过多余比较 叫比较放在 缩小范围的IF内部*/
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest=nums[0]+nums[1]+nums[2];
        int low,high;
        for(int i=0;i<nums.length-1;i++){
            low=i+1;
            high=nums.length-1;
            while(low<high){
                if(nums[low]+nums[high]==target-nums[i]) return target;
                else if(nums[low]+nums[high]>target-nums[i]){
                    while(low<high&&nums[low]+nums[high]>target-nums[i]) high--;
                    if(Math.abs(nums[i]+nums[low]+nums[high+1]-target)<Math.abs(closest-target))
                        closest=nums[i]+nums[low]+nums[high+1];
                }
                else{
                    while(low<high&&nums[low]+nums[high]<target-nums[i]) low++;
                    if(Math.abs(nums[i]+nums[low-1]+nums[high]-target)<Math.abs(closest-target))
                        closest=nums[i]+nums[low-1]+nums[high];
                }
            }
        }
        return closest;
    }
}
