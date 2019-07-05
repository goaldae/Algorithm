package linkedList;

class MyLinkedList{
    Node head;
    int size;

    MyLinkedList(){
        head = new Node(null);
        size = 0;
    }
    MyLinkedList(Integer x){
        head = new Node(x);
        size = 1;
    }

    class Node{
        Integer data;
        Node link;

        Node(Integer x){
            this.data = x;
            link = null;
        }
    }

    Node makeNode(int x){
        Node tempNode = new Node(x);
        return tempNode;
    }

    void add(int x){
        if(head.data == null){
            head.data = x;
            size++;
            return;
        }
        Node cur = head;

        while(cur.link != null){
            cur = cur.link;
        }
        Node newNode = makeNode(x);
        cur.link = newNode;
        size++;
    }

    void printLinkedList(){
        Node cur = head;
        while(cur != null){
            System.out.print(cur.data+" ");
            cur = cur.link;
        }
        System.out.println();
        System.out.print(size);
    }
}

public class LinkedList {
    public static void main(String[] args) throws Exception {
        MyLinkedList l = new MyLinkedList(0);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.printLinkedList();
    }
}
