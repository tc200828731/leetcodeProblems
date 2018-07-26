package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 */
 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
public class RemoveNthNodeFromEndofList {

    /* mysolution */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int i = 0;
        ListNode tar = null;
        ListNode node = head;
        while(node!=null){
            i++;
            if(i>n){
                if(i==n+1) tar = head;
                else tar = tar.next;
            }
            node = node.next;
        }
        if(tar!= null){
            tar.next = tar.next.next;
            return head;
        }else{
            return head.next;
        }

    }
}
