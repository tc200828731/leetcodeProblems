package leetcode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Note:
 *
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.*/
public class SwapNodesinPairs {
    /* mysolution  2ms*/
    public ListNode swapPairs(ListNode head) {
        if(head == null) return head;
        ListNode node = head;
        ListNode res = head.next!=null?head.next:head;
        ListNode before = null;
        while(node != null){
            if(node.next == null) break;
            ListNode nextNode = node.next;
            twoChange(before,node,nextNode);
            before = node;
            node = node.next;
        }
        return res;
    }

    private void twoChange(ListNode l1,ListNode l2,ListNode l3){
        if(l1!=null){
            l1.next = l3;
        }
        l2.next = l3.next;
        l3.next = l2;
    }
}
