package ss10_dsa_list.practice.linked_list;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList(10);
        ll.addFirst(11);
        ll.addFirst(12);
        ll.addFirst(13);

        ll.add(4,9);
        ll.add(4,9);
        ll.printList();

    }


}
