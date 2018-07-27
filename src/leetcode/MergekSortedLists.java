package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * * Definition for singly-linked list.
 *      * public class ListNode {
 *      * int val;
 *      * ListNode next;
 *      * ListNode(int x) { val = x; }
 *      * }
 */
public class MergekSortedLists {

    /* my solution brute force */
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            while (node != null) {
                list.add(node.val);
                node = node.next;
            }
        }
        Collections.sort(list);
        ListNode res = new ListNode(0);
        ListNode tep = res;
        for (Integer num : list) {

            tep.next = new ListNode(num);
            tep = tep.next;

        }
        return res.next;
    }

    /* 利用归并 -  优先队列（堆排序）的思想*/
    /*public ListNode mergeKLists2(ListNode[] lists) {

    }*/

    /*分而治之*/
    public ListNode mergeKLists3(ListNode[] lists){
        int interval = 1;
        int len = lists.length;
        while(interval < len){
            for(int i = 0; i<len -interval ; i+=2*interval){
                lists[i] = mergeTwoList(lists[i],lists[i+interval]);
            }
            interval = 2*interval;
        }
        return len>0?lists[0]:null;
    }

    private ListNode mergeTwoList(ListNode l1,ListNode l2){
        ListNode res = new ListNode(0);
        ListNode l3 = res;
        while(l1!=null || l2!=null){
            if(l1 == null){
                l3.next = l2;
                l2 = l2.next;
                l3 = l3.next;
            }else if(l2 == null){
                l3.next = l1;
                l1 = l1.next;
                l3 = l3.next;
            }else if(l1.val > l2.val){
                l3.next = l2;
                l2 = l2.next;
                l3 = l3.next;
            }else{
                l3.next = l1;
                l1 = l1.next;
                l3 = l3.next;
            }
        }
        return res.next;
    }
}
