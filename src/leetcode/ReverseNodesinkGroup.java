package leetcode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * Example:
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Note:
 * <p>
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class ReverseNodesinkGroup {
    /* my solution 效率较低*/
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return head;
        ListNode res = head;
        ListNode tem = head;
        int sign = 0;
        ListNode before = null;
        while (tem != null) {
            int i = 0;
            ListNode fir = tem;
            while (i < k && tem != null) {
                i++;
                if (sign == 0 && i == k) {
                    res = tem;
                }
                tem = tem.next;
            }
            if (i == k) {
                ListNode nextNode = null;
                ListNode node = fir;
                for (int l = 0; l < k - 1; l++) {
                    ListNode tbefore = before;
                    for (int m = 0; m < k - l - 1; m++) {
                        ListNode beforeNode = node.next;
                        if (m == 0) {
                            nextNode = beforeNode;
                        }
                        twoChange(tbefore, node, node.next);
                        tbefore = beforeNode;
                    }
                    node = nextNode;//更新node 为该k个节点的第一节点
                }
                sign++;
                before = fir;
            }

        }
        return res;
    }

    private void twoChange(ListNode l1, ListNode l2, ListNode l3) {
        if (l1 != null) {
            l1.next = l3;
        }
        l2.next = l3.next;
        l3.next = l2;
    }

    /* 迭代 反转指向 从后向前*/
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup2(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }

    /* 4ms 高效*/
    public ListNode reverseKGroup3(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode run = dummy;
        while (run != null) {
            int count = k;
            while (count > 0 && run != null) {
                run = run.next;
                count--;
            }
            if (run == null) return dummy.next;
            ListNode next = prev.next;
            while (prev.next != run) {
                ListNode cur = prev.next;
                prev.next = cur.next;
                cur.next = run.next;
                run.next = cur;
            }
            prev = next;
            run = next;
        }
        return dummy.next;

    }

}
