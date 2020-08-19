  public static void removeLoop(Node head) {
        Node fast = head;
        Node slow = head;
        //DETECT WHERE THE TWO POINTERS MEET IN THE LOOP
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast.equals(slow)) {
                slow = head;
                break;
            }
        }
        //NO LOOP
        if(fast.next == null || fast.next.next == null) {
            return;
        }
        //SPECIAL CASE WHERE THE WHOLE LL IS IN A LOOP (CIRCULAR LL)
        if(fast.equals(head)) {
            Node prev = fast;
            fast = fast.next;
            while(!fast.equals(slow)) {
                fast = fast.next;
                prev = prev.next;
            }
            prev.next = null;
            return;
        }
        //NORMAL CASE
        Node prev = fast;
        fast = fast.next;
        slow = slow.next;
        if(fast.equals(slow)) {
            prev.next = null;
            return;
        }
        while(!fast.equals(slow)) {
            fast = fast.next;
            slow = slow.next;
            prev = prev.next;
        }
        prev.next = null;
    }
