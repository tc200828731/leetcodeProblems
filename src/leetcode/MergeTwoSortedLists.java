package leetcode;
/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4*/
public class MergeTwoSortedLists {

    /* 类似归并 */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode l3 = head;
        while(l1 != null || l2 != null){
            if(l1 == null && l2 != null){
                l3.next = l2;
                l3 = l3.next;
                l2 = l2.next;
            }else if(l1 != null && l2 == null){
                l3.next = l1;
                l3 = l3.next;
                l1 = l1.next;
            }else if(l1.val > l2.val){
                l3.next = l2;
                l3 = l3.next;
                l2 = l2.next;
            }else{
                l3.next = l1;
                l3 = l3.next;
                l1 = l1.next;
            }
        }
        return head.next;
    }

    /* 另外的思路 */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
